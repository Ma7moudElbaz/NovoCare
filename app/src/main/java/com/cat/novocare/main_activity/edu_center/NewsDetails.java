package com.cat.novocare.main_activity.edu_center;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;

import com.akexorcist.localizationactivity.ui.LocalizationActivity;
import com.bumptech.glide.Glide;
import com.cat.novocare.R;

public class NewsDetails extends LocalizationActivity {

    ImageView image,back;
    TextView title, date, content;
    News_item item;
    WebView video;

    @SuppressLint("SetJavaScriptEnabled")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_details);
        image = findViewById(R.id.img);
        back = findViewById(R.id.back);
        title = findViewById(R.id.title);
        date = findViewById(R.id.date);
        content = findViewById(R.id.content);
        video = findViewById(R.id.video);


        video.getSettings().setJavaScriptEnabled(true);
        video.getSettings().setLoadWithOverviewMode(true);
        video.getSettings().setUseWideViewPort(true);

        video.getSettings().setDomStorageEnabled(true);
        video.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
        video.getSettings().setSupportMultipleWindows(true);

        video.setWebChromeClient(new WebChromeClient());


        item = (News_item) getIntent().getSerializableExtra("newsItem");

        title.setText(item.getTitle());
        date.setText(item.getDate());
        content.setText(Html.fromHtml(item.getContent()));
        Glide.with(this).load(item.getImageUrl()).placeholder(R.drawable.image_loading).into(image);
        if (!item.getVideoUrl().equals("null")){
            video.setVisibility(View.VISIBLE);
            video.loadUrl(item.getVideoUrl());
        }

        back.setOnClickListener(v -> onBackPressed());

    }
}