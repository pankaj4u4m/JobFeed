package feed.frontend.model

import net.liftweb.mapper._

/**
 * interview questions asked in company
 */
object InterviewQuestion extends InterviewQuestion with LongKeyedMetaMapper[InterviewQuestion] {

}

class InterviewQuestion extends LongKeyedMapper[InterviewQuestion] with IdPK with CreatedUpdated {
  def getSingleton = InterviewQuestion
  object company extends MappedLongForeignKey(this, Company)
  object whoAsked extends MappedLongForeignKey(this, User)
  object category extends MappedLongForeignKey(this, JobCategory)
  object question extends MappedText(this)
}