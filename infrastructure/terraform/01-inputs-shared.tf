variable "prefix" {}
variable "product" {}
variable "environment" {}
variable "location" {}
variable "builtFrom" {
  type        = string
  description = "Build pipeline"
}
variable "virtual_network_type" {
  description = "Network type: None / External / Internal"
  default     = "None"
}
variable "tenant_id" {
  description = "The Tenant ID for the principal we're giving permission to."
}
variable "sp_object_id" {
  description = "The Object ID for the principal we're giving permission to."
}
variable "secret_permissions" {
  description = "The permissions (list) for the creating principal accessing secrets."
  default = [
    "get",
    "set",
    "list",
    "delete"
  ]
}

module "ctags" {
  source      = "git::https://github.com/hmcts/terraform-module-common-tags.git?ref=master"
  environment = var.environment
  product     = var.product
  builtFrom   = var.builtFrom
}
locals {
  common_tags = local.common_tags
}