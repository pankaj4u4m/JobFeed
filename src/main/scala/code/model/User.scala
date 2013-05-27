package code.model
import net.liftweb._
import http._
import common._
import util.Helpers._
import scala.xml.NodeSeq
import mapper._
import net.liftweb.util.Settable
import scala.xml.Elem
import net.liftweb.mapper.MappedOneToMany

object User extends User with MetaMegaProtoUser[User] {

  val log = Logger(User.getClass())

  override def dbTableName = "Users"

  override lazy val fieldOrder = List(email, password)

  override val basePath = List()

  override def screenWrap = Full(
    <lift:surround with="user" at="user">
      <lift:bind/>
    </lift:surround>)

  override def skipEmailValidation = true

  override def signupFields = List(email, password);

  override def editFields: List[FieldPointerType] = List(email)

  override def signup = {
    var ns = super.signup
    //FIXME find better way to create link from list
    //FIXME User localization for string
    (<div class="header">
       <span class="pull-right">Already on Job Feed? <a href={ loginPageURL }>{ S.?("log.in") }</a></span>
       <h2>Job Feed</h2>
     </div>).++(ns)
  }

  override def signupXhtml(user: TheUserType) = {
    (<form method="post" action={ S.uri } class="form-signin">
       <table>
         <tr><td colspan="2"><h3 class="form-signin-heading">{ S.?("sign.up") }</h3></td></tr>
         { localForm(user, false, signupFields) }
         <tr><td><user:submit/></td></tr>
       </table>
     </form>)
  }

  override def login = {
    var ns = super.login //FIXME find better way to create link
    //FIXME User localization for string
    //FIXME replace onClick
    val signUpButton = "button [onclick]" #> SHtml.onEvent(s => S.redirectTo(signUpPath.mkString("/", "/", "")))
    signUpButton((<div class="header">
                    <span class="pull-right"><button class="btn"><strong>Create An Account</strong></button></span>
                    <h2>Job Feed</h2>
                  </div>).++(ns))

  }

  override def loginXhtml = {
    (<form method="post" action={ S.uri } class="form-signin">
       <table>
         <tr><td colspan="2"><h3 class="form-signin-heading">{ S.?("log.in") }</h3></td></tr>
         <tr><td><input type="text" name="username" class="input-block-level" placeholder={ S.?("email.address") } maxlength="48"/></td></tr>
         <tr><td><input type="password" name="password" class="input-block-level" placeholder={ S.?("password") } maxlength="48"/></td></tr>
         <tr><td><user:submit/></td></tr>
         <tr><td><span style=" padding-top: 10px; display: block; "><a href={ lostPasswordPath.mkString("/", "/", "") } style=" margin-left: 65px; "> { S.?("recover.password") }</a> </span></td></tr>
       </table>
     </form>)
  }

  override def edit = {
    var ns = super.edit
    //FIXME find better way to create link from list
    (<div class="header">
       <span class="pull-right"> <a href={ logoutPath.mkString("/", "/", "") }>{ S.?("log.out") }</a></span>
       <h2>Job Feed</h2>
     </div>).++(ns)
  }

  override def editXhtml(user: TheUserType) = {
    (<form method="post" action={ S.uri } class="form-signin">
       <table>
         <tr><td colspan="2"><h3 class="form-signin-heading">{ S.?("edit") }</h3></td></tr>
         { localForm(user, true, editFields) }
         <tr><td><user:submit/></td></tr>
       </table>
     </form>)
  }

  override def lostPassword = {
    var ns = super.lostPassword
    //FIXME find better way to create link from list
    //FIXME User localization for string
    (<div class="header">
       <span class="pull-right">Already on Job Feed? <a href={ loginPageURL }>{ S.?("log.in") }</a></span>
       <h2>Job Feed</h2>
     </div>).++(ns)
  }

  override def lostPasswordXhtml = {
    (<form method="post" action={ S.uri } class="form-signin">
       <table>
         <tr><td colspan="2"> { S.?("enter.email") } </td></tr>
         <tr><td>{ userNameFieldString }</td><td><user:email/></td></tr>
         <tr><td><user:submit/></td></tr>
       </table>
     </form>)
  }

  override def passwordReset(id: String) = {
    var ns = super.passwordReset(id)
    //FIXME find better way to create link from list
    //FIXME User localization for string
    (<div class="header">
       <span class="pull-right">Already on Job Feed? <a href={ loginPageURL }>{ S.?("log.in") }</a></span>
       <h2>Job Feed</h2>
     </div>).++(ns)
  }

  override def passwordResetXhtml = {
    (<form method="post" action={ S.uri } class="form-signin">
       <table>
         <tr><td colspan="2"> { S.?("reset.your.password") } </td></tr>
         <tr><td>{ S.?("enter.your.new.password") }</td><td><user:pwd/></td></tr>
         <tr><td>{ S.?("repeat.your.new.password") }</td><td><user:pwd/></td></tr>
         <tr><td><user:submit/></td></tr>
       </table>
     </form>)
  }

  override def changePassword = {
    var ns = super.changePassword
    //FIXME User localization for string
    (<div class="header">
       <span class="pull-right"><a href={ logoutPath.mkString("/", "/", "") }>{ S.?("log.out") }</a></span>
       <h2>Job Feed</h2>
     </div>).++(ns)
  }

  override def changePasswordXhtml = {
    (<form method="post" action={ S.uri } class="form-signin">
       <table>
         <tr><td colspan="2">{ S.?("change.password") }</td></tr>
         <tr><td>{ S.?("old.password") }</td><td><user:old_pwd/></td></tr>
         <tr><td>{ S.?("new.password") }</td><td><user:new_pwd/></td></tr>
         <tr><td>{ S.?("repeat.password") }</td><td><user:new_pwd/></td></tr>
         <tr><td><user:submit/></td></tr>
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
    //override protected def formInputType = "email"
    override def _toForm: Box[Elem] =
      S.fmapFunc({ s: List[String] => this.setFromAny(s) }) { name =>
        Full(appendFieldId(
          <input type={ formInputType } maxlength={ maxLen.toString } name={ name } value={ is match { case null => "" case s => s.toString } } class="input-block-level" placeholder={ S.?("email.address") }/>))
      }
  }

  override lazy val password: MappedPassword[User] = new ModifiedPassword(this)

  protected class ModifiedPassword(obj: User) extends MyPassword(obj) {
    override def _toForm: Box[NodeSeq] = {
      S.fmapFunc({ s: List[String] => this.setFromAny(s) }) { funcName =>
        Full(<span>
               { appendFieldId(<input type={ formInputType } name={ funcName } value="" class="input-block-level" placeholder={ displayName }/>) }
               <input type={ formInputType } name={ funcName } value="" class="input-block-level" placeholder={ S.?("repeat") + " " + displayName }/>
             </span>)
      }
    }
  }
}