package sri.web.template.components

import sri.web.vdom.tagsPrefix_<._

object LoadingIndicator {

  def apply() =
    <.h3(className = AppFrame.styles.loadingIndicator)("Loading....")

}
