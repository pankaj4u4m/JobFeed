package feed.frontend.model

import net.liftweb.mapper._

/**
 * store all the links to the file in the filesystem for resume/photo/videos of company/user
 */
object LinkPath extends LinkPath with LongKeyedMetaMapper[LinkPath] {

}

class LinkPath extends LongKeyedMapper[LinkPath] with IdPK with CreatedUpdated {
  def getSingleton = LinkPath
  object path extends MappedText(this)
  object category extends MappedString(this, 256)
  object belongsToId extends MappedLong(this)
}