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
    var ns = User.signup
    ns.andThen("#userlogin" #> <span> { "Already on Job Feed?" } <a href="/login">Log in</a> </span>)
    ns
  }
}