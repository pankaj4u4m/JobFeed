package feed.frontend.model

import net.liftweb.mapper._

/**
 * defines all category of jobs
 */
object JobCategory extends JobCategory with LongKeyedMetaMapper[JobCategory] {

}

class JobCategory extends LongKeyedMapper[JobCategory] with IdPK with CreatedUpdated {
  def getSingleton = JobCategory
  object category extends MappedString(this, 256)
}