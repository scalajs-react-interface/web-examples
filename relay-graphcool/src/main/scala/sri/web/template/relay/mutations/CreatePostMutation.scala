package sri.web.template.relay

package mutations

import org.scalajs.dom
import sri.relay._
import sri.relay.macros.graphql._
import sri.relay.runtime.PayloadError
import sri.relay.runtime.handlers.RelayConnectionHandler
import sri.relay.runtime.mutation.{CommitMutationConfig, RelayCommitMutation}
import sri.relay.runtime.store.RecordSourceSelectorProxy
import sri.web.template.models.CreatePostInput

import scala.scalajs.js

object CreatePostMutation {

  val mutation = graphql"""
        mutation CreatePostMutation($$input: CreatePostInput!) {
          createPost(input: $$input) {
            post {
              id
              description
              imageUrl
            }
          }
        }
    """

  var tempID = 0

  def apply(description: String,
            imageUrl: String,
            viewerId: String,
            callback: Function0[Unit] = null) = {

    val variables = js.Dynamic.literal(
      input = CreatePostInput(description = description,
                              imageUrl = imageUrl,
                              clientMutationId = ""))

    RelayCommitMutation(
      environment,
      CommitMutationConfig(
        mutation = mutation,
        variables = variables,
        onError = (e: js.Error) => {
          dom.window.console.log("Error in create post", e)
        },
        onCompleted =
          (resp: js.Any, erros: js.UndefOr[js.Array[PayloadError]]) => {
            dom.window.console.log("Create Post Success", resp)
            if (callback != null) callback()
          },
        optimisticUpdater = (proxyStore: RecordSourceSelectorProxy) => {
          val id = "client:newPost:" + tempID
          tempID += 1
          val newPost = proxyStore.create(id, "Post")
          newPost.setValue(id, "id")
          newPost.setValue(description, "description")
          newPost.setValue(imageUrl, "imageUrl")

          val viewerProxy = proxyStore.get(viewerId).getOrElse(null)
          val connection =
            RelayConnectionHandler.getConnection(viewerProxy,
                                                 "ListPage_allPosts")
          if (connection.isDefined) {
            RelayConnectionHandler.insertEdgeAfter(connection.get, newPost)
          }
        },
        updater = (proxyStore: RecordSourceSelectorProxy) => {

          val createPostField = proxyStore.getRootField("createPost")

          val newPost = createPostField.get.getLinkedRecord("post").get

          val viewerProxy = proxyStore.get(viewerId).getOrElse(null)
          val connection =
            RelayConnectionHandler.getConnection(viewerProxy,
                                                 "ListPage_allPosts")
          if (connection.isDefined) {
            RelayConnectionHandler.insertEdgeAfter(connection.get, newPost)
          }
        }
      )
    )
  }
}
