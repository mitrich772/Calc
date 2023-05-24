package com.example.calc;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button toExamples = (Button) findViewById(R.id.buttonToExamples);
        Button toInequality = (Button) findViewById(R.id.buttonToInequality);
        View.OnClickListener myb = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch(v.getId()){
                    case R.id.buttonToExamples: Intent setEx = new Intent(MainActivity.this,MainActivity2.class);
                        startActivity(setEx);
                        break;
                    case R.id.buttonToInequality: Intent setIn = new Intent(MainActivity.this,MainActivity3.class);
                        startActivity(setIn);
                        break;}}};
        toExamples.setOnClickListener(myb);
        toInequality.setOnClickListener(myb);
}
}