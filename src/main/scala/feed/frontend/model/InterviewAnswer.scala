package feed.frontend.model

import net.liftweb.mapper._

/**
 * answers to the questions for interview questions
 */
object InterviewAnswer extends InterviewAnswer with LongKeyedMetaMapper[InterviewAnswer] {

}

class InterviewAnswer extends LongKeyedMapper[InterviewAnswer] with IdPK with CreatedUpdated {
  def getSingleton = InterviewAnswer
  object question extends MappedLongForeignKey(this, InterviewQuestion)
  object whoAnswered extends MappedLongForeignKey(this, User)
  object answer extends MappedText(this)
}