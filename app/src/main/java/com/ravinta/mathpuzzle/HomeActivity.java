package com.ravinta.mathpuzzle;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_home);

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