package com.example.slapdesh.Common;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.slapdesh.R;
import com.example.slapdesh.User.UserDashBoard;

public class SplashScreen extends AppCompatActivity {

    private ImageView slepdeshimage;
    private TextView powredby;
    private static int SPLASH_TIMER=5000;

    Animation side_anim,buttom_anim;
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.splashscreen_main);

        slepdeshimage=findViewById(R.id.slapdesh_id);
        powredby=findViewById(R.id.powered_by_id);

        side_anim= AnimationUtils.loadAnimation(this,R.anim.side_animation);
        buttom_anim= AnimationUtils.loadAnimation(this,R.anim.buttom_animation);

        slepdeshimage.setAnimation(side_anim);
        powredby.setAnimation(buttom_anim);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

            sharedPreferences = getSharedPreferences("onBoardScreen",MODE_PRIVATE);

            boolean isfirstime = sharedPreferences.getBoolean("firstTime",true);
            if(isfirstime)
            {
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putBoolean("firstTime",false);
                editor.commit();
                Intent intent=new Intent(SplashScreen.this, OnBoarding.class);
                startActivity(intent);
                finish();
            }
            else {
                Intent intent=new Intent(SplashScreen.this, UserDashBoard.class);
                startActivity(intent);
                finish();
            }


            }
        },SPLASH_TIMER);
    }
}
