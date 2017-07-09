package sri.web.template.components.items

import sri.web.router.{RouterAwareComponentNoPS, WithRouter}
import sri.web.vdom.tagsPrefix_<._

import scala.scalajs.js
import scalajscss.{CSSStyleSheet, CSSStyleSheetRegistry}

class ItemsNav extends RouterAwareComponentNoPS {
  import ItemsNav._
  override def componentWillMount(): Unit = {
    CSSStyleSheetRegistry.addToDocument(styles)
  }

  def render() = {
    <.div(className = styles.container)(
      getStaticMenuItem("1"),
      getStaticMenuItem("2"),
      getStaticMenuItem("3"),
      getStaticMenuItem("4")
    )
  }

  def getStaticMenuItem(item: String) = {
    val isSelected = navigation.currentRoute.params.exists(
      o =>
        !js.isUndefined(o.asInstanceOf[js.Dynamic].id) && o
          .asInstanceOf[ItemDetailsScreen.Params]
          .id == item)
    <.div(
      className = if (isSelected) styles.buttonSelected else styles.button,
      onClick = (e: ReactEventH) => {
        navigation.navigateP[ItemDetailsScreen](
          params = new ItemDetailsScreen.Params {
            override val id: String = item
          })
      }
    )("Item " + item)

  }

}

object ItemsNav {

  object styles extends CSSStyleSheet {

    import dsl._

    import scalajscss.units._

    val container = style(display.flex,
                          flexDirection.column,
                          width := 150.px,
                          borderRight := "solid 1px",
                          borderColor := "#d8cbcb")

    val button = style(
      cursor.pointer,
//                       color.white,
      display.flex,
      justifyContent.center,
      alignItems.center,
      fontSize := 14.px,
      height := 44.px
    )

    val buttonSelected =
      styleExtend(button)(fontWeight.bold)

  }

  def apply() = WithRouter[ItemsNav]()

}
