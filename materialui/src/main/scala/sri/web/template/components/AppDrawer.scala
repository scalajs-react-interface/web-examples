package sri.web.template.components

import sri.materialui._
import sri.web.router.{RouterAwareComponentP, RouterScreenClass, WithRouter}
import sri.web.template.components.items.ItemsScreen
import sri.web.vdom.tags.ReactEventH
import sri.web.vdom.tagsPrefix_<._

import scala.reflect.ClassTag
import scala.scalajs.js.ConstructorTag
import scalajscss.{CSSStyleSheet, CSSStyleSheetRegistry}

class AppDrawer extends RouterAwareComponentP[AppDrawer.Props] {
  import AppDrawer._

  override def componentWillMount(): Unit = {
    CSSStyleSheetRegistry.addToDocument(styles)
  }

  def render() = {
    <.div(
      className = styles.drawer
    )(
      <.div(
        className = styles.drawerLogo
      )(
        MuiTypography(color = MuiTypographyColor.INHERIT,
                      `type` = MuiTypographyType.DISPLAY1)("Sri")
      ),
      MuiListC(
        getStaticMenuItem[HomeScreen]("Home"),
        MuiDivider(),
        getStaticMenuItem[ItemsScreen]("Items", module = true),
        MuiDivider(),
        getStaticMenuItem[AboutScreen]("About")
      )
    )
  }

  def getStaticMenuItem[C <: RouterScreenClass { type Params = Null }: ConstructorTag](
      text: String,
      module: Boolean = false)(implicit ctag: ClassTag[C]) = {
    val currentKey = navigation.currentRoute.screenKey.toString
    val pageKey = sri.web.router.getRouterScreenKey[C].toString

    val isSelected =
      if (!module) pageKey == currentKey
      else {
        (pageKey == currentKey) || (pageKey
          .split("\\.")
          .init
          .mkString(".") == currentKey.split("\\.").init.mkString("."))
      }
    MuiListItem(
      className =
        if (isSelected) styles.drawerMenuItemSelected
        else styles.drawerMenuItem,
      onClick = (e: ReactEventH) => {
        if (!isSelected) navigation.navigate[C]()
        props.closeDrawer()
      }
    )(
      text
    )
  }

}

object AppDrawer {

  case class Props(closeDrawer: () => _)

  object styles extends CSSStyleSheet {

    import dsl._

    import scalajscss.units._

    val drawer = style(width := 200.px)

    val drawerLogo =
      style(backgroundColor := "#3f51b5",
            display.flex,
            justifyContent.center,
            alignItems.center,
            color.white,
            height := 150.px)

    val drawerMenuItem = style(
      cursor.pointer
    )

    val drawerMenuItemSelected = style(
      color.black,
      fontWeight.bold,
      backgroundColor := "rgba(185, 185, 185, 0.12)")

  }

  def apply(closeDrawer: () => _) =
    WithRouter[AppDrawer](Props(closeDrawer))
}
