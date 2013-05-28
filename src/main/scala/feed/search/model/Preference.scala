package feed.search.model

import net.liftweb.mapper._

/**
 * stores various preferences for the user which is applicable for feed
 */
object Preference extends Preference with LongKeyedMetaMapper[Preference] {

}

class Preference extends LongKeyedMapper[Preference] with IdPK with CreatedUpdated {
  def getSingleton = Preference
}