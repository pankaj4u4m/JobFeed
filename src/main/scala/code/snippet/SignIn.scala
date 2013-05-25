package code.snippet
import net.liftweb._
import http._
import common._
import util.Helpers._
import scala.xml.NodeSeq
import net.liftweb.http.StatefulSnippet
import net.liftweb.http.js.JsCmds
import code.model.User

object SignIn {
  def render = User.signup
}