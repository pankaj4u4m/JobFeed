package bootstrap.liftweb

import net.liftweb._
import util._
import Helpers._
import common._
import http._
import sitemap._
import Loc._
import net.liftweb.http.js.jquery._
import net.liftmodules.FoBo
import net.liftweb.mapper.DB
import net.liftweb.mapper.DefaultConnectionIdentifier
import code.model.User
import net.liftweb.mapper.Schemifier

/**
 * A class that's instantiated early and run.  It allows the application
 * to modify lift's environment
 */
class Boot extends Loggable {
  def boot {
    DB.defineConnectionManager(DefaultConnectionIdentifier, MyDbVendor)

    // where to search snippet
    LiftRules.addToPackages("code")

    // Build SiteMap
    val entries = List(
      Menu.i("Index") / "index", // the simple way to declare a menu
      Menu.i("Home") / "home",
      Menu.i("Resume") / "resume"
    // more complex because this menu allows anything in the
    // /static path to be visible
    //Menu(Loc("Static", Link(List("static"), true, "/static/index"),
    //"Static Content"))
    ) ::: User.sitemap

    // set the sitemap.  Note if you don't want access control for
    // each page, just comment this line out.
    LiftRules.setSiteMap(SiteMap(entries: _*))

    //Show the spinny image when an Ajax call starts
    LiftRules.ajaxStart =
      Full(() => LiftRules.jsArtifacts.show("ajax-loader").cmd)

    // Make the spinny image go away when it ends
    LiftRules.ajaxEnd =
      Full(() => LiftRules.jsArtifacts.hide("ajax-loader").cmd)

    // Force the request to be UTF-8
    LiftRules.early.append(_.setCharacterEncoding("UTF-8"))

    // Use HTML5 for rendering
    LiftRules.htmlProperties.default.set((r: Req) =>
      new Html5Properties(r.userAgent))

    Schemifier.schemify(true, Schemifier.infoF _, User)

    //MapperRules.createForeignKeys_? = (_) => true

    LiftRules.statefulRewrite.append {
      case RewriteRequest(
        ParsePath("index" :: Nil, _, _, _), _, _) if (User.loggedIn_?) =>
        RewriteResponse("home" :: Nil)
      case RewriteRequest(
        ParsePath("home" :: Nil, _, _, _), _, _) if (!User.loggedIn_?) =>
        RewriteResponse("login" :: Nil)
    }

    //    //Init the jQuery module, see http://liftweb.net/jquery for more information.
    //    LiftRules.jsArtifacts = JQueryArtifacts
    //    JQueryModule.InitParam.JQuery = JQueryModule.JQuery182
    //    JQueryModule.init()

    FoBo.InitParam.JQuery = FoBo.JQuery182
    FoBo.InitParam.ToolKit = FoBo.Bootstrap222
    FoBo.InitParam.ToolKit = FoBo.FontAwesome300 // replaces the icon set of bootstrap
    FoBo.InitParam.ToolKit = FoBo.PrettifyJun2011
    FoBo.InitParam.ToolKit = FoBo.JQueryMobile110
    FoBo.InitParam.ToolKit = FoBo.DataTables190
    FoBo.InitParam.ToolKit = FoBo.Knockout200
    FoBo.init()

  }
}
