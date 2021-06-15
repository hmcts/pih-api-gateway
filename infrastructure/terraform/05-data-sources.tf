data "azurerm_key_vault" "infra_key_vault" {
  name                = "pip-shared-kv-${var.environment}"
  resource_group_name = "pipshared${var.environment}rg"
}
