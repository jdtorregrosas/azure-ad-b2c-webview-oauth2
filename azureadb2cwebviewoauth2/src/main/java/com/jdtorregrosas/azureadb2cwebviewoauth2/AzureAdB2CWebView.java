package com.jdtorregrosas.azureadb2cwebviewoauth2;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.net.UrlQuerySanitizer;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;
import android.webkit.CookieManager;
import android.webkit.CookieSyncManager;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import java.io.UnsupportedEncodingException;

/**
 * Created by jdtor on 27.07.2017.
 */

public class AzureAdB2CWebView extends WebView implements AzuerAdB2CWebViewInterface{

    private Context mContext;
    private Authorization mAuth;
    private String mTenant, mClientId, mRedirectUri, mPolicy;
    private OnPageStartedListener mOnPageStartedListener;
    private OnLoginFailedListener mOnLoginFailedListener;
    private OnLoginSuccessListener mOnLoginSuccessListener;

    public AzureAdB2CWebView(Context context, AttributeSet attrs) {
        super(context, attrs);

        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.AzureAdB2CWebView, 0, 0);
        try {
            mTenant = ta.getString(R.styleable.AzureAdB2CWebView_tenant);
            mClientId = ta.getString(R.styleable.AzureAdB2CWebView_clientId);
            mRedirectUri = ta.getString(R.styleable.AzureAdB2CWebView_redirectUri);
            mPolicy = ta.getString(R.styleable.AzureAdB2CWebView_signInPolicy);
        } finally {
            ta.recycle();
        }
        mAuth = new Authorization(mTenant, mClientId, mRedirectUri, mPolicy);

        this.mContext = context;
    }

    public void setUp(){
        this.clearCache(true);
        this.clearHistory();
        this.clearCookies();
        String loginUri = "";
        try {
            loginUri =  mAuth.getLoginUri();

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        this.loadUrl(loginUri);
        this.getSettings().setJavaScriptEnabled(true);
        this.setWebViewClient(new WebViewClient() {
            // When start to load page, show url in activity's title bar
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {

                mOnPageStartedListener.onPageStarted();

                UrlQuerySanitizer sanitizer = new UrlQuerySanitizer(url);
                String token = sanitizer.getValue("code");
                if (token == null) {
                    mOnLoginFailedListener.onLoginFailed();
                    return;
                }
                mOnLoginSuccessListener.onLoginSuccess(token);
                endLogin();
            }
        });
    }

    public void clearCookies()
    {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP_MR1) {
            CookieManager.getInstance().removeAllCookies(null);
            CookieManager.getInstance().flush();
        } else
        {
            CookieSyncManager cookieSyncMngr=CookieSyncManager.createInstance(mContext);
            cookieSyncMngr.startSync();
            CookieManager cookieManager=CookieManager.getInstance();
            cookieManager.removeAllCookie();
            cookieManager.removeSessionCookie();
            cookieSyncMngr.stopSync();
            cookieSyncMngr.sync();
        }
    }

    private void endLogin(){
        ((Activity) mContext).finish();
    }

    @Override
    public void setOnPageStarted(OnPageStartedListener listener){
        mOnPageStartedListener = listener;
    }

    public interface OnPageStartedListener{
        void onPageStarted();
    }

    @Override
    public void setOnLoginFailed(OnLoginFailedListener listener){
        mOnLoginFailedListener = listener;
    }

    public interface OnLoginFailedListener{
        void onLoginFailed();
    }

    @Override
    public void setOnLoginSuccess(OnLoginSuccessListener listener){
        mOnLoginSuccessListener = listener;
    }

    public interface OnLoginSuccessListener{
        void onLoginSuccess(String token);
    }
}
