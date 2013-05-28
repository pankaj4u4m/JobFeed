package feed.frontend.model

import net.liftweb.mapper._

/**
 * all companies detail in json
 */
object CompanyDetail extends CompanyDetail with LongKeyedMetaMapper[CompanyDetail] {

}

class CompanyDetail extends LongKeyedMapper[CompanyDetail] with IdPK with CreatedUpdated {
  def getSingleton = CompanyDetail
  object company extends MappedLongForeignKey(this, Company)
  object header extends MappedLongForeignKey(this, Header)
  object detail extends MappedText(this)
  object whoEdited extends MappedLongForeignKey(this, User)
}