apim_sku_name                      = "Developer"
apim_sku_capacity                  = "1"
publisher_name                     = "HMCTS PIP"
publisher_email                    = "pip-team@HMCTS.NET"
protocols                          = ["http", "https"]
open_api_spec_content_format       = "openapi-link"
open_api_spec_content_value        = "https://raw.githubusercontent.com/hmcts/reform-api-docs/master/docs/specs/pip-gateway-api.json"
open_api_health_spec_content_value = "https://raw.githubusercontent.com/hmcts/reform-api-docs/master/docs/specs/pip-gateway-api-health.json"
revision                           = "1"
service_url                        = ""
tags = {
  "businessarea" : "cross-cutting",
  "application" : "hearing-management-interface",
  "environment" : "sandbox"
}
enable_mock_header_string = "<set-header name=\"_EnableMocks\" exists-action=\"override\"><value>true</value></set-header>"
virtual_network_type      = "None"

