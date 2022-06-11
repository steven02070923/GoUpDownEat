package com.example.catchball;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;

public class start extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
    }
    public  void startGame(View view){
        startActivity(new Intent(getApplicationContext(),MainActivity.class));
    }
    public  void GameIntroduce(View view){
        startActivity(new Intent(getApplicationContext(),GameIntroduce.class));
    }
    public  void GameSetting(View view){
        startActivity(new Intent(getApplicationContext(),GameSetting.class));
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