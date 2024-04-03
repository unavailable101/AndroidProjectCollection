package com.example.androidprojectcollection;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import java.util.Random;

public class ButtonExercise  extends AppCompatActivity {

    Button btnClose, btnToast, btnBg, btnBgBtn, btnInvis;
    ConstraintLayout bg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buttons_exercise);

        btnClose = (Button)findViewById(R.id.close);

        btnClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(
                        ButtonExercise.this,
                        EmptyActivity.class
                );
                startActivity(intent);
            }
        });


        btnToast = (Button)findViewById(R.id.poke);
        btnToast.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast toast = Toast.makeText(ButtonExercise.this, "You poke me hmmp!", Toast.LENGTH_SHORT);
                toast.show();
            }
        });

        btnBg = (Button)findViewById(R.id.bg);
        btnBg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bg = (ConstraintLayout)findViewById(R.id.theBG);
                Random random = new Random();
                int color = Color.argb(255, random.nextInt(255), random.nextInt(255), random.nextInt(255));
                bg.setBackgroundColor(color);

            }
        });

        btnBgBtn = (Button)findViewById(R.id.btnbg);
        btnBgBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Random random = new Random();
                int color = Color.argb(255, random.nextInt(255), random.nextInt(255), random.nextInt(255));
                view.setBackgroundColor(color);
            }
        });


        btnInvis = (Button)findViewById(R.id.inbisibol);
        btnInvis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btnInvis.setVisibility(View.INVISIBLE);
            }
        });
    }
}
