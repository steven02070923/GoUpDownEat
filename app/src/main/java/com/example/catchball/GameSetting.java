package com.example.catchball;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageButton;
import android.media.AudioManager;
import android.content.Context;
import android.widget.Toast;

public class GameSetting extends AppCompatActivity {
    private ImageButton music;
    music mp;
    //private ImageButton music1;
    //private ImageButton music2;
    public static boolean isPlay;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_setting);

        music=(ImageButton) findViewById(R.id.music1);

        music.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setting(view);
            }
        });
    }
    public  void BackStart(View view){
        startActivity(new Intent(getApplicationContext(),start.class));
    }


    public void setting(View v) {
        isPlay = !isPlay;
        music.setImageResource(isPlay ? R.drawable.music2 : R.drawable.music1);
//        isPlay?R.drawable.mute:R.drawable.sound;
        if (!isPlay) {
            Toast.makeText(GameSetting.this,"Sound On",Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(GameSetting.this,"Sound Off",Toast.LENGTH_SHORT).show();
        }
    }
    @Override
    public  boolean dispatchKeyEvent(KeyEvent event){
        if(event.getAction() == KeyEvent.ACTION_DOWN){
            switch (event.getKeyCode()){
                case KeyEvent.KEYCODE_BACK:
                    return true;
            }
        }
        return super.dispatchKeyEvent(event);
    }
}