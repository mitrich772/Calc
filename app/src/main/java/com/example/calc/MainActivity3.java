package com.example.calc;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity3 extends AppCompatActivity {
    public CountDownTimer MyTimer;
    public static String problem;
    public static int rightAnswer;
    public static int userAnswer;
    Game nGame = new Game();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        TextView MainText = findViewById(R.id.textViewMainIn);
        TextView TimerText = findViewById(R.id.timerTextView);
        TextView CorrectAnswer = findViewById(R.id.textViewCorrect);
        TextView WrongAnswer = findViewById(R.id.textViewWrong);
        Button acceptBu = findViewById(R.id.accAnsButton);
        Button rejectBu = findViewById(R.id.wrongAnsButton);
        Button buttonToMenu = findViewById(R.id.toMenu);
        createNewTask(nGame,MainText,CorrectAnswer,WrongAnswer,TimerText);

        buttonToMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent setx = new Intent(MainActivity3.this,MainActivity.class);
                try {
                    MyTimer.cancel();
                } catch (NullPointerException e){}
                startActivity(setx);
            }
        });

        View.OnClickListener InequalityListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch(v.getId()){
                    case R.id.accAnsButton:
                        userAnswer = 1;
                        break;
                    case R.id.wrongAnsButton:
                        userAnswer = 0;
                        break;
                }
                nGame.checkRight(userAnswer, rightAnswer);
                CorrectAnswer.setText(Integer.toString(nGame.trueAns));
                WrongAnswer.setText(Integer.toString(nGame.falseAns));
                if((nGame.curAns >= 30) || (nGame.falseAns >= 3)){
                    Intent setx = new Intent(MainActivity3.this,MainActivity.class);
                    try {
                        MyTimer.cancel();
                    } catch (NullPointerException e){}
                    startActivity(setx);
                    nGame.reset();
                }
                else{
                    createNewTask(nGame,MainText,CorrectAnswer,WrongAnswer,TimerText);
                }
            }
        };

        acceptBu.setOnClickListener(InequalityListener);
        rejectBu.setOnClickListener(InequalityListener);
    }
    public void createNewTask(Game nGame, TextView MainText, TextView CorrectAnswer, TextView WrongAnswer, TextView TimerText){
        String[] a = nGame.getInequality(nGame.difficulty);
        rightAnswer = Integer.parseInt(a[1]);
        problem = a[0];
        MainText.setText(problem);
        int lastCurAns = nGame.curAns;
        new CountDownTimer(nGame.timeToFinish, 1000){
            public void onTick(long millisUntilFinished){
                if(nGame.curAns != lastCurAns){
                    this.cancel();
                }
                else {
                    TimerText.setText(String.valueOf((millisUntilFinished / 1000) + 1));
                }
            }
            public void onFinish(){
                nGame.timeIsOut();
                CorrectAnswer.setText(Integer.toString(nGame.trueAns));
                WrongAnswer.setText(Integer.toString(nGame.falseAns));
                if((nGame.curAns >= 16) || (nGame.falseAns >= 3)){
                    Intent setx = new Intent(MainActivity3.this,MainActivity.class);
                    startActivity(setx);
                    nGame.reset();
                }
                else{
                    createNewTask(nGame,MainText,CorrectAnswer,WrongAnswer,TimerText);
                }
            }
        }.start();
    }
}
