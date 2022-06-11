package com.example.catchball;

import android.content.Context;
import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Build;

public class SoundPlayer {
    private AudioAttributes audioAttributes;
    final int SOUND_POOL_MAX=3;

    private static SoundPool soundPool;
    private static int hitSound;
    private static int overSound;
    private static int hurtSound;
    private static int winSound;

    public SoundPlayer(Context context){

        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.LOLLIPOP){
            audioAttributes=new AudioAttributes.Builder()
                    .setUsage(AudioAttributes.USAGE_GAME)
                    .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                    .build();
            soundPool = new SoundPool.Builder()
                    .setAudioAttributes(audioAttributes)
                    .setMaxStreams(SOUND_POOL_MAX)
                    .build();
        }else{
            //SoundPool(int maxStream,int streamType,int srcQuality)
            soundPool=new SoundPool(SOUND_POOL_MAX, AudioManager.STREAM_MUSIC,0);
        }

        hitSound=soundPool.load(context,R.raw.hit,1);
        overSound=soundPool.load(context,R.raw.over,1);
        hurtSound=soundPool.load(context,R.raw.hurt,1);
        winSound=soundPool.load(context,R.raw.win,1);

    }
        public void playHitSound(){
        soundPool.play(hitSound,1.0f,1.0f,1,0,1.0f);
    }
        public void playOverSound(){
        soundPool.play(overSound,1.0f,1.0f,1,0,1.0f);
    }
        public void playHurtSound(){soundPool.play(hurtSound,1.0f,1.0f,1,0,1.0f);}
        public void playWinSound(){soundPool.play(winSound,1.0f,1.0f,1,0,1.0f);}
}
