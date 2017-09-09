# Azure AD B2C WebView

Commonly it is hard to find a library that can handle easily Azure Oauth2
requests, as abstraction you can do it with a simple web view, but I'm still
pretty sure it isn't easy enough. This library shows this webView without
making the things so hard.

## How to use it

1. Include the view in your XML layout lke this:

```xml
<com.jdtorregrosas.azureadb2cwebviewoauth2.AzureAdB2CWebView
    android:id="@+id/webview_login"
    xmlns:azureb2c="http://schemas.android.com/apk/res-auto"
    android:layout_width="fill_parent"
    android:layout_height="fill_parent"
    azureb2c:tenant="@string/b2c_tenant"
    azureb2c:signInPolicy="@string/b2c_policy"
    azureb2c:redirectUri="@string/b2c_redirect_uri"
    azureb2c:clientId="@string/b2c_client_id"
/>
```

2. Create the needed strings depending on your tenant configuration

```
<string name="b2c_tenant">live.your_tenant.com</string>
<string name="b2c_client_id">YOUR-CLIENT-ID</string>
<string name="b2c_redirect_uri">urn:ietf:wg:oauth:2.0:oob</string>
<string name="b2c_policy">B2C_sign_in_policy</string>
```

3. Bind your AzureAdB2CWebView and setup it

```xml
AzureAdB2CWebView webView = (AzureAdB2CWebView) findViewById(R.id.webview_login);
webView.setUp();
```

4. Implement the event methods and introduce your logic

```java
webView.setOnLoginFailed(new AzureAdB2CWebView.OnLoginFailedListener() {
     @Override
     public void onLoginFailed() {
     }
     });
     webView.setOnLoginSuccess(new AzureAdB2CWebView.OnLoginSuccessListener() {
     @Override
     public void onLoginSuccess(String token) {
         Toast.makeText(mContext, "On Login Success, token: " + token, Toast.LENGTH_SHORT).show();
     }
     });
     webView.setOnPageStarted(new AzureAdB2CWebView.OnPageStartedListener() {
     @Override
     public void onPageStarted() {
         Toast.makeText(mContext, "On page started", Toast.LENGTH_SHORT).show();
     }
});
```

5. Use jcenter() as repository in your project gradle

```gradle
allprojects {
    repositories {
        jcenter()
    }
}
```

6. Import the module into your app gradle

```gradle
compile 'com.jdtorregrosas:azureadb2cwebviewoauth2:0.0.1'
```

7. Enjoy!!