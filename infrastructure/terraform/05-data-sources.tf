data "azurerm_key_vault" "infra_key_vault" {
  name                = "pih-shared-kv-${var.environment}"
  resource_group_name = "pih-sharedservices-${var.environment}-rg"
}
