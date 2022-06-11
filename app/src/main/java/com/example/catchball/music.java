package com.example.catchball;

import android.content.Context;
import android.media.MediaPlayer;

public class music {
    private static MediaPlayer mp = null;

    /** Stop old song and start new one */
    public static void  play(Context context, int resource) {

        stop(context);
        mp = MediaPlayer.create(context, resource);
        mp.setLooping(true);
        mp.start();
        if(GameSetting.isPlay){mp.stop();}

    }

    /** Stop the music */
    public static void stop(Context context) {
        if (mp != null) {
            mp.stop();
            mp.release();
            mp = null;
        }
    }
}
