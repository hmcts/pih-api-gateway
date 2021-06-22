resource "azurerm_api_management_product_api" "pip_apim_product_api" {
  api_name            = azurerm_api_management_api.pip_apim_api.name
  product_id          = azurerm_api_management_product.pip_apim_product.product_id
  api_management_name = azurerm_api_management.pip_apim.name
  resource_group_name = azurerm_resource_group.pip_apim_rg.name
}
