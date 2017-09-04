package com.jdtorregrosas.azure_ad_b2c_webview_oauth2;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.jdtorregrosas.azureadb2cwebviewoauth2.AzureAdB2CWebView;

public class MainActivity extends AppCompatActivity {

    private Context mContext;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mContext = this;

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
                Toast.makeText(mContext, "On Login Success, token: " + token, Toast.LENGTH_SHORT).show();
            }
        });
        webView.setOnPageStarted(new AzureAdB2CWebView.OnPageStartedListener() {
            @Override
            public void onPageStarted() {
                Toast.makeText(mContext, "On page started", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
