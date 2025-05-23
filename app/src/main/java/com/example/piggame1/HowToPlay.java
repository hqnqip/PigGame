package com.example.piggame1;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class HowToPlay extends AppCompatActivity {

    MediaPlayer oink;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_how_to_play);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        //Initiates variable.
        Button back;

        //Connects variable to a specific element.
        back = (Button)findViewById(R.id.buttonBack);
        oink = MediaPlayer.create(this, R.raw.pig_sfx);

        //Event Handler
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                oink.start();
                Intent intent = new Intent(HowToPlay.this, MainActivity.class);
                //^ Draws a connection from one page to another.
                startActivity(intent);
                //^ Activates the connection.
            }
        });
    }
}