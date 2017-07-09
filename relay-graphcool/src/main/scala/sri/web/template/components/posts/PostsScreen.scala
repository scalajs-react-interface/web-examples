package sri.web.template.components.posts

import org.scalajs.dom
import sri.relay.{QueryRenderer, ReadyState}
import sri.relay.macros.graphql._
import sri.relay.runtime.CacheConfig
import sri.web.router.RouterScreenComponentNoPSLS
import sri.web.template.relay
import sri.web.vdom.tagsPrefix_<._
import sri.universal._
import sri.web.template.components.LoadingIndicator

import scala.scalajs.js

class PostsScreen extends RouterScreenComponentNoPSLS {

  def render() = {
    QueryRenderer[js.Object](
      cacheConfig = CacheConfig(force = false),
      environment = relay.environment,
      query = graphql""" query appQuery {
                            viewer {
                              ...PostsViewer
                            }
                          } """,
      render =
        (x: ReadyState[js.Object], y: js.UndefOr[ReadyState[js.Object]]) => {
          dom.window.console.log(x)
          if (x.props.isDefinedAndNotNull)
            PostList(x.props.get)
          else if (x.error.isDefinedAndNotNull) <.h3C("Service Error!")
          else LoadingIndicator()
        }
    )
  }

}
