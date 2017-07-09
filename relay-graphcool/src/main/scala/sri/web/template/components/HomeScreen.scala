package sri.web.template.components

import org.scalajs.dom
import sri.core._
import sri.web.router.RouterScreenComponentS
import sri.web.vdom.tagsPrefix_<._

import scalajscss.{CSSStyleSheet, CSSStyleSheetRegistry}

class HomeScreen extends RouterScreenComponentS[HomeScreen.State] {
  import HomeScreen._

  initialState(State("sri"))

  override def componentWillMount(): Unit = {
    CSSStyleSheetRegistry.addToDocument(styles)
  }

  def render() = {
    <.div(className = styles.container)(
      <.h2C("Welcome to Sri Relay Web")
    )
  }

  def onTextChange(e: ReactEventI) = {
    val value = e.target.value
    dom.window.console.log(value)
    setState((state: State) => state.copy(text = value))
  }
}

object HomeScreen {

  case class State(text: String)

  object styles extends CSSStyleSheet {

    import dsl._

    val container =
      style(display.flex,
            alignItems.center,
            justifyContent.center,
            flex := "1")
  }

}
