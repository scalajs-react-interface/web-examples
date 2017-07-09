package sri.web.template.components

import sri.web.router.RouterScreenComponentNoPSLS
import sri.web.vdom.tagsPrefix_<._

import scalajscss.{CSSStyleSheet, CSSStyleSheetRegistry}

class AboutScreen extends RouterScreenComponentNoPSLS {

  import AboutScreen._

  override def componentWillMount(): Unit = {
    CSSStyleSheetRegistry.addToDocument(styles)
  }

  def render() = {
    <.div(className = styles.container)(<.h2C("Built with Sri :) "))
  }
}

object AboutScreen {

  object styles extends CSSStyleSheet {

    import dsl._

    val container = style(display.flex,
                          flex := "1",
                          alignItems.center,
                          justifyContent.center)

  }

}
