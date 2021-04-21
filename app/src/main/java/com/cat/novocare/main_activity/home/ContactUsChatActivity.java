package com.cat.novocare.main_activity.home;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import com.cat.novocare.R;

public class ContactUsChatActivity extends AppCompatActivity {


    WebView webView;
    ProgressBar loading;
    String name;
    String url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_us_chat);

        webView = findViewById(R.id.webView);
        loading = findViewById(R.id.loading);
        name = getIntent().getStringExtra("name");


        url = "https://cat-sw.com/clickdesk/customerly.php/?name=" + name;


        webView.clearCache(true);
        webView.clearHistory();

        webView.getSettings().setAppCacheEnabled(false);
        webView.getSettings().setCacheMode(WebSettings.LOAD_NO_CACHE);

        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setLoadWithOverviewMode(true);
        webView.getSettings().setUseWideViewPort(true);

        webView.getSettings().setDomStorageEnabled(true);
        webView.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
        webView.getSettings().setSupportMultipleWindows(false);

        webView.setWebViewClient(new WebViewClient() {

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                Log.e("TAG", url);
                if (url.contains("realtime.customerly.io/call")) {
                    Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                    startActivity(browserIntent);
                } else {
                    loading.setVisibility(View.VISIBLE);
                    view.loadUrl(url);
                }
                return true;
            }

            @Override
            public void onPageFinished(WebView view, final String url) {
                loading.setVisibility(View.GONE);
            }


        });


        webView.setWebChromeClient(new WebChromeClient() {
            @Override
            public boolean onCreateWindow(WebView view, boolean dialog, boolean userGesture, android.os.Message resultMsg) {
//                WebView.HitTestResult result = view.getHitTestResult();
//                String data = result.getExtra();
//                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(data));
//                startActivity(browserIntent);
//                return false;

                WebView.WebViewTransport transport = (WebView.WebViewTransport) resultMsg.obj;
                transport.setWebView(webView);
                resultMsg.sendToTarget();
                return true;
            }
        });

        webView.loadUrl(url);
    }
}