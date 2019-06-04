package com.mjq.music;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Binder;
import android.os.IBinder;

import com.example.aidl.R;

public class MusicService extends Service {
    public MediaPlayer mMMMediaPlayer;

    public MusicService() {
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mMMMediaPlayer = MediaPlayer.create(this, R.raw.music);


    }

    @Override
    public IBinder onBind(Intent intent) {
       return  new MusicBinder();
    }

    public void playMusic() {
        //mMMMediaPlayer.reset();
        mMMMediaPlayer.start();


    }

    public void pauseMusic() {
        if(mMMMediaPlayer.isPlaying()){
            mMMMediaPlayer.pause();
        }


    }

    public void countinueMusic() {
        mMMMediaPlayer.start();

    }

    public void stopMusic() {
        if(mMMMediaPlayer.isPlaying()){
            mMMMediaPlayer.stop();
        }
    }

    public class  MusicBinder extends Binder implements IMusicServices{

        @Override
        public void iplayMusic() {
            playMusic();

        }

        @Override
        public void ipauseMusic() {
            pauseMusic();

        }

        @Override
        public void icountinueMusic() {
            countinueMusic();

        }

        @Override
        public void istopMusic() {
            stopMusic();

        }
    }

    @Override
    public void onDestroy() {
        if(mMMMediaPlayer!=null){
            mMMMediaPlayer.stop();
            mMMMediaPlayer.release();
        }
        super.onDestroy();
    }
}
