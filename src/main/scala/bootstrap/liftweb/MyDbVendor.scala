package bootstrap.liftweb

import net.liftweb.db.ConnectionManager
import java.sql.Connection
import net.liftweb.util.Props
import net.liftweb.common.Box
import net.liftweb.common.Full
import java.sql.DriverManager
import org.slf4j.LoggerFactory
import net.liftweb.common.Empty
import net.liftweb.db.ConnectionIdentifier

object MyDbVendor extends ConnectionManager {
  private val log = LoggerFactory.getLogger(MyDbVendor.getClass())

  private var pool: List[Connection] = Nil
  private var poolSize = 0
  private val maxPoolSize = 4

  private lazy val chooseDriver = Props.mode match {
    case Props.RunModes.Production => "mysql" //FIXME
    case _ => "org.h2.Driver"
  }

  private lazy val chooseURL = Props.mode match {
    case Props.RunModes.Production => "jdbc:mysql" //FIXME
    case _ => "jdbc:h2:mem:lift_mapperexample;DB_CLOSE_DELAY=-1"
  }

  private def createOne: Box[Connection] = try {
    val driverName: String = Props.get("db.driver") openOr chooseDriver
    val dbUrl: String = Props.get("db.url") openOr chooseURL
    Class.forName(driverName)

    val dm = (Props.get("db.user"), Props.get("db.password")) match {
      case (Full(user), Full(pwd)) =>
        DriverManager.getConnection(dbUrl, user, pwd)
      case _ => DriverManager.getConnection(dbUrl)
    }
    Full(dm)
  } catch {
    case e: Exception => {
      log.error(e.getMessage(), e)
      Empty
    }
  }

  def newConnection(name: ConnectionIdentifier): Box[Connection] = synchronized {
    pool match {
      case Nil if (poolSize < maxPoolSize) => {
        val ret = createOne
        poolSize = poolSize + 1
        ret.foreach(c => pool = c :: pool)
        ret
      }

      case Nil => {
        wait(1000L)
        newConnection(name)
      }

      case x :: xs => try {
        x.setAutoCommit(false)
        Full(x)
      } catch {
        case e => try {
          pool = xs
          poolSize = poolSize + 1
          x.close()
          newConnection(name)
        } catch {
          case e => newConnection(name)
        }
      }
    }
  }

  def releaseConnection(conn: Connection): Unit = synchronized {
    pool = conn :: pool
    notify
  }
}