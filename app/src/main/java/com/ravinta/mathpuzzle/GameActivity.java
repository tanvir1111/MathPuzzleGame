package com.ravinta.mathpuzzle;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

public class GameActivity extends AppCompatActivity implements View.OnClickListener {

    public static int COUNT_DOWN_TIME=30;

    TextView num1,num2,numResult,timeLeft,operator1,operatorAdd,operatorSub,operatorMul,operatorDivide,scoreTextView;
    String userChoice,botChoice;
    QuestionModel questionModel;
    boolean isTimeUp=false;
    String level;
    int score=0;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_game);

        questionModel =new QuestionModel();
        num1=findViewById(R.id.num1);
        num2=findViewById(R.id.num2);
        scoreTextView=findViewById(R.id.scoreText);
        numResult=findViewById(R.id.numResult);
        timeLeft=findViewById(R.id.countDownTimer);
        operator1=findViewById(R.id.operator1);
        operatorAdd=findViewById(R.id.operatorOptionAdd);
        operatorSub=findViewById(R.id.operatorOptionSubtract);
        operatorMul=findViewById(R.id.operatorOptionMul);
        operatorDivide=findViewById(R.id.operatorOptionDivide);
        level=getIntent().getStringExtra("Level");
        setQuestion();
        scoreTextView.setText("0");

        operatorAdd.setOnClickListener(this);
        operatorSub.setOnClickListener(this);
        operatorMul.setOnClickListener(this);
        operatorDivide.setOnClickListener(this);

        setTimer(COUNT_DOWN_TIME*1000);




    }


    private void setQuestion() {
        QuestionModel newQuestion=questionModel.getQuestion(level);

        num1.setText(String.valueOf(newQuestion.num1));
        num2.setText(String.valueOf(newQuestion.num2));
        numResult.setText(String.valueOf(newQuestion.numResult));
        botChoice=newQuestion.answer;
    }
    public void setTimer(long time){
        new CountDownTimer(time, 1000) {

            public void onTick(long millisUntilFinished) {
                timeLeft.setText(String.valueOf(millisUntilFinished / 1000));
            }

            public void onFinish() {
                isTimeUp=true;
                showScoreDialog();

            }
        }.start();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.operatorOptionAdd:
                userChoice="+";
                showResult();
                break;
                case R.id.operatorOptionSubtract:
                userChoice="-";
                    showResult();
                break;
                case R.id.operatorOptionMul:
                userChoice="X";
                    showResult();
                break;
                case R.id.operatorOptionDivide:
                userChoice="/";
                    showResult();
                break;

        }
    }

    private void showResult() {
        operator1.setText(userChoice);
        if(botChoice.equals(userChoice)){
            score++;

            operator1.setTextColor(Color.WHITE);

            operator1.setBackgroundTintList(ColorStateList.valueOf(Color.GREEN));
        }
        else {
            int number1,number2,resultAsPerUser;
            number1=Integer.parseInt(num1.getText().toString());
            number2=Integer.parseInt(num2.getText().toString());

            if(userChoice.equals("+"))
            {
                resultAsPerUser=number1+number2;
            }
            else if(userChoice.equals("-")){
                resultAsPerUser=number1-number2;

            }
            else if(userChoice.equals("X"))
            {
                resultAsPerUser=number1*number2;

            }
            else{
                resultAsPerUser=number1/number2;
            }
            if(String.valueOf(resultAsPerUser).equals(numResult.getText().toString())){
                score++;

                operator1.setTextColor(Color.WHITE);

                operator1.setBackgroundTintList(ColorStateList.valueOf(Color.GREEN));
            }
            else {
                score--;
                operator1.setTextColor(Color.BLACK);
                operator1.setBackgroundTintList(ColorStateList.valueOf(Color.RED));
            }
        }
        scoreTextView.setText(String.valueOf(score));
        Handler handler=new Handler();
        handler.postDelayed(() -> {
            operator1.setTextColor(getColor(R.color.num_back_orange));
            operator1.setBackgroundTintList(ColorStateList.valueOf(Color.WHITE));
            operator1.setText("?");
            setQuestion();
        },200);


//        if(isTimeUp){
//
//        }
//        else {
//
//        }
    }

    private void showScoreDialog() {
        Dialog dialog=new Dialog(GameActivity.this);
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.score_dialog);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        TextView restart, close,dialogScoreText;
        dialogScoreText=dialog.findViewById(R.id.scoreDialogScoreText);
        restart=dialog.findViewById(R.id.scoreDialogRestart);
        close=dialog.findViewById(R.id.scoreDialogClose);
        dialogScoreText.setText(dialogScoreText.getText().toString()+String.valueOf(score));
        restart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setTimer(COUNT_DOWN_TIME*1000);
                score=0;
                scoreTextView.setText("0");
                setQuestion();
                dialog.dismiss();
            }
        });
        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                finish();
            }
        });

        dialog.show();


    }
}