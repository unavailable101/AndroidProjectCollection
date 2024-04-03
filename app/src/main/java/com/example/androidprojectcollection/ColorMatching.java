package com.example.androidprojectcollection;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Random;

public class ColorMatching extends AppCompatActivity implements View.OnClickListener {

    Button exit, ret;
    Button[][] btn = new Button[3][3];

    int[] btn_id = {
        R.id.btn1,
        R.id.btn2,
        R.id.btn3,
        R.id.btn4,
        R.id.btn5,
        R.id.btn6,
        R.id.btn7,
        R.id.btn8,
        R.id.btn9
    };

    int[] colors = {
            Color.CYAN,
            Color.BLACK,
            Color.BLUE
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.color_matching);

        ret = (Button) findViewById(R.id.ret);
        exit = (Button) findViewById(R.id.exit);


        Random random = new Random();
        int k = 0;
        for (int i = 0; i<3; i++){
            for (int j = 0; j<3; j++){
                int random_color = random.nextInt(colors.length);
                btn[i][j] = (Button) findViewById(btn_id[k++]);
                btn[i][j].setOnClickListener(this);
                btn[i][j].setBackgroundColor(colors[random_color]);
            }
        }
        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        ret.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                for(int i = 0; i<3; i++){
                    for (int j = 0; j<3; j++){
                        int random_color = random.nextInt(colors.length);
                        btn[i][j].setBackgroundColor(colors[random_color]);
                    }
                }
            }
        });
    }
    @Override
    public void onClick(View v){
//        Toast.makeText(getApplicationContext(), "it clicked!" + v.getId(), Toast.LENGTH_SHORT).show();
        int curr = v.getId();
        int curr_in = 0;
        for (int i = 0; i<btn_id.length; i++){
            if (curr == btn_id[i]) curr_in = i;
        }
        if (!check()) {
            changeColor(curr_in);
        } else {
            Toast.makeText(getApplicationContext(), "YOU WIN!", Toast.LENGTH_SHORT).show();
        }
    }

    public void changeColor(int ind){
        for (int i = 0; i<3; i++){//row
            for(int j = 0; j<3; j++){//col
                if (btn[i][j].getId() == btn_id[ind]){
                    if (i > 0){
                        btn[i-1][j].setBackgroundColor(colors[(btn[i-1][j].getSolidColor()+1)%3]);
                    }
                    if (i < 0){
                        btn[i+1][j].setBackgroundColor(colors[(btn[i+1][j].getSolidColor()+1)%3]);
                    }
                    if (j > 0){
                        btn[i][j-1].setBackgroundColor(colors[(btn[i][j-1].getSolidColor()+1)%3]);
                    }
                    if ( j < 0 ){
                        btn[i][j+1].setBackgroundColor(colors[(btn[i][j+1].getSolidColor()+1)%3]);
                    }
                }
            }
        }
    }

    public boolean check(){
        Button intitial = btn[0][0];
        for (int i=0; i<3; i++){
            for (int j = 0; j<3; j++){
                if (btn[i][j] != intitial) return false;
            }
        }

        return true;
    }
}
