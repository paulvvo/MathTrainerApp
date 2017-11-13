package com.example.sleepy.mathtrainerapp;

import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Random;

import static java.util.Arrays.asList;

public class MainActivity extends AppCompatActivity {

    TextView timerTextView;
    TextView qTextView;
    TextView scoreTextView;
    CountDownTimer timer;
    Button ansButton0,ansButton1,ansButton2,ansButton3,playButton,helpButton;
    int globalAnswer;
    int scoreNum = 0;
    int scoreDen = 0;
    Random rand;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rand = new Random();

        playButton = (Button) findViewById(R.id.playButton);
        helpButton = (Button) findViewById(R.id.helpButton);

        timerTextView = (TextView) findViewById(R.id.timerTextView);
        timerTextView.setEnabled(false);
        timerTextView.setVisibility(View.INVISIBLE);

        qTextView = (TextView) findViewById(R.id.qTextView);
        qTextView.setEnabled(false);
        qTextView.setVisibility(View.INVISIBLE);

        scoreTextView = (TextView) findViewById(R.id.scoreTextView);
        scoreTextView.setEnabled(false);
        scoreTextView.setVisibility(View.INVISIBLE);

        ansButton0 = (Button) findViewById(R.id.ansButton0);
        ansButton1 = (Button) findViewById(R.id.ansButton1);
        ansButton2 = (Button) findViewById(R.id.ansButton2);
        ansButton3 = (Button) findViewById(R.id.ansButton3);
        ansButton0.setVisibility(View.INVISIBLE);
        ansButton1.setVisibility(View.INVISIBLE);
        ansButton2.setVisibility(View.INVISIBLE);
        ansButton3.setVisibility(View.INVISIBLE);
    }

    public void playGame(View view){
        Log.i("test", "play game");
        globalAnswer = setBoard();
        startTimer();

        ansButton0.setVisibility(View.VISIBLE);
        ansButton1.setVisibility(View.VISIBLE);
        ansButton2.setVisibility(View.VISIBLE);
        ansButton3.setVisibility(View.VISIBLE);
        qTextView.setVisibility(View.VISIBLE);
        scoreTextView.setVisibility(View.VISIBLE);
        timerTextView.setVisibility(View.VISIBLE);
        playButton.setVisibility(View.INVISIBLE);
        helpButton.setVisibility(View.INVISIBLE);
        ansButton0.setEnabled(true);
        ansButton1.setEnabled(true);
        ansButton2.setEnabled(true);
        ansButton3.setEnabled(true);
        scoreTextView.setText(Integer.toString(scoreNum) + "/" + Integer.toString(scoreDen));
    }

    public int answerClick(View view){
        int playerAnswer = Integer.parseInt(view.getTag().toString());
        if(playerAnswer == globalAnswer) {
            System.out.println("CORRECT");
            scoreNum++;
            scoreDen++;
            scoreTextView.setText(Integer.toString(scoreNum) + "/" + Integer.toString(scoreDen));
            globalAnswer = setBoard();
        }else {
            System.out.println("INCORRECT");
            scoreDen++;
            scoreTextView.setText(Integer.toString(scoreNum) + "/" + Integer.toString(scoreDen));
            globalAnswer = setBoard();
        }
        return playerAnswer;
    }

    public void startTimer(){
        timer = new CountDownTimer(30000+100, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                timerTextView.setText(Long.toString(millisUntilFinished/1000));
            }

            @Override
            public void onFinish() {
                timerTextView.setText("0");
                playButton.setText("Play Again");
                playButton.setVisibility(View.VISIBLE);
                helpButton.setVisibility(View.VISIBLE);
                ansButton0.setEnabled(false);
                ansButton1.setEnabled(false);
                ansButton2.setEnabled(false);
                ansButton3.setEnabled(false);
                scoreNum=0;
                scoreDen=0;
            }
        }.start();
    }

    public int setBoard(){
        int randPlacement = rand.nextInt(4);
        int a = rand.nextInt(100);
        int b = rand.nextInt(100);
        qTextView.setText(Integer.toString(a) + " + " + Integer.toString(b) + "= ?" );

        if(randPlacement ==0){
            ansButton0.setText(Integer.toString(a+b));
            ansButton1.setText(Integer.toString(rand.nextInt(200)));
            ansButton2.setText(Integer.toString(rand.nextInt(200)));
            ansButton3.setText(Integer.toString(rand.nextInt(200)));
        }
        if(randPlacement ==1){
            ansButton0.setText(Integer.toString(rand.nextInt(200)));
            ansButton1.setText(Integer.toString(a+b));
            ansButton2.setText(Integer.toString(rand.nextInt(200)));
            ansButton3.setText(Integer.toString(rand.nextInt(200)));
        }
        if(randPlacement ==2){
            ansButton0.setText(Integer.toString(rand.nextInt(200)));
            ansButton1.setText(Integer.toString(rand.nextInt(200)));
            ansButton2.setText(Integer.toString(a+b));
            ansButton3.setText(Integer.toString(rand.nextInt(200)));
        }
        if(randPlacement ==3){
            ansButton0.setText(Integer.toString(rand.nextInt(200)));
            ansButton1.setText(Integer.toString(rand.nextInt(200)));
            ansButton2.setText(Integer.toString(rand.nextInt(200)));
            ansButton3.setText(Integer.toString(a+b));
        }
        return randPlacement;

    }

}
