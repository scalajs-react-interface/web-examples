package sri.web.template

import org.scalajs.dom
import sri.web.ReactDOM

import scalajscss.CSSStyleSheetRegistry
import scalajscss.plugins.autoprefixer.AutoPrefixer

object WebApp {

  def main(args: Array[String]): Unit = {
    CSSStyleSheetRegistry.setPlugins(AutoPrefixer())
    ReactDOM.render(
      components.root,
      dom.document.getElementById("app")
    )
  }

}
