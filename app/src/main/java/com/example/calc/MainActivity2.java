package com.example.calc;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.os.CountDownTimer;

public class MainActivity2 extends AppCompatActivity {
    public CountDownTimer MyTimer;
    public static int rightAnswer;
    public static String problem;
    public static int userAnswer;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main2);

        Game nGame = new Game();
        TextView MainText = findViewById(R.id.textView);
        TextView CorrectAnswer = findViewById(R.id.textViewCorrect);
        TextView WrongAnswer = findViewById(R.id.textViewWrong);
        EditText inputField = findViewById(R.id.AnsText);
        Button checkAnswerButton = findViewById(R.id.accAnsButton);
        Button buttonToMenu = findViewById(R.id.toMenu);
        TextView TimerText = findViewById(R.id.timerTextView);



        createNewTask(nGame,MainText,checkAnswerButton,inputField,TimerText);

        buttonToMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent BackActivity = new Intent(MainActivity2.this,MainActivity.class);
                try {
                    MyTimer.cancel();
                } catch (NullPointerException e){}
                startActivity(BackActivity);
            }
        });

        checkAnswerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    userAnswer = Integer.parseInt(inputField.getText().toString());
                } catch (Exception error){
                    inputField.setText("");
                    return;
                }
                nGame.checkRight(userAnswer, rightAnswer);
                CorrectAnswer.setText(Integer.toString(nGame.trueAns));
                WrongAnswer.setText(Integer.toString(nGame.falseAns));
                inputField.setText("");
                if((nGame.curAns >= 30) || (nGame.falseAns >= 3)){
                    Intent setx = new Intent(MainActivity2.this,MainActivity.class);
                    try {
                        MyTimer.cancel();
                    } catch (NullPointerException e){}
                    startActivity(setx);
                    nGame.reset();
                }
                else{
                    createNewTask(nGame,MainText,checkAnswerButton,inputField,TimerText);
                }
            }
        });
    }
    public void createNewTask(Game nGame, TextView MainText, Button checkAnswerButton, TextView inputField, TextView TimerText){
        String[] a = nGame.getTask(nGame.difficulty);
        rightAnswer = Integer.parseInt(a[1]);
        problem = a[0];
        MainText.setText(problem);
        int lastCurAns = nGame.curAns;
        MyTimer = new CountDownTimer(nGame.timeToFinish, 1000){
            public void onTick(long millisUntilFinished){
                if(nGame.curAns != lastCurAns){
                    this.cancel();
                }
                else {
                    TimerText.setText(String.valueOf((millisUntilFinished / 1000) + 1));
                }
            }
            public void onFinish(){
                inputField.setText("1000000");
                checkAnswerButton.performClick();
            }
        }.start();
    }
}
