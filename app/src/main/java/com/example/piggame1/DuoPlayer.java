package com.example.piggame1;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class DuoPlayer extends AppCompatActivity {

    //LinearLayout slideBar;
    TextView slideBar;
    int playerTurn;
    Button endButton;

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

        //Attempt at Making Workable Slide-Line.
        //slideBar = findViewById(R.id.slideBox);
        slideBar = findViewById(R.id.slideBar);
        endButton = findViewById(R.id.buttonEnd);
        playerTurn = 1;

        endButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View view) {
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
            }
        });
    }//L

    /*
    public void endTurn(View v)
    {
        if (playerTurn == 1)
        {
            slideBar.setGravity(Gravity.END);
            playerTurn = 2;
        }
        else {
            slideBar.setGravity(Gravity.START);
            playerTurn = 1;
        }
    }
    */
}