## How to use

1. Import module
2. Add to dependencies like this: ``` compile project(":azureadb2cwebviewoauth2") ``` and sync
3. Add these permissions to manifest:
```xml
 <uses-permission android:name="android.permission.INTERNET" />
 <uses-permission android:name="android.permission.ACCESS_NETWORK_STATE" />
```
4. Create your view:

```xml
<com.jdtorregrosas.azureadb2cwebviewoauth2.AzureAdB2CWebView
  android:id="@+id/webview_login"
  android:layout_width="fill_parent"
  android:layout_height="fill_parent"
  azureb2c:tenant="@string/b2c_tenant"
  azureb2c:signInPolicy="@string/b2c_policy"
  azureb2c:redirectUri="@string/b2c_redirect_uri"
  azureb2c:clientId="@string/b2c_client_id"
  />
```
5. Implement events:

```java
AzureAdB2CWebView webView = (AzureAdB2CWebView) findViewById(R.id.webview_login);
 webView.setUp();
 webView.setOnLoginFailed(new AzureAdB2CWebView.OnLoginFailedListener() {
     @Override
     public void onLoginFailed() {

     }
 });
 webView.setOnLoginSuccess(new AzureAdB2CWebView.OnLoginSuccessListener() {
     @Override
     public void onLoginSuccess(String token) {

     }
 });
 webView.setOnPageStarted(new AzureAdB2CWebView.OnPageStartedListener() {
     @Override
     public void onPageStarted() {
        
     }
 });
```
