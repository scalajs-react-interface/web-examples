package sri.web.template.styles

import scalacss.Defaults._
import scalacss.internal.mutable.GlobalRegistry

object AppStyles {

  def load() = {
    GlobalRegistry.register(GlobalStyle)
    GlobalRegistry.addToDocumentOnRegistration()
  }
}
