package code.model
import net.liftweb._
import http._
import common._
import util.Helpers._
import scala.xml.NodeSeq
import mapper._

object User extends User with MetaMegaProtoUser[User] {
  override def dbTableName = "Users"

  override lazy val fieldOrder = List(id, email, password)
  override val basePath = "user" :: Nil
  override def screenWrap = Full(<lift:surround with="default" at="signup"><lift:bind/></lift:surround>)
}

class User extends MegaProtoUser[User] with CreatedUpdated {
  def getSingleton = User
}