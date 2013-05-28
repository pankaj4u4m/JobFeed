package feed.frontend.model

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
  object industryType extends MappedLongForeignKey(this, IndustryType)
  object revenue extends MappedLong(this)
  object currency extends MappedString(this, 256)
  object social extends MappedText(this)

  def employeesIds: List[Employee] = Employee.findAll(By(Employee.company, this))

  def employees: List[User] = employeesIds.map((x) => User.findAll(By(User.id, x.whoWorks))).flatten

  def jobs: List[Job] = Job.findAll(By(Job.company, this))

  def reviews: List[Review] = Review.findAll(By(Review.company, this))
}
