package com.cat.novocare.main_activity.locator;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.provider.Settings;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import com.cat.novocare.R;
import com.cat.novocare.utils.StoreActivity;

public class LocatorFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_locator, container, false);
    }

    WebView webView;
    ProgressBar loading;
    String url;

    @SuppressLint("SetJavaScriptEnabled")
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        String device_id = "Android-"+ Settings.Secure.getString(getActivity().getContentResolver(), Settings.Secure.ANDROID_ID);
        StoreActivity.addScreenActivity("Locator",device_id);

        webView = view.findViewById(R.id.webView);
        loading = view.findViewById(R.id.loading);


        url = "https://novolocator.com";


        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setLoadWithOverviewMode(true);
        webView.getSettings().setUseWideViewPort(true);

        webView.getSettings().setDomStorageEnabled(true);
        webView.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
        webView.getSettings().setSupportMultipleWindows(true);

        webView.setWebViewClient(new WebViewClient() {

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                loading.setVisibility(View.VISIBLE);
                view.loadUrl(url);
                return true;
            }

            @Override
            public void onPageFinished(WebView view, final String url) {
                loading.setVisibility(View.GONE);
            }


        });

        webView.setWebChromeClient(new WebChromeClient() {
            @Override
            public boolean onCreateWindow(WebView view, boolean dialog, boolean userGesture, android.os.Message resultMsg)
            {
                WebView.HitTestResult result = view.getHitTestResult();
                String data = result.getExtra();
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(data));
                startActivity(browserIntent);
                return false;
            }
        });

        webView.loadUrl(url);
    }

}