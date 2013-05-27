package code.model

import net.liftweb.mapper._

/**
 * company metadata and path to photo/video
 */
object Company extends Company with LongKeyedMetaMapper[Company] {

}

class Company extends LongKeyedMapper[Company] with IdPK with CreatedUpdated {
  def getSingleton = Company
  object name extends MappedString(this, 256)
  object foundedAt extends MappedInt(this)
  object typeOf extends MappedString(this, 256)
  object industry extends MappedString(this, 256)
  object revenue extends MappedLong(this)
  object currency extends MappedString(this, 256)
  object social extends MappedText(this)
}