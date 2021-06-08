data "azurerm_virtual_network" "pih_apim_vnet" {
  name                = "pih-sharedinfra-vnet-${var.environment}"
  resource_group_name = "pih-sharedinfra-${var.environment}-rg"
}

data "azurerm_subnet" "pih_apim_subnet" {
  name                 = "pih-subnet-${var.environment}"
  virtual_network_name = data.azurerm_virtual_network.pih_apim_vnet.name
  resource_group_name  = data.azurerm_virtual_network.pih_apim_vnet.resource_group_name
}
