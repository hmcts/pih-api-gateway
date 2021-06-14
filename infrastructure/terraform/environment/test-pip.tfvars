apim_sku_name                      = "Developer"
apim_sku_capacity                  = "1"
publisher_name                     = "HMCTS PIP"
publisher_email                    = "pip-team@HMCTS.NET"
protocols                          = ["http", "https"]
open_api_spec_content_format       = "swagger-link-json"
open_api_spec_content_value        = "https://raw.githubusercontent.com/hmcts/reform-api-docs/master/docs/specs/pub-hub-gateway-api.json"
open_api_health_spec_content_value = "https://raw.githubusercontent.com/hmcts/reform-api-docs/master/docs/specs/pub-hub-gateway-api-health.json"
revision                           = "15"
service_url                        = "https://www.hmcts.com/request-hearings/request-listings"
tags = {
  "businessarea" : "cross-cutting",
  "application" : "hearing-management-interface",
  "environment" : "testing"
}
enable_mock_header_string = "<set-header name=\"_EnableMocks\" exists-action=\"override\"><value>true</value></set-header>"
virtual_network_type      = "Internal"
