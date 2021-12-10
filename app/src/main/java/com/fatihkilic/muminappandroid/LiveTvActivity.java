package com.fatihkilic.muminappandroid;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.VideoView;

import com.fatihkilic.muminappandroid.databinding.ActivityAyarlarBinding;
import com.fatihkilic.muminappandroid.databinding.ActivityLiveTvBinding;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView;

public class LiveTvActivity extends AppCompatActivity {

    private ActivityLiveTvBinding binding;
    YouTubePlayerView youtubeKabePlayer;

    String videoUrl = "https://youtu.be/X6hmvvqXFfU";



    @SuppressLint("SetJavaScriptEnabled")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_live_tv);

        binding = ActivityLiveTvBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        getSupportActionBar().setTitle("Kabe Canlı");

        youtubeKabePlayer = binding.youtubeKabePlayer;

        getLifecycle().addObserver(youtubeKabePlayer);
        youtubeKabePlayer.getPlayerUiController().enableLiveVideoUi(true);


        youtubeKabePlayer.addYouTubePlayerListener(new AbstractYouTubePlayerListener() {
            @Override
            public void onReady(@NonNull YouTubePlayer youTubePlayer) {
                String videoId =  "X6hmvvqXFfU" ;
                youTubePlayer . loadVideo(videoId, 0 );
            }
        });






      /*


  kabeLiveWebView = binding.liveKabeView;
        btnPlayPause = binding.btnPlayPause;

        btnPlayPause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mDialog = new ProgressDialog(LiveTvActivity.this);
                mDialog.setMessage("Lütfen bekleyiniz...");
                mDialog.setCanceledOnTouchOutside(false);
                mDialog.show();

                try {

                    if (!kabeLiveWebView.isPlaying()) {

                        Uri uri = Uri.parse(videoUrl);
                        kabeLiveWebView.setVideoURI(uri);
                        kabeLiveWebView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                            @Override
                            public void onCompletion(MediaPlayer mp) {

                                btnPlayPause.setImageResource(R.drawable.ic_play);

                            }
                        });

                    } else {

                        kabeLiveWebView.pause();
                        btnPlayPause.setImageResource(R.drawable.ic_play);

                    }

                } catch (Exception ex) {



                }

                kabeLiveWebView.requestFocus();
                kabeLiveWebView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                    @Override
                    public void onPrepared(MediaPlayer mp) {

                        mDialog.dismiss();
                        mp.setLooping(true);
                        kabeLiveWebView.start();
                        btnPlayPause.setImageResource(R.drawable.ic_pause);

                    }
                });

            }
        });



       kabeLiveWebView.setVideoPath("http://videocdn.bodybuilding.com/video/mp4/62000/62792m.mp4");
        kabeLiveWebView.start();kabeLiveWebView = binding.liveKabeView;
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