package code.model
import net.liftweb._
import http._
import common._
import util.Helpers._
import scala.xml.NodeSeq
import mapper._
import net.liftweb.util.Settable
import scala.xml.Elem

object User extends User with MetaMegaProtoUser[User] {

  val log = Logger(User.getClass())

  override def dbTableName = "Users"

  override lazy val fieldOrder = List(email, password)

  override val basePath = List()

  override def screenWrap = Full(<lift:surround with="user" at="user"><lift:bind/></lift:surround>)

  override def skipEmailValidation = true

  override def signupFields = List(email, password);

  override def editFields: List[FieldPointerType] = List(email)

  override def signupXhtml(user: TheUserType) = {
    (<form method="post" action={ S.uri } class="form-signin">
       <table>
         <tr><td colspan="2"><h3 class="form-signin-heading">{ S.?("sign.up") }</h3></td></tr>
         { localForm(user, false, signupFields) }
         <tr><td><user:submit/></td></tr>
       </table>
     </form>)
  }

  override def loginXhtml = {
    (<form method="post" action={ S.uri } class="form-signin">
       <table>
         <tr><td colspan="2"><h3 class="form-signin-heading">{ S.?("log.in") }</h3></td></tr>
         <tr><td><input type="email" name="username" class="input-block-level span3" placeholder={ S.?("email.address") } required="" maxlength="48"/></td></tr>
         <tr><td><input type="password" name="password" class="input-block-level span3" placeholder={ S.?("password") } required="" maxlength="48"/></td></tr>
         <tr>
           <td>
             <user:submit/>
             <span class="pull-right"><a href={ lostPasswordPath.mkString("/", "/", "") }> { S.?("recover.password") }</a> </span>
           </td>
         </tr>
       </table>
     </form>)
  }

  override protected def localForm(user: TheUserType, ignorePassword: Boolean, fields: List[FieldPointerType]): NodeSeq = {
    super.localForm(user, ignorePassword, fields)
    for {
      pointer <- fields
      field <- computeFieldFromPointer(user, pointer).toList
      if field.show_? && (!ignorePassword || !pointer.isPasswordField_?)
      form <- field.toForm.toList
    } yield <tr><td>{ form }</td></tr>
  }

  override def standardSubmitButton(name: String, func: () => Any = () => {}) = {
    SHtml.submit(name, func, "class" -> "btn btn-large btn-success");
  }

}

class User extends MegaProtoUser[User] with CreatedUpdated {
  def getSingleton = User

  override lazy val email: MappedEmail[User] = new ModifiedEmail(this, 48)

  protected class ModifiedEmail(obj: User, size: Int) extends MyEmail(obj, size) {
    override protected def formInputType = "email"
    override def _toForm: Box[Elem] =
      S.fmapFunc({ s: List[String] => this.setFromAny(s) }) { name =>
        Full(appendFieldId(
          <input type={ formInputType } maxlength={ maxLen.toString } name={ name } value={ is match { case null => "" case s => s.toString } } class="input-block-level span3" placeholder={ S.?("email.address") } required=""/>))
      }
  }

  override lazy val password: MappedPassword[User] = new ModifiedPassword(this)

  protected class ModifiedPassword(obj: User) extends MyPassword(obj) {
    override def _toForm: Box[NodeSeq] = {
      S.fmapFunc({ s: List[String] => this.setFromAny(s) }) { funcName =>
        Full(<span>
               { appendFieldId(<input type={ formInputType } name={ funcName } value="" class="input-block-level span3" placeholder={ displayName } required=""/>) }
               <input type={ formInputType } name={ funcName } value="" class="input-block-level span3" placeholder={ S.?("repeat") + " " + displayName } required=""/>
             </span>)
      }
    }
  }
}