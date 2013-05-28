package feed.frontend.model

import net.liftweb.mapper._

/**
 * follow perticular job/interview questions/company etc
 */
object Follow extends Follow with LongKeyedMetaMapper[Follow] {

}

class Follow extends LongKeyedMapper[Follow] with IdPK with CreatedUpdated {
  def getSingleton = Follow
  object follower extends MappedLongForeignKey(this, User)
  object following extends MappedLong(this)
  object category extends MappedString(this, 256)
}