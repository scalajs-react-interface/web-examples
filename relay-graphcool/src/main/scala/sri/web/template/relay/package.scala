package sri.web.template

import org.scalajs.dom
import org.scalajs.dom.experimental.{HttpMethod, RequestInit}
import org.scalajs.dom.raw.{Blob, File}
import sri.relay.runtime.network.{FetchFunction, RelayNetwork}
import sri.relay.runtime.store.{
  RelayEnvironment,
  RelayEnvironmentConfig,
  RelayRecordSource,
  RelayStore
}
import sri.relay.runtime.{CacheConfig, ConcreteBatch, QueryPayload}

import scala.scalajs.js
import scala.scalajs.js.{JSON, |}

package object relay {

  val fetchFunction: FetchFunction = (
      operation: ConcreteBatch /*operation*/,
      variables: js.Object /*variables*/,
      cacheCOnfig: CacheConfig /*cacheConfig*/,
      uploadables: js.Dictionary[File | Blob] /*uploadables*/ ) => {

    val respFunc: js.Function1[dom.experimental.Response,
                               QueryPayload | js.Thenable[QueryPayload]] =
      (response: dom.experimental.Response) =>
        response.json().asInstanceOf[QueryPayload | js.Thenable[QueryPayload]]
    dom.experimental.Fetch
      .fetch(
        ProjectGlobals.SERVICE_URL,
        RequestInit(
          method = HttpMethod.POST,
          headers = js.Dictionary("Access-Control-Allow-Origin" -> "*",
                                  "Accept" -> "application/json",
                                  "Content-Type" -> "application/json"),
          body = JSON.stringify(
            js.Dynamic.literal(query = operation.text, variables = variables))
        )
      )
      .`then`(respFunc)

  }

  val network = RelayNetwork.create(fetchFunction)

  val environment = new RelayEnvironment(
    RelayEnvironmentConfig(store = new RelayStore(new RelayRecordSource()),
                           network = network))
}
