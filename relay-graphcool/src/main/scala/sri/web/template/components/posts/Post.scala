package sri.web.template.components.posts

import org.scalajs.dom
import sri.relay.{CreateRelayElement, Relay, RelayFragmentComponentP}
import sri.relay.macros.graphql._
import sri.web.template.models.{PostEdge, Viewer}
import sri.web.vdom.styles.InlineStyleSheetWeb
import sri.web.vdom.tagsPrefix_<._

import scala.scalajs.js
import scalajscss.{CSSStyleSheet, CSSStyleSheetRegistry}

class Post extends RelayFragmentComponentP[Post.Props] {

  import Post._

  override def componentWillMount(): Unit = {
    CSSStyleSheetRegistry.addToDocument(styles)
  }

  def render() = {
    dom.window.console.log(props)
    <.div(className = styles.container)(
      <.img(className = styles.image, src = props.post.imageUrl),
      <.div(className = styles.imageInfo)(props.post.description)
    )
  }

}

object Post {

  class Props(val viewer: Viewer, val post: sri.web.template.models.Post)
      extends js.Object

  object styles extends CSSStyleSheet {

    import dsl._
    import scalajscss.units._

    val container = style(display.flex,
                          marginLeft := 20.px,
                          flexDirection.column,
                          width := 150.px)

    val image =
      style(width := 150.px, height := 150.px)

    val imageInfo = style(paddingTop := 10.px, fontWeight.bold)

  }

  val container =
    Relay.createFragmentContainer[Post](
      js.Dictionary(
        "viewer" -> graphql"""
       fragment PostViewer on Viewer {
        id
       }
    """,
        "post" -> graphql"""
      fragment PostData on Post {
         id
         imageUrl
         description
      }
    """
      ))

  def apply(props: Props, key: String) =
    CreateRelayElement[Post](container, props, key = key)

}
