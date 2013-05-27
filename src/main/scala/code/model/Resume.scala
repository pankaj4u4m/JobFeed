package code.model

import net.liftweb.mapper._

/**
 * store metadata and path to the resume/ full detail of edited resume in system in json
 */
object Resume extends Resume with LongKeyedMetaMapper[Resume] {

}

class Resume extends LongKeyedMapper[Resume] with IdPK with CreatedUpdated {
  def getSingleton = Resume
  object order extends MappedInt(this)
  object user extends MappedLongForeignKey(this, User)
  object header extends MappedString(this, 256)
  object resumeHeader extends MappedLongForeignKey(this, ResumeHeader)
  object description extends MappedText(this)
}