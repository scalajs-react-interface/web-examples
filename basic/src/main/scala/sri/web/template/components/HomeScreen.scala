package sri.web.template.components

import sri.web.router.RouterScreenComponentNoPSLS
import sri.web.vdom.tagsPrefix_<._

import scala.scalajs.js.{UndefOr => U}
import scalajscss.{CSSStyleSheet, CSSStyleSheetRegistry}

class HomeScreen extends RouterScreenComponentNoPSLS {
  import HomeScreen._

  override def componentWillMount(): Unit = {
    CSSStyleSheetRegistry.addToDocument(styles)
  }

  def render() = {
    <.div(className = styles.container)(
      <.h2C("Welcome to Sri Web")
    )
  }

}

object HomeScreen {

  object styles extends CSSStyleSheet {

    import dsl._

    val container =
      style(display.flex,
            alignItems.center,
            justifyContent.center,
            flex := "1")
  }

}
