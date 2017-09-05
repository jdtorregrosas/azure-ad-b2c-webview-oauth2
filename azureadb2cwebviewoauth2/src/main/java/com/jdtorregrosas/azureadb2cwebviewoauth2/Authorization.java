package com.jdtorregrosas.azureadb2cwebviewoauth2;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * Created by jdtor on 27.07.2017.
 */

public class Authorization {
    private String tenant;
    private String clientId;
    private String redirectUri;
    private String signInPolicy;

    public Authorization (String tenant, String clientId, String redirectUri, String signInPolicy){
        this.tenant = tenant;
        this.clientId = clientId;
        this.redirectUri = redirectUri;
        this.signInPolicy = signInPolicy;
    }

    public String getLoginUri() throws UnsupportedEncodingException {
        return "https://login.microsoftonline.com/" + tenant + "/oauth2/v2.0/authorize?client_id=" + clientId + "&response_type=code&redirect_uri=" + URLEncoder.encode(redirectUri, "UTF8") + "&response_mode=query&scope=" + clientId + "&p=" + signInPolicy;
    }
}

