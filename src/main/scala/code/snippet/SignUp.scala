package code.snippet
import net.liftweb._
import http._
import common._
import util.Helpers._
import scala.xml.NodeSeq
import net.liftweb.http.StatefulSnippet
import code.model.User
import net.liftweb.http.js.JsCmds

class SignUp extends StatefulSnippet {
  private var email = ""
  private var password = ""
  private var reemail = ""

  private val whence = S.referer openOr "/"

  def dispatch = {
    case "render" => render
    case "signUpButton" => signUpButton
  }

  def render(html: NodeSeq): NodeSeq = {
    val user = new User
    user.toForm(Full("Save"), { _.save })
  }

  private def process() = {
    Option(email) match {
      case Some(email) if (email == reemail) => S.error("emailId same, Authentication not implemented")
      case _ => S.error("Account Creation not implemented")
    }
  }

  def signUpButton = { "button [onclick]" #> SHtml.onEvent(s => S.redirectTo("/")) }

}