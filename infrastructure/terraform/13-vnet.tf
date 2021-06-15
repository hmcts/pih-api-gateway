data "azurerm_virtual_network" "pip_apim_vnet" {
  name                = "pip-sharedinfra-vnet-${var.environment}"
  resource_group_name = "pipshared${var.environment}rg"
}

data "azurerm_subnet" "pip_apim_subnet" {
  name                 = "pip-subnet-${var.environment}"
  virtual_network_name = data.azurerm_virtual_network.pip_apim_vnet.name
  resource_group_name  = data.azurerm_virtual_network.pip_apim_vnet.resource_group_name
}
