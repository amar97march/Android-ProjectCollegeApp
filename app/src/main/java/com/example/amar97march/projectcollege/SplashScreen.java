package com.example.amar97march.projectcollege;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

public class SplashScreen extends AppCompatActivity {

    private TextView tv;
    private ImageView iv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        tv= (TextView)findViewById(R.id.tv);
        iv=(ImageView) findViewById(R.id.iv);
        Animation myFadeIn= AnimationUtils.loadAnimation(this,R.anim.fade_in);
        tv.startAnimation(myFadeIn);
        iv.startAnimation(myFadeIn);
        Thread myThread= new Thread(){
            @Override
            public void run(){
            try{
                sleep(5000);
                Intent intent=new Intent(getApplicationContext(),MainActivity.class);
                startActivity(intent);
                finish();
            }catch(InterruptedException e){
                e.printStackTrace();
            }
            }
        };
        myThread.start();
    }
}
