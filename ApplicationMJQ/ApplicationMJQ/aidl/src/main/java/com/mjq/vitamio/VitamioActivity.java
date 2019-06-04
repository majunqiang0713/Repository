package com.mjq.vitamio;

import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.aidl.R;

import butterknife.BindView;
import io.vov.vitamio.MediaPlayer;
import io.vov.vitamio.Vitamio;
import io.vov.vitamio.widget.MediaController;
import io.vov.vitamio.widget.VideoView;

import android.view.View;

public class VitamioActivity extends AppCompatActivity {

    VideoView vitamio_videoview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vitamio);
        Vitamio.isInitialized(this);
       vitamio_videoview= (VideoView) findViewById(R.id.vitamio_videoview);


            //放入网址
        vitamio_videoview.setVideoURI(Uri.parse("http://qiubai-video.qiushibaike.com/YXSKWQA6N838MJC4_3g.mp4"));

        //设置控制栏
        vitamio_videoview.setMediaController(new MediaController(this));

        //获取焦点
        vitamio_videoview.requestFocus();
        //获取焦点
        vitamio_videoview.requestFocus();


        //准备播放监听
        vitamio_videoview.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                mp.setPlaybackSpeed(1.0f);//设置播放速度
                mp.start();
            }
        });
        /**
         * 播放完成继续播放
         */
        vitamio_videoview.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                mp.seekTo(0);
                mp.start();
            }
        });

    }




}
