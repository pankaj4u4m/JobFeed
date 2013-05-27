package code.model

import net.liftweb.mapper._
/**
 * store resume header, common header to hint user which he is editing resume
 */
object ResumeHeader extends ResumeHeader with LongKeyedMetaMapper[ResumeHeader] {

}

class ResumeHeader extends LongKeyedMapper[ResumeHeader] with IdPK with CreatedUpdated {
  def getSingleton = ResumeHeader
  object heading extends MappedString(this, 256)
}