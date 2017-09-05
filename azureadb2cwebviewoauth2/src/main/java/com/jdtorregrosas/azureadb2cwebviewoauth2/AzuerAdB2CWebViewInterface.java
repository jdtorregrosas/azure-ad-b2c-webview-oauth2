package com.jdtorregrosas.azureadb2cwebviewoauth2;

/**
 * Created by jdtor on 27.07.2017.
 */

public interface AzuerAdB2CWebViewInterface {
    void setOnPageStarted(AzureAdB2CWebView.OnPageStartedListener listener);
    void setOnLoginFailed(AzureAdB2CWebView.OnLoginFailedListener listener);
    void setOnLoginSuccess(AzureAdB2CWebView.OnLoginSuccessListener listener);
}
