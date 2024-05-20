package com.example.androidprojectcollection;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.Toast;

import java.util.Random;

public class Menus extends AppCompatActivity {

    int[] str_id = {
            R.string.str1,
            R.string.str2,
            R.string.str3,
            R.string.str4,
            R.string.str5,
            R.string.str6,
            R.string.str7,
            R.string.str8,
            R.string.str9,
            R.string.str10,
    };

    int[] color_id = {
            R.color.clr1,
            R.color.clr2,
            R.color.clr3,
            R.color.clr4,
            R.color.clr5,
            R.color.clr6,
            R.color.clr7,
            R.color.clr8,
            R.color.clr9,
            R.color.clr10,
    };
    Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menus);

        btn = (Button) findViewById(R.id.btn);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_exercise,menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        Random r = new Random();
        if (item.getItemId() == R.id.to_change){
            Toast.makeText( this, "Edit Object Item is clicked", Toast.LENGTH_SHORT) .show();
        } else if (item.getItemId() == R.id.to_exit) {
            finish();
        }else if (item.getItemId() == R.id.to_reset) {
            Toast.makeText( this, "Reset Object Item is clicked", Toast.LENGTH_SHORT).show();
            btn.setBackgroundColor(getColor(R.color.light_lavender));
            btn.setText(getText(R.string.str));
            btn.setWidth(875);
            btn.setHeight(875);
            btn.setTextSize(14f);
            btn.setTextColor(Color.BLACK);
            btn.setRotationX(0);
            btn.setRotationY(0);
            btn.setRotation(0);
        } else if (item.getItemId() == R.id.changeTextColor) {
            btn.setTextColor(getColor(color_id[r.nextInt(10)]));
        } else if (item.getItemId() == R.id.changeText) {
            btn.setText(getString(str_id[r.nextInt(10)]));
        } else if (item.getItemId() == R.id.changeSize) {
            btn.setHeight(r.nextInt(1000));
            btn.setWidth(r.nextInt(1000));
        } else if (item.getItemId() == R.id.changeTextSize) {
            btn.setTextSize(r.nextFloat()*100);
        } else if (item.getItemId() == R.id.changeBgColor) {
            btn.setBackgroundColor(getColor(color_id[r.nextInt(10)]));
        } else if (item.getItemId() == R.id.changeRotation) {
            btn.setRotationX(r.nextFloat()*100);
            btn.setRotationY(r.nextFloat()*100);
            btn.setRotation(r.nextFloat()*100);
        }
        return true;
    }
}