package feed.frontend.model

import net.liftweb.mapper._

/**
 * store metadata and path to the resume/ full detail of edited resume in system in json
 */
object Resume extends Resume with LongKeyedMetaMapper[Resume] {

}

class Resume extends LongKeyedMapper[Resume] with IdPK with CreatedUpdated {
  def getSingleton = Resume
  object order extends MappedInt(this) // order of each header in stack for one resume
  object user extends MappedLongForeignKey(this, User)
  object title extends MappedString(this, 256)
  object header extends MappedLongForeignKey(this, Header)
  object details extends MappedText(this)
}