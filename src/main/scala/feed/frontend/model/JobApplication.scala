package feed.frontend.model

import net.liftweb.mapper._

/**
 * all the applications for the job provided to the company
 */
object JobApplication extends JobApplication with LongKeyedMetaMapper[JobApplication] {

}

class JobApplication extends LongKeyedMapper[JobApplication] with IdPK with CreatedUpdated {
  def getSingleton = JobApplication
  object job extends MappedLongForeignKey(this, Job)
  object whichResume extends MappedLongForeignKey(this, Resume)
}