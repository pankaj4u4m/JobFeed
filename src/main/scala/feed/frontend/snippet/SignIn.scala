package feed.frontend.snippet
import net.liftweb._
import http._
import common._
import util.Helpers._
import scala.xml.NodeSeq
import net.liftweb.http.StatefulSnippet
import net.liftweb.http.js.JsCmds
import feed.frontend.model.User

object SignIn {
  def render = {
    var ns = User.signup
    ns.andThen("#userlogin" #> <button class="pull-right btn"><strong>Create An Account</strong></button>)
    ns
  }
}