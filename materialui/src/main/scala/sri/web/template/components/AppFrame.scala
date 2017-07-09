package sri.web.template.components

import sri.core.{Component, CreateElementWithChildren, ReactNode}
import sri.materialui._
import sri.materialui.svgicons.MuiMenuIcon
import sri.web.vdom.tags.ReactEventH
import sri.web.vdom.tagsPrefix_<._

import scala.scalajs.js.JSConverters.genTravConvertible2JSRichGenTrav
import scalajscss.{CSSStyleSheet, CSSStyleSheetRegistry}

class AppFrame extends Component[AppFrame.Props, AppFrame.State] {
  import AppFrame._

  initialState(State())

  override def componentWillMount(): Unit = {
    CSSStyleSheetRegistry.addToDocument(styles)
  }

  def render() = {
    <.div(
      className = styles.container
    )(
      MuiAppBarC(
        MuiToolbarC(
          MuiIconButton(color = MuiIconButtonColor.CONTRAST,
                        onClick = (e: ReactEventH) => toggleDrawer())(
            MuiMenuIcon()
          ),
          MuiTypography(className = styles.title,
                        noWrap = true,
                        color = MuiTypographyColor.INHERIT,
                        `type` = MuiTypographyType.TITLE)(
            props.title
          )
        )
      ),
      <.div(
        className = styles.content
      )(
        children
      ),
      MuiDrawer(open = state.drawerOpen,
                onRequestClose = (e: ReactEventH) => closeDrawer())(
        AppDrawer(closeDrawer _)
      )
    )
  }

  def toggleDrawer() = {
    setState((state: State) => state.copy(drawerOpen = !state.drawerOpen))
  }

  def closeDrawer() = {
    setState((state: State) => state.copy(drawerOpen = false))

  }
}

object AppFrame {

  case class Props(title: String)

  case class State(drawerOpen: Boolean = false)

  object styles extends CSSStyleSheet {
    import dsl._

    import scalajscss.units._

    val container = style(display.flex,
                          flexDirection.column,
                          alignItems.stretch,
                          minHeight := 100.vh,
                          minWidth := 100.%%)

    val title = style(marginLeft := 24.px, flex := "0 1 auto")

    val content =
      style(display.flex, flex := "1")

  }

  def apply(title: String)(children: ReactNode*) =
    CreateElementWithChildren[AppFrame](props = Props(title),
                                        children = children.toJSArray)
}
