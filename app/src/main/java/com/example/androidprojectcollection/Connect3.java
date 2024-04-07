package com.example.androidprojectcollection;

import static androidx.constraintlayout.widget.Constraints.TAG;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.PaintDrawable;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import android.util.Log;

public class Connect3 extends AppCompatActivity implements View.OnClickListener{

    private final Integer[][] ids = new Integer[][] {
        //row1
        { R.id.r1c1, R.id.r1c2, R.id.r1c3, R.id.r1c4, R.id.r1c5 },
        //row2
        { R.id.r2c1, R.id.r2c2, R.id.r2c3, R.id.r2c4, R.id.r2c5 },
        //row3
        { R.id.r3c1, R.id.r3c2, R.id.r3c3, R.id.r3c4, R.id.r3c5 },
        //row4
        { R.id.r4c1, R.id.r4c2, R.id.r4c3, R.id.r4c4, R.id.r4c5 },
        //row5
        { R.id.r5c1, R.id.r5c2, R.id.r5c3, R.id.r5c4, R.id.r5c5 }
    };
    private final Integer[] cols = new Integer[]{ R.id.c1, R.id.c2, R.id.c3, R.id.c4, R.id.c5 };
    List<Integer> clickable = new ArrayList<>();
    Button[][] all_btns = new Button[5][5];
    Button restart, exit;
    LinearLayout[] column = new LinearLayout[5];
    TextView player;
    int[][] slots = new int[][] {
            {-1,-1,-1,-1,-1}, {-1,-1,-1,-1,-1}, {-1,-1,-1,-1,-1}, {-1,-1,-1,-1,-1}, {-1,-1,-1,-1,-1}
    };
    int[] currentPlayer = { R.string.black, R.string.red };
    int[] curr_player = { Color.BLACK, Color.RED };
    private int cp = 0;
    boolean foundWinner = false;

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.connect3);

        restart = (Button) findViewById(R.id.restart); exit =  (Button) findViewById(R.id.out);
        player = (TextView) findViewById(R.id.currP);

        //for columns, sole purpose is assigning its id
        for (int i = 0; i<5; i++){
            column[i] = (LinearLayout) findViewById(cols[i]);
        }

        //for btns
        for (int i = 0; i<5; i++){
            for (int j = 0; j<5; j++){
                all_btns[i][j] = (Button) findViewById(ids[i][j]);
                if (i == 0){
                    clickable.add(ids[i][j]);
                    all_btns[i][j].setOnClickListener(this);
                }
            }
        }
        player.setText(getString(currentPlayer[cp]));
        restart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //TODO: clear all the colors, make all white ganern hays
                restartGame();
            }
        });
        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
    @Override
    public void onClick(View view){
        int i;
        for (i = -1; i + 1 < 5 && slots[ i + 1 ][ clickable.indexOf(view.getId()) ] == -1; i++) ;
        if ( i != -1 ){
            column[clickable.indexOf(view.getId())].getChildAt(i).setBackgroundColor(curr_player[cp]);
            slots[i][clickable.indexOf(view.getId())] = cp;
            foundWinner = isWinner(i, clickable.indexOf(view.getId()), cp) ;
            if ( !foundWinner) {
                cp = curr(cp);
                player.setText(getString(currentPlayer[cp]));
            }
        }
    }

    private void restartGame(){
        for (int i = 0; i<5; i++){
            for (int j = 0; j<5; j++){
                all_btns[i][j].setBackgroundColor(Color.WHITE);
                slots[i][j] = -1;
                cp = 0;
                player.setText(getString(currentPlayer[cp]));
                foundWinner = false;
            }
        }
    }

    private int curr(int cp){
        return cp == 1 ? 0 : 1;
    }

    private boolean isWinner(int row, int col, int player){
        if ( checkVertical(col, row, player ) || checkHorizontal(col, row, player) || checkDiagonal(col, row, player) ) {
            new CountDownTimer(1000,1000){
                @Override
                public void onTick(long l) {
                    Toast.makeText(getApplicationContext(), getString(currentPlayer[cp]) + " player wins the game!", Toast.LENGTH_SHORT).show();
                }
                @Override
                public void onFinish() {
                    restartGame();
                }
            }.start();
            return true;
        } else if ( isTie() ) {
            new CountDownTimer(1000,1000){
                @Override
                public void onTick(long l) {
                    Toast.makeText(getApplicationContext(), "It's a tie!", Toast.LENGTH_SHORT).show();
                }
                @Override
                public void onFinish() {
                    restartGame();
                }
            }.start();
        }
        return false;
    }

    private boolean checkVertical(int col, int row, int curr){
        return (row < 3 && curr == slots[row+1][col] && curr == slots[row+2][col]);
    }

    private boolean checkHorizontal(int col, int row, int curr){
        int count = 1;
        for (int i = col - 1; i >= 0 && slots[row][i] == curr; i--, count++);
        for (int i = col + 1; i < 5 && slots[row][i] == curr; i++, count++) ;
        return count >= 3;
    }

    private boolean checkDiagonal(int col, int row, int curr){
        int up_down = 1, down_up = 1;
        //for up_down or \
        for (int i = row-1, j = col - 1; i >= 0 && j >= 0 && slots[i][j] == curr; i--, j--, up_down++); //top-left
        for (int i = row+1, j =col+1; i < 5 && j < 5 && slots[i][j] == curr; i++, j++, up_down++); //bottom-right
        //for down_up or /
        for (int i = row-1, j = col + 1; i >= 0 && j < 5 && slots[i][j] == curr; i--, j++, down_up++); //top-right
        for (int i = row+1, j = col - 1; i < 5 && j >= 0 && slots[i][j] == curr; i++, j--, down_up++); //bottom-left

        return Math.max(up_down, down_up) >= 3;
    }

    private boolean isTie(){
        boolean isFull = true;
        for (int i = 0; i < 5; i++){
            for (int j = 0; j<5; j++){
                if (slots[i][j] == -1) {
                    isFull = false;
                    break;
                }
            }
            if (isFull) break;
        }
        return isFull && !foundWinner;
    }
}