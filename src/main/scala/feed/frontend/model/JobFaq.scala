package feed.frontend.model

import net.liftweb.mapper._

/**
 * FAQs for particular job
 */
object JobFaq extends JobFaq with LongKeyedMetaMapper[JobFaq] {

}

class JobFaq extends LongKeyedMapper[JobFaq] with IdPK with CreatedUpdated {
  def getSingleton = JobFaq
  object question extends MappedText(this)
  object answer extends MappedText(this)
  object whoAsked extends MappedLongForeignKey(this, User)
  object whoAnswered extends MappedLongForeignKey(this, User)
}