package sri.web.template.components

import sri.web.router.RouterModuleConfig

package object items {

  object Routes extends RouterModuleConfig("items") {

    registerScreen[ItemsScreen]("/", title = "Items")

    registerDynamicScreen[ItemDetailsScreen](":id", title = "Item Details")
  }
}
