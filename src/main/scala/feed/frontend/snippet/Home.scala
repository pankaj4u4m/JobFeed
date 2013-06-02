package feed.frontend.snippet

import net.liftweb.common.Full
import net.liftweb.http.S
import net.liftweb.http.SHtml
import feed.frontend.model.User

object Home {
  def render = {
    User.currentUser match {
      case Full(user) => User.asHtml(user)
      case _ => <span>{ "you are not logged in" }</span>
    }
  }
}