resource "azurerm_api_management_product" "pip_apim_product" {
  product_id            = "${var.prefix}-${var.product}-product-${var.environment}"
  resource_group_name   = azurerm_resource_group.pip_apim_rg.name
  api_management_name   = azurerm_api_management.pip_apim.name
  display_name          = "pip-apim-product"
  subscription_required = false
  approval_required     = false
  published             = true
  description           = "Products let you group APIs, define terms of use, and runtime policies."
}
