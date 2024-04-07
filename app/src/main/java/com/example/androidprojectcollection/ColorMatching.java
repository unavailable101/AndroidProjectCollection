package com.example.androidprojectcollection;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Random;

public class ColorMatching extends AppCompatActivity implements View.OnClickListener {

    Button exit, ret;
    Button[][] btn = new Button[3][3];
    TextView secret_cheat;
    int[][] btn_id = {
            { R.id.btn1, R.id.btn2, R.id.btn3 },
            { R.id.btn4, R.id.btn5, R.id.btn6 },
            { R.id.btn7, R.id.btn8, R.id.btn9 }
    };
    int[] colors = {
            R.color.rand1,
            R.color.rand2,
            R.color.rand3
    };
    int[][] blocks = new int[3][3];
    Random random = new Random();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.color_matching);

        ret = (Button) findViewById(R.id.ret);
        exit = (Button) findViewById(R.id.exit);
        secret_cheat = (TextView) findViewById(R.id.secret_cheat);

        for (int i = 0; i<3; i++){
            for (int j = 0; j<3; j++){
                int random_color = random.nextInt(colors.length);
                btn[i][j] = (Button) findViewById(btn_id[i][j]);
                btn[i][j].setBackgroundColor( getColor(colors[random_color]) );
                blocks[i][j] = random_color;
                btn[i][j].setOnClickListener(this);
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
                playAgain();
            }
        });

        secret_cheat.setOnClickListener(this);
    }
    @Override
    public void onClick(View v){

        if (v.getId() == R.id.secret_cheat){
            int random_color = random.nextInt(colors.length);
            for (int i = 0; i<3; i++){
                for (int j = 0; j<3; j++){
                    btn[i][j].setBackgroundColor( getColor(colors[random_color]) );
                    blocks[i][j] = random_color;
                }
            }
        }
        //i = row
        //j = col
        if ( !colorMatch() ){
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (btn[i][j].getId() == v.getId()) {
                        if (i > 0)
                            btn[i - 1][j].setBackgroundColor( getColor(colors[blocks[i - 1][j] = nextColor(blocks[i - 1][j])]) );
                        if (i < 2)
                            btn[i + 1][j].setBackgroundColor( getColor(colors[blocks[i + 1][j] = nextColor(blocks[i + 1][j])]) );
                        if (j > 0)
                            btn[i][j - 1].setBackgroundColor( getColor(colors[blocks[i][j - 1] = nextColor(blocks[i][j - 1])]) );
                        if (j < 2)
                            btn[i][j + 1].setBackgroundColor( getColor(colors[blocks[i][j + 1] = nextColor(blocks[i][j + 1])]) );
                        break;
                    }
                }
            }
        } else {
            new CountDownTimer(1000, 1000){
                @Override
                public void onTick(long l) {
                    Toast.makeText(getApplicationContext(), "You Win! All blocks are matched!", Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onFinish() {
                    playAgain();
                }
            }.start();
        }
    }

    private int nextColor(int curr){
        return curr+1 < colors.length ? (curr+1) : 0;
    }

    private boolean colorMatch(){
        int initial = blocks[0][0];
        int count = 1;
        for (int i = 0; i<3; i++){
            for (int j = 0; j<3; j++){
                if (initial == blocks[i][j]) count++;
            }
        }
        return count == 10;
    }

    public void playAgain(){
        for(int i = 0; i<3; i++){
            for (int j = 0; j<3; j++){
                int random_color = random.nextInt(colors.length);
                btn[i][j].setBackgroundColor( getColor(colors[random_color]) );
                blocks[i][j] = random_color;
            }
        }
    }
}