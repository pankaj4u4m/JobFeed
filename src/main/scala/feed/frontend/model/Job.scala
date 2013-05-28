package feed.frontend.model

import net.liftweb.mapper._

/**
 * list of jobs in company
 */
object Job extends Job with LongKeyedMetaMapper[Job] {

}

class Job extends LongKeyedMapper[Job] with IdPK with CreatedUpdated {
  def getSingleton = Job
  object company extends MappedLongForeignKey(this, Company)
  object jobCategory extends MappedLongForeignKey(this, JobCategory)
  object detail extends MappedText(this)
}