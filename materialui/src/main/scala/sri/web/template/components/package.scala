package sri.web.template

import sri.core.ReactElement
import sri.web.router
import sri.web.router.{
  History,
  HistoryFactory,
  RouteNotFound,
  Router,
  RouterConfig,
  RouterCtrl
}

package object components {

  object AppRoutes extends RouterConfig {
    override val history: History = HistoryFactory.browserHistory()

    registerScreen[HomeScreen]("/", title = "Home")

    registerScreen[AboutScreen]("about", title = "About")

    registerModule(items.Routes)

    override val notFound: RouteNotFound = RouteNotFound(
      router.getRouterScreenKey[HomeScreen])

    override def renderScene(navigation: RouterCtrl): ReactElement = {
      AppFrame(navigation.currentRoute.title)(super.renderScene(navigation))
    }
  }

  val root = Router(AppRoutes)

}
