resource "azurerm_api_management_product_api" "pih_apim_product_api" {
  api_name            = azurerm_api_management_api.pih_apim_api.name
  product_id          = azurerm_api_management_product.pih_apim_product.product_id
  api_management_name = azurerm_api_management.pih_apim.name
  resource_group_name = azurerm_resource_group.pih_apim_rg.name
}
