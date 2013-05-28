package feed.frontend.model

import net.liftweb.mapper._

/**
 * store history of viewed job/questions etc
 */
object History extends History with LongKeyedMetaMapper[History] {

}

class History extends LongKeyedMapper[History] with IdPK with CreatedUpdated {
  def getSingleton = History
  object link extends MappedText(this)
  object category extends MappedString(this, 256)
  object whoes extends MappedLongForeignKey(this, User)
}