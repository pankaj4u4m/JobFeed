package feed.frontend.model

import net.liftweb.mapper._

/**
 * all the authorized user under company to have full access to company data
 * this is separate from company/user because there will be many users who are not in the company
 */
object Employee extends Employee with LongKeyedMetaMapper[Employee] {

}

class Employee extends LongKeyedMapper[Employee] with IdPK with CreatedUpdated {
  def getSingleton = Employee
  object company extends MappedLongForeignKey(this, Company)
  object whoWorks extends MappedLongForeignKey(this, User)
}