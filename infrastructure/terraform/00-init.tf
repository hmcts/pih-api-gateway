terraform {
  backend "azurerm" {
  }

  required_version = ">= 0.15.4"
  required_providers {
    azurerm = {
      source  = "hashicorp/azurerm"
      version = ">= 2.7.0"
    }
  }
}


provider "azurerm" {
  features {}
}
