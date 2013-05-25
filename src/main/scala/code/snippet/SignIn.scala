package code.snippet
import net.liftweb._
import http._
import common._
import util.Helpers._
import scala.xml.NodeSeq
import net.liftweb.http.StatefulSnippet
import net.liftweb.http.js.JsCmds

class SignIn extends StatefulSnippet {
  private var email = ""
  private var password = ""

  private val whence = S.referer openOr "/"

  def dispatch = {
    case "render" => render
  }

  def render = {
    //FIXME
    "name=email" #> SHtml.email(email, (x: String) => { email = x }) &
      "name=password" #> SHtml.password(password, password = _) &
      "type=submit" #> SHtml.onSubmitUnit(process)
  }

  private def process() = {
    S.error("Authentication not implemented")
  }
}