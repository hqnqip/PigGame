package com.example.piggame1;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class DuoPlayer extends AppCompatActivity {

    Button end;
    TextView slideBar;
    boolean isLeft;


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
        end = findViewById(R.id.buttonEnd);
        slideBar = findViewById(R.id.slideBar);
        isLeft = true;

        LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) slideBar.getLayoutParams();

        end.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                params.gravity = Gravity.END;
            }
        });
    }
}