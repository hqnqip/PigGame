package com.example.piggame1;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import android.os.Handler;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Random;
import java.util.concurrent.TimeUnit;

public class GameScreen extends AppCompatActivity {

    ImageView dice1;
    ImageView dice2;
    Button rollBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_game_screen);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        dice1 = findViewById(R.id.die1);
        dice2 = findViewById(R.id.die2);
        rollBtn = findViewById(R.id.RollButton);

        Random r = new Random();
        dice1.setImageResource(getDieImage(r.nextInt(6) + 1));
        dice2.setImageResource(getDieImage(r.nextInt(6) + 1));

        rollBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rollDice();
                rollBtn.setEnabled(true);
            }
        });
    }

    @SuppressLint("SetTextI18n")
    public void rollDice()
    {
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
                        dice1.setImageResource(getDieImage(rn.nextInt(6) + 1));
                        dice2.setImageResource(getDieImage(rn.nextInt(6) + 1));
                        rollBtn.setEnabled(false);
                        if(finalI == 14)
                        {
                            rollBtn.setEnabled(true);
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