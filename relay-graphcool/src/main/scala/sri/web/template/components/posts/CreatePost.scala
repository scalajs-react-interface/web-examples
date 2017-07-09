package sri.web.template.components.posts

import sri.core.{Component, CreateElement}
import sri.web.template.relay.mutations.CreatePostMutation
import sri.web.vdom.tagsPrefix_<._

import scalajscss.{CSSStyleSheet, CSSStyleSheetRegistry}

class CreatePost extends Component[CreatePost.Props, CreatePost.State] {
  import CreatePost._
  initialState(State())

  override def componentWillMount(): Unit = {
    CSSStyleSheetRegistry.addToDocument(styles)
  }
  def render() = {
    <.div(className = styles.container)(
      <.div(className = styles.form)(
        <.div(className = styles.inputGroup)(
          <.labelC("Image URL : "),
          <.input(className = styles.input,
                  value = state.url,
                  onChange = handleURlChange _)
        ),
        <.div(className = styles.inputGroup)(
          <.labelC("Description : "),
          <.input(className = styles.input,
                  value = state.description,
                  onChange = handleDescriptionChange _)
        ),
        <.div(className = styles.button, onClick = handleCreatePost _)(
          "Create Post")
      )
    )
  }

  def handleURlChange(e: ReactEventI) = {
    val value = e.target.value
    setState((state: State) => state.copy(url = value))
  }

  def handleDescriptionChange(e: ReactEventI) = {
    val value = e.target.value
    setState((state: State) => state.copy(description = value))
  }

  def handleCreatePost(e: ReactEventH) = {
    CreatePostMutation(description = state.description,
                       imageUrl = state.url,
                       viewerId = props.userId)
  }

}

object CreatePost {

  case class Props(userId: String)
  case class State(description: String = "", url: String = "")

  object styles extends CSSStyleSheet {

    import dsl._
    import scalajscss.units._

    val container = style(display.flex,
                          marginTop := 100.px,
                          justifyContent.center,
                          alignItems.center)

    val form =
      style(display.flex,
            flexDirection.column,
            alignItems.center,
            padding := 40.px,
            boxShadow := "0 2px 4px grey")

    val inputGroup = style(display.flex, marginTop := 20.px)

    val input = style(border := "none",
                      borderBottom := "1px solid",
                      background := "transparent",
                      padding := 3.px,
                      minWidth := 250.px,
                      borderColor := "#9898a5")

    val button = style(marginTop := 20.px,
                       padding := 10.px,
                       boxShadow := "0 2px 4px grey",
                       cursor.pointer)

  }

  def apply(id: String) = CreateElement[CreatePost](Props(id))
}
