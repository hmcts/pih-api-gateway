resource "azurerm_api_management_api_diagnostic" "pih_apim_diag" {
  resource_group_name      = azurerm_resource_group.pih_apim_rg.name
  api_management_name      = azurerm_api_management.pih_apim.name
  api_name                 = azurerm_api_management_api.pih_apim_api.name
  api_management_logger_id = azurerm_api_management_logger.pih_apim_logger.id
  identifier               = "applicationinsights"

  always_log_errors         = true
  log_client_ip             = true
  verbosity                 = "information"
  http_correlation_protocol = "W3C"

  frontend_request {
    body_bytes = 32
    headers_to_log = [
      "content-type",
      "accept",
      "origin",
    ]
  }

  frontend_response {
    body_bytes = 32
    headers_to_log = [
      "content-type",
      "content-length",
      "origin",
    ]
  }

  backend_request {
    body_bytes = 32
    headers_to_log = [
      "content-type",
      "accept",
      "origin",
    ]
  }

  backend_response {
    body_bytes = 32
    headers_to_log = [
      "content-type",
      "content-length",
      "origin",
    ]
  }
}
