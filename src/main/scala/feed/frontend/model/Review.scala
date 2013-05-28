package feed.frontend.model

import net.liftweb.mapper._

/**
 * review of company/user as company
 */
object Review extends Review with LongKeyedMetaMapper[Review] {

}

class Review extends LongKeyedMapper[Review] with IdPK with CreatedUpdated {
  def getSingleton = Review
  object company extends MappedLongForeignKey(this, Company)
  object title extends MappedString(this, 256)
  object detail extends MappedText(this)
}