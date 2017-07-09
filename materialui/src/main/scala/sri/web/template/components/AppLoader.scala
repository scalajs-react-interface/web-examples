package sri.web.template.components

import sri.core.{ComponentS, CreateElementNoPropsWithChildren, ReactNode}
import sri.materialui.{MuiAppBarC, MuiThemeProviderC}

import scala.scalajs.js.JSConverters.genTravConvertible2JSRichGenTrav

class AppLoader extends ComponentS[AppLoader.State] {

  import AppLoader._

  initialState(State())

  def render() = {
    MuiThemeProviderC(
      if (state.loading) MuiAppBarC()
      else root
    )
  }

  override def componentDidMount(): Unit = {
    setState((state: State) => state.copy(loading = false))
  }
}

object AppLoader {

  case class State(loading: Boolean = true)

  def apply(children: ReactNode*) =
    CreateElementNoPropsWithChildren[AppLoader](children = children.toJSArray)
}
