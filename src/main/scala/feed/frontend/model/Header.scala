package feed.frontend.model

import net.liftweb.mapper._
/**
 * store resume header, common header to hint user which he is editing resume
 */
object Header extends Header with LongKeyedMetaMapper[Header] {

}

class Header extends LongKeyedMapper[Header] with IdPK with CreatedUpdated {
  def getSingleton = Header
  object header extends MappedString(this, 256)
  object headertype extends MappedString(this, 256)
}