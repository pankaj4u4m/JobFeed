package feed.frontend.snippet
import net.liftweb._
import http._
import common._
import util.Helpers._
import scala.xml.NodeSeq
import feed.frontend.model.User
import net.liftweb.common.Full

object Resume {
  def render = {
    User.currentUser match {
      case Full(currentUser) => "#sidebar" #> <table>
                                                <tbody>
                                                  <tr><td>{ currentUser.firstNameDisplayName + " " + currentUser.lastNameDisplayName }</td></tr>
                                                  <tr><td></td><td>{ "Software Engineer" }</td></tr>
                                                  <tr><td>Email: </td><td>{ currentUser.email }</td></tr>
                                                  <tr><td>Company: </td><td>{ "Amazon India" }</td></tr>
                                                  <tr><td></td><td>{ "2nd Floor Q-City, Gachibowli, Hyderabad, AP - 500032" }</td></tr>
                                                </tbody>
                                              </table>
      case _ => "#sidebar" #> <table>
                                <tbody></tbody>
                              </table>

    }
  }
}