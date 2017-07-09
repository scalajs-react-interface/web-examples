package sri.web.template

import org.scalajs.dom
import sri.web.ReactDOM

import scala.scalajs.js
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
