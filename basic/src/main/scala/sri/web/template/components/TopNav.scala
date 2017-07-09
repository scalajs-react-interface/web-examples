package sri.web.template.components

import sri.web.router.{RouterAwareComponentNoPS, RouterScreenClass, WithRouter}
import sri.web.template.components.items.ItemsScreen
import sri.web.vdom.tagsPrefix_<._

import scala.reflect.ClassTag
import scala.scalajs.js.ConstructorTag
import scalajscss.{CSSStyleSheet, CSSStyleSheetRegistry}

class TopNav extends RouterAwareComponentNoPS {

  import TopNav._

  override def componentWillMount(): Unit = {
    CSSStyleSheetRegistry.addToDocument(styles)
  }

  def render() = {
    <.div(className = styles.topNav)(
      getStaticMenuItem[HomeScreen]("Home"),
      getStaticMenuItem[ItemsScreen]("Items", module = true),
      getStaticMenuItem[AboutScreen]("About")
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
    <.div(className = if (isSelected) styles.buttonSelected else styles.button,
          onClick = (e: ReactEventH) => {
            navigation.navigate[C]()
          })(text)

  }
}

object TopNav {

  object styles extends CSSStyleSheet {
    import dsl._

    import scalajscss.units._

    val topNav =
      style(display.flex,
            zIndex := 100,
            width := 100.%%,
            paddingLeft := 20.px,
            position.fixed,
            alignItems.center,
            height := 64.px,
            backgroundColor := "#1976D2")

    val button = style(cursor.pointer,
                       color.white,
                       display.flex,
                       alignItems.center,
                       fontSize := 18.px,
                       paddingLeft := 13.px,
                       paddingRight := 13.px,
                       height := 64.px,
                       marginLeft := 20.px)

    val buttonSelected = styleExtend(button)(backgroundColor := "#dc5c1d")
  }

  def apply() = WithRouter[TopNav]()
}
