Param( [string]$tenantId = "", [string]$secretForWebApp = "" )
 
function testParams() {
 
    if (!$tenantId) 
    { 
        Write-Host "tenantId is null"
        exit 1
    }
 
    if (!$secretForWebApp) 
    { 
        Write-Host "Application Id is not defined, creating one"
        $secretForWebApp = New-Guid
    }
    return $secretForWebApp
}
 
function testSubscription {
    $account = az account show | ConvertFrom-Json
    $accountTenantId = $account.tenantId
    if ($accountTenantId -ne $tenantId) 
    { 
        Write-Host "$accountTenantId not possible, change account"
        exit 1
    }
    $accountName = $account.name
    Write-Host "tenant: $accountName can update"
}
 
$secretForWebApp = testParams
testSubscription
 
Write-Host "tenantId $tenantId"
Write-Host "secretForWebApp $secretForWebApp"
Write-Host "-----------"
Write-Host (az version)
Write-Host "-----------"

# Create API App Registration
$createdAppRegAppIdApi = &".\pip_gateway_app_registration.ps1" $tenantId | select -Last 1
Write-Host "Created Backend App registraion: $createdAppRegAppIdApi"

$createdCftAppRegAppIdApi = &".\hmi_client_app_registration.ps1" $tenantId $createdAppRegAppIdApi| select -Last 1
Write-Host "Created Client App registraion: $createdCftAppRegAppIdApi"