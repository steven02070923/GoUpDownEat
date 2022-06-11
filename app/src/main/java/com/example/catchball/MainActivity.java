package com.example.catchball;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Point;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.provider.ContactsContract;
import android.view.Display;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    //public static boolean isPlay;
    private TextView scoreLabel;
    private TextView startLabel;
    private ImageView player;
    private ImageView red;
    private ImageView blue;
    private ImageView black;
    private ImageView purple;
    private ImageView yellow;
    private ImageView red2;
    private ImageView red3;
    //position
    private int playerX;
    private int playerY;
    private int redX=-200;
    private int redY;
    private int red2X=-200;
    private int red2Y;
    private int red3X=-200;
    private int red3Y;
    private int yellowX;
    private int yellowY;
    private int blackX;
    private int blackY;
    private int purpleX=-200;
    private int purpleY;
    private int blueX=-200;
    private int blueY;
    //Score
    private int score=0;

    //size
    private  int frameHeight;
    private  int playerSize;
    //Initialize Class
    private Handler handler=new Handler();
    private Timer timer =new Timer();
    private SoundPlayer sound;
    //status check
    private  boolean action_flg=false;
    private  boolean start_flg=false;

    private  int screenWidth;
    private  int screenHeight;

    //music
    //MediaPlayer song;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sound=new SoundPlayer(this);
        music.play(this, R.raw.background);
        /*song=MediaPlayer.create(MainActivity.this,R.raw.background);
        song.setLooping(true);
        song.setVolume(1,1);
        song.start();*/


        scoreLabel=(TextView) findViewById(R.id.scoreLabel);
        startLabel=(TextView) findViewById(R.id.startLabel);
        player=(ImageView) (findViewById(R.id.player));
        red  =(ImageView) findViewById(R.id.red);
        blue =(ImageView)findViewById(R.id.blue);
        black=(ImageView)findViewById(R.id.black);
        purple=(ImageView) findViewById(R.id.purple);
        yellow=(ImageView) findViewById(R.id.yellow);
        red2  =(ImageView) findViewById(R.id.red2);
        red3  =(ImageView) findViewById(R.id.red3);


        //get the screen size
        WindowManager windowManager=getWindowManager();
        Display display=windowManager.getDefaultDisplay();
        Point size =new Point();
        display.getSize(size);

        screenWidth=size.x;
        screenHeight=size.y;

        //移到螢幕外
        red.setX(-200);
        red.setY(-200);
        red2.setX(-200);
        red2.setY(-200);
        red3.setX(-200);
        red3.setY(-200);
        blue.setX(-200);
        blue.setY(-200);
        black.setX(-200);
        black.setY(-200);
        purple.setX(-200);
        purple.setY(-200);
        yellow.setX(-200);
        yellow.setY(-200);

        scoreLabel.setText("Score: 0");


    }
    public void  changePos(){
        //hitCheck
        hitCheck();
        win();
        //red
        redX -=20;
        if(redX< 0){
            redX=screenWidth+10;
            redY=(int)Math.floor(Math.random()*(frameHeight-red.getHeight()));
        }
        red.setX(redX);
        red.setY(redY);
        //red2
        red2X -=10;
        if(score>=1000) {
            if (red2X < 0) {
                red2X = screenWidth + 100;
                red2Y = (int) Math.floor(Math.random() * (frameHeight - red2.getHeight()));
            }
        }
        red2.setX(red2X);
        red2.setY(red2Y);
        //red2
        red3X -=30;
        if(score>=1500) {
            if (red3X < 0) {
                red3X = screenWidth + 200;
                red3Y = (int) Math.floor(Math.random() * (frameHeight - red3.getHeight()));
            }
        }
        red3.setX(red3X);
        red3.setY(red3Y);
        //yellow
        yellowX-=16;
        if(yellowX< 0){
            yellowX=screenWidth+30;
            yellowY=(int)Math.floor(Math.random()*(frameHeight-yellow.getHeight()));
        }
        yellow.setX(yellowX);
        yellow.setY(yellowY);

        //black
        blackX-=10;
        if(blackX< 0){
            blackX=screenWidth+20;
            blackY=(int)Math.floor(Math.random()*(frameHeight-black.getHeight()));
        }
        black.setX(blackX);
        black.setY(blackY);
        //purple
        purpleX -= 10;
        if(score>=500&&score<=1500) {
            if (purpleX < 0) {
                purpleX = screenWidth + 10;
                purpleY = (int) Math.floor(Math.random() * (frameHeight - purple.getHeight()));
            }
        }
        purple.setX(purpleX);
        purple.setY(purpleY);
        //blue
        blueX -= 10;
        if(score>=1500) {
            if (blueX < 0) {
                blueX = screenWidth + 10;
                blueY = (int) Math.floor(Math.random() * (frameHeight - blue.getHeight()));
            }
        }
        blue.setX(blueX);
        blue.setY(blueY);

        //move player
        if(action_flg==true){
            playerY-=20;
        }else {
            playerY+=20;
        }
        if(playerY<0)playerY=0;
        if(playerY>frameHeight-playerSize)playerY=frameHeight-playerSize;
        player.setY(playerY);

        scoreLabel.setText("Score: "+score);
    }
    public void hitCheck(){
        //if the center of the ball is in the box ,it count as hit
        //purple
        int purpleCenterX = purpleX + purple.getWidth() / 2;
        int purpleCenterY = purpleY + purple.getHeight() / 2;

        if (0 <= purpleCenterX && purpleCenterX <= playerSize &&
                playerY <= purpleCenterY && purpleCenterY <= playerSize + playerY) {

            score -= 100;
            purpleX = -200;
            sound.playHurtSound();
        }
        //blue
        int blueCenterX = blueX + blue.getWidth() / 2;
        int blueCenterY = blueY + blue.getHeight() / 2;

        if (0 <= blueCenterX && blueCenterX <= playerSize &&
                    playerY <= blueCenterY && blueCenterY <= playerSize + playerY) {

            score -= 500;
            blueX = -200;
            sound.playHurtSound();
        }
        //red
        int redCenterX = redX+red.getWidth()/2;
        int redCenterY = redY+red.getHeight()/2;

        if(0<=redCenterX && redCenterX<=playerSize&&
                playerY<=redCenterY && redCenterY<=playerSize+playerY){
            timer.cancel();
            timer=null;
            //song.pause();
            music.stop(this);
            sound.playOverSound();
            //show result
            Intent intent=new Intent(getApplicationContext(),result.class);
            intent.putExtra("SCORE",score);
            startActivity(intent);
        }
        //red2
        int red2CenterX = red2X + red2.getWidth() / 2;
        int red2CenterY = red2Y + red2.getHeight() / 2;

        if (0 <= red2CenterX && red2CenterX <= playerSize &&
                    playerY <= red2CenterY && red2CenterY <= playerSize + playerY) {
            timer.cancel();
            timer = null;
            //song.pause();
            music.stop(this);
            sound.playOverSound();
            //show result
            Intent intent = new Intent(getApplicationContext(), result.class);
            intent.putExtra("SCORE", score);
            startActivity(intent);
        }
        //red3
        int red3CenterX = red3X + red3.getWidth() / 2;
        int red3CenterY = red3Y + red3.getHeight() / 2;

        if (0 <= red3CenterX && red3CenterX <= playerSize &&
                    playerY <= red3CenterY && red3CenterY <= playerSize + playerY) {
            timer.cancel();
            timer = null;
            //song.pause();
            music.stop(this);
            sound.playOverSound();
            //show result
            Intent intent = new Intent(getApplicationContext(), result.class);
            intent.putExtra("SCORE", score);
            startActivity(intent);
        }

        //yellow蛋黃酥
        int yellowCenterX = yellowX+yellow.getWidth()/2;
        int yellowCenterY = yellowY+yellow.getHeight()/2;

        if(0<=yellowCenterX && yellowCenterX<=playerSize&&
                playerY<=yellowCenterY && yellowCenterY<=playerSize+playerY){
            score+=150;
            yellowX =-20;
            sound.playHitSound();
        }
        //black鳳梨中心點
        int blackCenterX = blackX+black.getWidth()/2;
        int blackCenterY = blackY+black.getHeight()/2;

        if(0<=blackCenterX && blackCenterX<=playerSize&&
                playerY<=blackCenterY && blackCenterY<=playerSize+playerY){
            score+=100;
            blackX =-20;
            sound.playHitSound();
        }
    }
    public boolean onTouchEvent(MotionEvent me){
        if(start_flg==false){
            start_flg=true;

            FrameLayout frame =(FrameLayout)(findViewById(R.id.frame));
            frameHeight =frame.getHeight();

            playerY=(int)player.getY();
            playerSize=player.getHeight();

            startLabel.setVisibility(View.GONE);//點擊開始


            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            changePos();

                        }
                    });
                }
            },0,20);//call changePos every 20milliseconds


        }else {
            if(me.getAction()== MotionEvent.ACTION_DOWN){
                action_flg=true;
            }else if(me.getAction()==MotionEvent.ACTION_UP){
                action_flg=false;
            }
        }
        return  true;
    }
    public void win(){
        if(score>=2000){
            timer.cancel();
            timer=null;
            //song.pause();
            music.stop(this);
            sound.playWinSound();
            //show result
            Intent intent=new Intent(getApplicationContext(),win.class);
            intent.putExtra("SCORE",score);
            startActivity(intent);
        }
    }

    //無法回前頁
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