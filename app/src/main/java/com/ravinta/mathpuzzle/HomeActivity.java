package com.ravinta.mathpuzzle;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

public class HomeActivity extends AppCompatActivity {
    Animation zoomIn,zoomOut;
    ImageView welcomeImg;
    TextView easy,medium,hard,select_level;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_home);

        select_level=findViewById(R.id.home__select_level);
        easy=findViewById(R.id.home_level_easy);
        medium=findViewById(R.id.home_level_Medium);
        hard=findViewById(R.id.home_Hard);
        welcomeImg=findViewById(R.id.welcomeText);



        zoomIn= AnimationUtils.loadAnimation(this,R.anim.zoom_in);
        zoomOut= AnimationUtils.loadAnimation(this,R.anim.zoom_out);
        welcomeImg.setAnimation(zoomIn);


    }
    @Override
    protected void onResume() {
        super.onResume();
        Handler handler=new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {

                welcomeImg.setAnimation(zoomOut);
                welcomeImg.setVisibility(View.GONE);
                select_level.setVisibility(View.VISIBLE);
                easy.setVisibility(View.VISIBLE);
                medium.setVisibility(View.VISIBLE);
                hard.setVisibility(View.VISIBLE);





            }
        },2000);




    }

    public void gotoGame(View view) {
        Intent intent=new Intent(HomeActivity.this,GameActivity.class);
        switch (view.getId()){
            case R.id.home_level_easy:
                intent.putExtra("Level","easy");
                break;
            case R.id.home_level_Medium:
                intent.putExtra("Level","medium");
                break;
            case R.id.home_Hard:
                intent.putExtra("Level","hard");
                break;

        }
        startActivity(intent);
    }
}