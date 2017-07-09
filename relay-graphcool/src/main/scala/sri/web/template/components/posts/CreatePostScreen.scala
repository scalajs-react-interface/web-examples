package sri.web.template.components.posts

import org.scalajs.dom
import sri.relay.{QueryRenderer, ReadyState}
import sri.relay.runtime.CacheConfig
import sri.web.router.RouterScreenComponentNoPSLS
import sri.web.template.components.LoadingIndicator
import sri.relay._
import sri.relay.macros.graphql._
import sri.web.template.relay
import sri.web.vdom.tagsPrefix_<._
import sri.universal._
import sri.web.template.models.Viewer

import scala.scalajs.js
import scalajscss.{CSSStyleSheet, CSSStyleSheetRegistry}

class CreatePostScreen extends RouterScreenComponentNoPSLS {
  import CreatePostScreen._

  def render() = {
    //Note : iam using QueryRenderer here just to get viewerId , this can be achieved by many other ways depending on your structure
    QueryRenderer[js.Object](
      environment = relay.environment,
      query = graphql""" query createPostScreenQuery {
                            viewer {
                             id
                            }
                          } """,
      render =
        (x: ReadyState[js.Object], y: js.UndefOr[ReadyState[js.Object]]) => {
          dom.window.console.log(x)
          if (x.props.isDefinedAndNotNull)
            CreatePost(x.props.get.asInstanceOf[js.Dynamic].viewer.id.toString)
          else if (x.error.isDefinedAndNotNull) <.h3C("Service Error!")
          else LoadingIndicator()
        }
    )
  }
}

object CreatePostScreen {}
