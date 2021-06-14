resource "azurerm_api_management_api_policy" "pip_apim_api_policy" {
  api_name            = azurerm_api_management_api.pip_apim_api.name
  api_management_name = azurerm_api_management.pip_apim.name
  resource_group_name = azurerm_resource_group.pip_apim_rg.name
  xml_content         = templatefile("../template/api-policy.tmpl", { enableMockHeader = "${var.enable_mock_header_string}" })
}
