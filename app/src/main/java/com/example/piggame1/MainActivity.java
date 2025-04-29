package com.example.piggame1;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Die d = new Die();
        d.roll();
        System.out.println(d.getSide());

        //MAIN MENU BUTTONS:
        //Initiates variable.
        Button howTo;
        Button duoPlayer;

        //Connects variable to a specific element.
        howTo = (Button)findViewById(R.id.buttonHowTo);
        duoPlayer = (Button)findViewById(R.id.buttonPlayDuo);

        //Event Handler
        howTo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, HowToPlay.class);
                //^ Draws a connection from one page to another.
                startActivity(intent);
                //^ Activates the connection.
            }
        });

        //Event Handler
        duoPlayer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, DuoPlayer.class);
                //^ Draws a connection from one page to another.
                startActivity(intent);
                //^ Activates the connection.
            }
        });
    }
}