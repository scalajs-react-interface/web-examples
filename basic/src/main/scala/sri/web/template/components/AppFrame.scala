package sri.web.template.components

import sri.core.{
  ComponentNoPS,
  CreateElementNoPropsWithChildren,
  ReactElementNode
}
import sri.universal._
import sri.web.vdom.tagsPrefix_<._

import scala.scalajs.js.JSConverters.genTravConvertible2JSRichGenTrav
import scalajscss.{CSSStyleSheet, CSSStyleSheetRegistry}

class AppFrame extends ComponentNoPS {
  import AppFrame._

  override def componentWillMount(): Unit = {
    CSSStyleSheetRegistry.addToDocument(styles)
  }

  def render() = {
    <.div(className = styles.container)(
      TopNav(),
      children
    )
  }

}

object AppFrame {

  object styles extends CSSStyleSheet {
    import dsl._

    import scalajscss.units._
    val container = style(display.flex,
                          flexDirection.column,
                          width := 100.%%,
                          height := 100.vh)

  }

  def apply(children: ReactElementNode*) =
    CreateElementNoPropsWithChildren[AppFrame](children = children.toJSArray)

}
