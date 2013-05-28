package feed.frontend.model

import net.liftweb.mapper._

/**
 * all types of industry
 */
object IndustryType extends IndustryType with LongKeyedMetaMapper[IndustryType] {

}

class IndustryType extends LongKeyedMapper[IndustryType] with IdPK with CreatedUpdated {
  def getSingleton = IndustryType
  object industryType extends MappedString(this, 256)
}