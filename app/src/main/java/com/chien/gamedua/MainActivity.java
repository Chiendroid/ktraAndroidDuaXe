package com.chien.gamedua;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Random;

public class MainActivity extends AppCompatActivity {


    TextView tvScoreOne, tvScoreTwo, tvScoreThree;
    ImageView duck1, duck2, duck3;
    Button btnStart, btnReset;


    int scoreOne = 0;
    int scoreTwo = 0;
    int scoreThree = 0;


    int screenWidth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mapping();


        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);

        screenWidth = displayMetrics.widthPixels - 150;

        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                btnStart.setEnabled(false);
                btnReset.setEnabled(false);


                resetPositions();

                runRace();
            }
        });

        // Xử lý sự kiện nút Reset
        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetPositions();
                btnStart.setEnabled(true);
            }
        });
    }

    private void resetPositions() {
        duck1.setTranslationX(0);
        duck2.setTranslationX(0);
        duck3.setTranslationX(0);
    }

    private void runRace() {

        CountDownTimer timer = new CountDownTimer(60000, 50) {
            @Override
            public void onTick(long millisUntilFinished) {
                Random random = new Random();

                int one = random.nextInt(20) + 10;
                int two = random.nextInt(20) + 10;
                int three = random.nextInt(20) + 10;


                duck1.setTranslationX(duck1.getTranslationX() + one);
                duck2.setTranslationX(duck2.getTranslationX() + two);
                duck3.setTranslationX(duck3.getTranslationX() + three);


                if (duck1.getTranslationX() >= screenWidth) {
                    this.cancel();
                    updateScore(1);
                    announceWinner("xe 1 thắng!");
                } else if (duck2.getTranslationX() >= screenWidth) {
                    this.cancel();
                    updateScore(2);
                    announceWinner("xe 2 thắng!");
                } else if (duck3.getTranslationX() >= screenWidth) {
                    this.cancel();
                    updateScore(3);
                    announceWinner("xe 3 thắng!");
                }
            }

            @Override
            public void onFinish() {
                btnStart.setEnabled(true);
                btnReset.setEnabled(true);
            }
        };
        timer.start();
    }


    private void updateScore(int winnerId) {
        switch (winnerId) {
            case 1:
                scoreOne += 10;
                tvScoreOne.setText("Duck 1: " + scoreOne);
                break;
            case 2:
                scoreTwo += 10;
                tvScoreTwo.setText("Duck 2: " + scoreTwo);
                break;
            case 3:
                scoreThree += 10;
                tvScoreThree.setText("Duck 3: " + scoreThree);
                break;
        }
    }

    private void announceWinner(String message) {
        Toast.makeText(MainActivity.this, message, Toast.LENGTH_LONG).show();
        btnStart.setEnabled(false);
        btnReset.setEnabled(true);
    }

    private void mapping() {

        tvScoreOne = findViewById(R.id.score1);
        tvScoreTwo = findViewById(R.id.score2);
        tvScoreThree = findViewById(R.id.score3);


        duck1 = findViewById(R.id.xe1);
        duck2 = findViewById(R.id.xe2);
        duck3 = findViewById(R.id.xe3);

        btnStart = findViewById(R.id.btnStart);
        btnReset = findViewById(R.id.btnReset);
    }
}
