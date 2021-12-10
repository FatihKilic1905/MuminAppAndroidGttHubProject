package com.fatihkilic.muminappandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.VideoView;

import com.fatihkilic.muminappandroid.databinding.ActivityAyarlarBinding;
import com.fatihkilic.muminappandroid.databinding.ActivityLiveTvBinding;

public class LiveTvActivity extends AppCompatActivity {

    private ActivityLiveTvBinding binding;
    VideoView kabeLiveWebView;



    @SuppressLint("SetJavaScriptEnabled")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_live_tv);

        binding = ActivityLiveTvBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        getSupportActionBar().setTitle("Kabe Canlı");

        kabeLiveWebView = binding.liveKabeView;

        kabeLiveWebView.setVideoPath("http://videocdn.bodybuilding.com/video/mp4/62000/62792m.mp4");
        kabeLiveWebView.start();

      /*  kabeLiveWebView = binding.liveKabeView;
        kabeLiveWebView.getSettings().setJavaScriptEnabled(true);
        kabeLiveWebView.setWebViewClient(new WebViewClient(){

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {

                kabeLiveWebView.loadUrl(request.getUrl().toString());

                return false;

            }
        });


        kabeLiveWebView.loadUrl("https://youtu.be/X6hmvvqXFfU");



       */
    }
}