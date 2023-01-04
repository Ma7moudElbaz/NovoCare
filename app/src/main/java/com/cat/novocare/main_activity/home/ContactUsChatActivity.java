package com.cat.novocare.main_activity.home;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.CookieManager;
import android.webkit.PermissionRequest;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.akexorcist.localizationactivity.ui.LocalizationActivity;
import com.cat.novocare.R;
import com.cat.novocare.ThankYouActivity;

import java.util.Locale;

public class ContactUsChatActivity extends LocalizationActivity {
    WebView webView;
    ProgressBar loading;
    String url;
    ImageView back;
    String lang;

    @SuppressLint("SetJavaScriptEnabled")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_us_chat);

        webView = findViewById(R.id.webView);
        loading = findViewById(R.id.loading);
        back = findViewById(R.id.back);
        back.setOnClickListener(v -> onBackPressed());

        lang = Locale.getDefault().toString();
//        url = "https://chat.novocare-eg.com/?lang="+lang;
        url = "https://vya.cat-sw.com/elbazzzzzzzz";

//        CookieManager.getInstance().setAcceptThirdPartyCookies(webView, false);
//        webView.getSettings().setAppCacheEnabled(false);
//
//        webView.getSettings().setCacheMode(WebSettings.LOAD_NO_CACHE);
//        webView.clearCache(true);
//        webView.clearHistory();


        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setLoadWithOverviewMode(true);
        webView.getSettings().setUseWideViewPort(true);
        webView.getSettings().setAllowFileAccessFromFileURLs(true);
        webView.getSettings().setAllowUniversalAccessFromFileURLs(true);

        webView.getSettings().setDomStorageEnabled(true);
        webView.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
        webView.getSettings().setSupportMultipleWindows(false);
        webView.getSettings().setMediaPlaybackRequiresUserGesture(false);



        webView.addJavascriptInterface(new JsInterface(ContactUsChatActivity.this), "AndroidFunction");

        String myUA = "Android" + "Chrome/[.0-9]* Mobile";
        webView.getSettings().setUserAgentString(myUA);

        webView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                Log.e("TAG", url);
                loading.setVisibility(View.VISIBLE);
                view.loadUrl(url);

                return true;
            }

            @Override
            public void onPageFinished(WebView view, final String url) {
                loading.setVisibility(View.GONE);
                if (url.contains("disconnectChat")) {
                    Intent i = new Intent(ContactUsChatActivity.this, ThankYouActivity.class);
                    startActivity(i);
                    finish();
                }
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

            @Override
            public void onPermissionRequest(PermissionRequest request) {
                Log.d("Permission", "onPermissionRequest");
                ContactUsChatActivity.this.runOnUiThread(() -> {
                    Log.d("Permission", request.getOrigin().toString());
                    request.grant(request.getResources());
                });
            }
        });

        webView.loadUrl(url);
        CookieManager.getInstance().removeAllCookies(null);
        CookieManager.getInstance().flush();
    }


    private static boolean hasArabic(String s) {
        for (int i = 0; i < s.length(); ) {
            int c = s.codePointAt(i);
            if (c >= 0x0600 && c <= 0x06E0)
                return true;
            i += Character.charCount(c);
        }
        return false;
    }

    private String replaceArabic(String s) {
        if (hasArabic(s)) {
            int randomInt = (int) (10000.0 * Math.random());
            return "arabic" + randomInt;
        } else {
            return s;
        }
    }
}