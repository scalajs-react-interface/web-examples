package sri.web.template.components.items

import sri.web.router.RouterScreenComponentP

import scala.scalajs.js

class ItemDetailsScreen
    extends RouterScreenComponentP[ItemDetailsScreen.Params] {

  def render() = {
    val id = params.id
    ItemsFrame(s"Item $id Details")
  }

}

object ItemDetailsScreen {

  trait Params extends js.Object {
    val id: String
  }
}
