package com.example.piggame1;

import android.annotation.SuppressLint;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Random;

public class DuoPlayer extends AppCompatActivity {
    //LinearLayout slideBar;
    TextView slideBar;
    int playerTurn;
    Button endButton;
    ImageView dice1;
    ImageView dice2;
    Button rollBtn;
    int points1 = 0;
    int points2 = 0;
    int startingPoint = 0;
    int rolls = 0;
    TextView player1;
    TextView player2;

    //make 2 die objects to more easily look at the side the animated die land on
    Die d1 = new Die();
    Die d2 = new Die();

    MediaPlayer diceSFX;
    MediaPlayer oink;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_duo_player);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });


        diceSFX = MediaPlayer.create(this, R.raw.dice_sfx);
        oink = MediaPlayer.create(this, R.raw.pig_sfx);


        //Attempt at Making Workable Slide-Line.
        //slideBar = findViewById(R.id.slideBox);
        slideBar = findViewById(R.id.slideBar);
        endButton = findViewById(R.id.buttonEnd);
        playerTurn = 1;

        endButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View view) {
                oink.start();
                if (playerTurn == 1) {
                    Animation slideRight = AnimationUtils.loadAnimation(DuoPlayer.this, R.anim.slide_right);
                    slideBar.startAnimation(slideRight);
                    playerTurn = 2;
                    rolls = 0;
                }
                else {
                    Animation slideLeft = AnimationUtils.loadAnimation(DuoPlayer.this, R.anim.slide_left);
                    slideBar.startAnimation(slideLeft);
                    playerTurn = 1;
                    rolls = 0;
                }
            }
        });

        player1 = findViewById(R.id.textPlayer1Score);
        player2 = findViewById(R.id.textPlayer2Score);

        dice1 = findViewById(R.id.die1);
        dice2 = findViewById(R.id.die2);
        rollBtn = findViewById(R.id.buttonRoll);

        Random r = new Random();
        dice1.setImageResource(getDieImage(r.nextInt(6) + 1));
        dice2.setImageResource(getDieImage(r.nextInt(6) + 1));

        rollBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                diceSFX.start();
                rollDice();
                rollBtn.setEnabled(true);
                endButton.setEnabled(true);

                if(points1 >= 100 || points2 >= 100)
                {
                    if (playerTurn == 1)
                    {
                        Toast.makeText(DuoPlayer.this, "Player 1 WON!", Toast.LENGTH_SHORT).show();
                    }
                    else
                    {
                        Toast.makeText(DuoPlayer.this, "Player 2 WON!", Toast.LENGTH_SHORT).show();
                    }
                }

            }
        });

        slideBar = findViewById(R.id.slideBar);
        playerTurn = 1;
    }

    public void endTurn()
    {
        if (playerTurn == 1) {
            Animation slideRight = AnimationUtils.loadAnimation(DuoPlayer.this, R.anim.slide_right);
            slideBar.startAnimation(slideRight);
            playerTurn = 2;
        }
        else {
            Animation slideLeft = AnimationUtils.loadAnimation(DuoPlayer.this, R.anim.slide_left);
            slideBar.startAnimation(slideLeft);
            playerTurn = 1;
        }
        rolls = 0;
    }

    @SuppressLint("SetTextI18n")
    public void rollDice()
    {
        if(rolls == 0)
        {
            System.out.println("Roll check" + rolls);
            rolls = 1000;
            if(playerTurn == 1)
            {
                startingPoint = points1;
                System.out.println(startingPoint);
            }
            else
                startingPoint = points2;
        }
        Random rn = new Random();
        for (int i = 0; i < 15; i++) {
            final Handler handler = new Handler();
            int finalI = i;
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    //Do something after 1s
                    //dice1.setText("" + (rn.nextInt(6) + 1));
                    //dice2.setText("" + (rn.nextInt(6) + 1));
                    //done

                    d1.setSide(rn.nextInt(6) + 1);
                    d2.setSide(rn.nextInt(6) + 1);

                    dice1.setImageResource(getDieImage(d1.getSide()));
                    dice2.setImageResource(getDieImage(d2.getSide()));


                    //store the player's score before adding


                    //change image of the dice
                    d1.setSide(rn.nextInt(6) + 1);
                    d2.setSide(rn.nextInt(6) + 1);
                    dice1.setImageResource(getDieImage(d1.getSide()));
                    dice2.setImageResource(getDieImage(d2.getSide()));

                    //make it so that buttons cannot be pressed during a roll

                    rollBtn.setEnabled(false);
                    endButton.setEnabled(false);
                    if(finalI == 14)
                    {
                        if(d1.getSide() == 1|| d2.getSide() == 1)
                        {

     

                            if(d1.getSide() == 1 && d2.getSide() == 1)
                            {

                                if(playerTurn == 1)
                                {
                                    points1 = 0;
                                    player1.setText("Score: " + points1);
                                    startingPoint = 0;
                                }
                                else
                                {
                                    points2 = 0;
                                    player2.setText("Score: " + points2);
                                    startingPoint= 0;
                                }
                                endTurn();
                                rolls = 0;
                            }
                            else
                            {

                                if(playerTurn == 1)
                                {
                                    player1.setText("Score: " + startingPoint);
                                    points1= startingPoint;
                                }
                                else
                                {
                                    player2.setText("Score: " + startingPoint);
                                    points2 = startingPoint;
                                }
                                endTurn();
                                rolls = 0;
                            }

                        }
                        else
                        {
                            if(playerTurn == 1)
                            {
                                points1+= d1.getSide() + d2.getSide();
                                player1.setText("Score: " + points1);
                            }
                            else
                            {
                                points2+= d1.getSide() + d2.getSide();
                                player2.setText("Score: " + points2);
                            }
                            System.out.println(startingPoint);
                        }
                        rollBtn.setEnabled(true);
                        endButton.setEnabled(true);

                    }
                }
            }, 50 * (2 * i + 1));
        }

    }//end roll dice

    public int getDieImage(int n)
    {
        if (n == 1)
        {
            return R.drawable.one;
        }
        else if(n == 2)
        {
            return R.drawable.two;
        }
        else if(n == 3)
        {
            return R.drawable.three;
        }
        else if(n == 4)
        {
            return R.drawable.four;
        }
        else if(n == 5)
        {
            return R.drawable.five;
        }
        return R.drawable.six;
    }
}
