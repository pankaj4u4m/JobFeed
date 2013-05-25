package code.snippet
import net.liftweb._
import http._
import common._
import util.Helpers._
import scala.xml.NodeSeq
import net.liftweb.http.StatefulSnippet
import code.model.User
import net.liftweb.http.js.JsCmds

object SignUp {
  def render = {
    val ns = User.signup
    ns.andThen(
      "#txtEmail" #> <input type="email" name="username" class="input-block-level" placeholder={ S.?("email.address") }/>)

    ns;
  }

  def signUpButton = { "button [onclick]" #> SHtml.onEvent(s => S.redirectTo("/user/sign_up")) }
}