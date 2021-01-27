package com.example.guessinggamev2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import android.media.MediaPlayer;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private int leftNumber;
    private int rightNumber;
    private int points = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        roll();

    }

    public void setRightButton(View view){
        check(rightNumber, leftNumber);
    }

    public void setLeftButton(View view){
        check(leftNumber, rightNumber);
    }

    public void resetGameButton(View view){
        points = 0;
        setPoints();
        Toast.makeText(this, "Game Restarted", Toast.LENGTH_SHORT).show();
    }

    public void setPoints(){
        TextView pointsView = (TextView) findViewById(R.id.pointsText);
        pointsView.setText("Points: " + points);
    }

    private void roll(){
        Random random = new Random();

        leftNumber = random.nextInt(20);
        rightNumber = random.nextInt(20);

        while(leftNumber == rightNumber){
            leftNumber = random.nextInt(20);
        }

        Button rightButton = (Button)findViewById(R.id.rightButton);
        Button leftButton = (Button)findViewById(R.id.leftButton);
        leftButton.setText(""+leftNumber);
        rightButton.setText(""+ rightNumber);

    }

    private void check(int leftNumber, int rightNumber){
        if (leftNumber > rightNumber){
            points++;
            Toast.makeText(this, "You got it right", Toast.LENGTH_SHORT).show();
            MediaPlayer win = MediaPlayer.create(this,R.raw.win);
            win.start();
        }
        else{
            points--;
            Toast.makeText(this, "IDIOT", Toast.LENGTH_SHORT).show();
            MediaPlayer lose = MediaPlayer.create(this, R.raw.lose);
            lose.start();
        }
        if (points == 11){
            Toast.makeText(this, "You have Completed", Toast.LENGTH_SHORT).show();
            points = 0;
        }

        setPoints();
        roll();
    }


}