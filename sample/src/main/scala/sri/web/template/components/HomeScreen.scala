package sri.web.template.components

import org.scalajs.dom
import sri.core._
import sri.web.template.styles.GlobalStyle
import sri.web.styledtags._
import sri.scalacss.Defaults._

import scala.scalajs.js.annotation.ScalaJSDefined

@ScalaJSDefined
class HomeScreen extends ComponentS[HomeScreen.State] {
  import HomeScreen._

  initialState(State("sri"))

  def render(): ReactElement = {
    div(className := GlobalStyle.flexOneAndCenter)(
      span(className := GlobalStyle.bigText)("Home Screen"),
      input(value := state.text, onChange := onTextChange _)
    )
  }

  def onTextChange(e: ReactEventI):Unit = {
    val value = e.target.value
    dom.window.console.log(value)
    setState((state: State) => state.copy(text = value))
  }
}

object HomeScreen {

  case class State(text: String)

  def apply():ReactElement = CreateElementNoProps[HomeScreen]()

}
