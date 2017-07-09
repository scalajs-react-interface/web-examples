package sri.web.template.components.posts

import org.scalajs.dom
import sri.core.ComponentNoPS
import sri.relay.macros.graphql._
import sri.relay._
import sri.web.template.models
import sri.web.template.models.Viewer
import sri.web.vdom.tagsPrefix_<._

import scala.scalajs.js
import scalajscss.{CSSStyleSheet, CSSStyleSheetRegistry}
import sri.universal._
class PostList extends RelayFragmentComponentP[PostList.Props] {

  import PostList._
  override def componentWillMount(): Unit = {
    CSSStyleSheetRegistry.addToDocument(styles)
  }

  def render() = {
    dom.window.console.log(props)
    <.div(className = styles.container)(
      props.viewer.allPosts.edges.map(edge => {
        Post(new Post.Props(props.viewer, edge.node), key = edge.node.id)
      })
    )
  }

}

object PostList {

  trait Props extends js.Object {
    val viewer: Viewer
  }

  object styles extends CSSStyleSheet {

    import dsl._
    import scalajscss.units._
    val container =
      style(display.flex,
            flex := "1",
            paddingTop := 64.px,
            alignItems.center,
            flexWrap.wrap,
            marginLeft := 40.px)

  }

  val container = Relay.createFragmentContainer[PostList](
    js.Dictionary("viewer" -> graphql""" fragment PostsViewer on Viewer {
                                ...PostViewer
                              allPosts(last: 100, orderBy: createdAt_DESC) @connection(key: "ListPage_allPosts", filters: []) {
                                edges {
                                  node {
                                   id
                                    ...PostData
                                  }
                                }
                              }
                          } """)
  )

  def apply(props: js.Any) =
    CreateRelayElement[PostList](container, props.asInstanceOf[Props])

}
