Param( [string]$tenantId = "" , [string]$pipGatewayId = "" )

$displayNameApi = "hmi-pip-client-np"
$bodyApi = '{
    "signInAudience" : "AzureADandPersonalMicrosoftAccount", 
    "groupMembershipClaims": "None"
}' | ConvertTo-Json | ConvertFrom-Json
$userAccessScopeApi = '{
    "lang": null,
    "origin": "Application",        
    "adminConsentDescription": "Allow access to the PIP",
    "adminConsentDisplayName": "pip-api-gateway-access",
    "id": "--- replaced in scripts ---",
    "isEnabled": true,
    "type": "User",
    "userConsentDescription": "Allow access to PIP Gateway access_as_user",
    "userConsentDisplayName": "Allow access to PIP Gateway",
    "value": "access_as_user"
}' | ConvertTo-Json | ConvertFrom-Json
 
##################################
### testParams
##################################
 
function testParams {
 
    if (!$tenantId) 
    { 
        Write-Host "tenantId is null"
        exit 1
    }
    if (!$pipGatewayId) 
    { 
        Write-Host "hmiGatewayId is null"
        exit 1
    }
}
 
testParams
 
Write-Host "Begin PIP Client Azure App Registration"
 
##################################
### Create Azure App Registration
##################################

$keyVaultName = "beotech-apim-kv-dev"

$myApiAppRegistration = az ad app create `
    --display-name $displayNameApi `
    --available-to-other-tenants false `
    --oauth2-allow-implicit-flow  false
 
$myApiAppRegistrationResult = ($myApiAppRegistration | ConvertFrom-Json)
$myApiAppRegistrationResultAppId = $myApiAppRegistrationResult.appId

$identifierUrlApi = "api://" + $myApiAppRegistrationResultAppId
az ad app update --id $myApiAppRegistrationResultAppId --identifier-uris $identifierUrlApi
Write-Host " - Created API $displayNameApi with myApiAppRegistrationResultAppId: $myApiAppRegistrationResultAppId"
 
##################################
### Add Roles to App Registration 
##################################
az ad app update --id $myApiAppRegistrationResultAppId --app-roles @pip-gateway-role.json
Write-Host " - Roles added to App Registration: $myApiAppRegistrationResultAppId"
 
##################################
###  Add API Permission
##################################
$pipGateWayApp = az ad app list --app-id $pipGatewayId | ConvertFrom-Json
$roleId = $pipGateWayApp[0].appRoles[0].id

az keyvault secret set --vault-name $keyVaultName --name "$displayNameApi-permission" --value $roleId
Write-Host "Added Permission id: '$roleId' to Key Vault"

$pipgatewayResourceAccess = "[
    {
        `"resourceAppId`": `"" + $pipGatewayId   + "`",
        `"resourceAccess`": [
            {
                `"id`": `"" + $roleId + "`",
                `"type`": `"Role`"
            }
        ]
    }
]" | ConvertTo-Json | ConvertFrom-Json
$pipgatewayResourceAccess | Out-File -FilePath .\pipgatewayResourceAccess.json

az  ad app update  --id $myApiAppRegistrationResultAppId --required-resource-accesses `@pipgatewayResourceAccess.json

# 5. Create Secret for this client
$clientSecret = New-Guid
az ad app update --id $myApiAppRegistrationResultAppId --password $clientSecret

# 6. Add generated secret to Key Vault
az keyvault secret set --vault-name $keyVaultName --name "$displayNameApi-secret" --value $clientSecret
az keyvault secret set --vault-name $keyVaultName --name "$displayNameApi-client" --value $myApiAppRegistrationResultAppId

##################################
###  Create a ServicePrincipal for the API App Registration
##################################
 
az ad sp create --id $myApiAppRegistrationResultAppId | Out-String | ConvertFrom-Json
Write-Host " - Created Service Principal for API App registration"

az ad app permission admin-consent --id $myApiAppRegistrationResultAppId
    
return $myApiAppRegistrationResultAppId