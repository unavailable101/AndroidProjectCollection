package com.example.androidprojectcollection;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

//    Button layout, btn_exer, calc, midterm, connect;
    Button[] btns = new Button[6];
    Toast toast;

    Integer[] id = new Integer[] {
            R.id.layout_exer,
            R.id.btn_exer,
            R.id.calc,
            R.id.midterm,
            R.id.connect,
            R.id.passingIntents
    };

    List<Integer> id_list = Arrays.asList(id);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        for (int i = 0; i<id.length; i++){
            btns[i] = (Button) findViewById(id_list.get(i));
            btns[i].setOnClickListener(this);
        }

//        layout = (Button)findViewById(id[0]);
//        btn_exer = (Button)findViewById(id[1]);
//        calc = (Button)findViewById((id[2]));
//        midterm = (Button)findViewById(id[3]);
//        connect = (Button)findViewById(id[4]);
//

        //button Layout Exercise id
//        layout.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                //gateways from one activity to another
//                Intent intent1 = new Intent(
//                        MainActivity.this,//this activity
//                        LayoutExercise.class);//destination activity
//                startActivity(intent1);
//            }
//        });
    }

    @Override
    public void onClick(View view){
        Intent intent;
        switch (id_list.indexOf(view.getId())){
            case 0: //layout exercise
                intent = new Intent(MainActivity.this, LayoutExercise.class);
                startActivity(intent);
                break;
            case 1: // btn exercise
                intent = new Intent(MainActivity.this, ButtonExercise.class);
                startActivity(intent);
                break;
            case 2: // calulator
                intent = new Intent(MainActivity.this, Calculator.class);
                startActivity(intent);
                break;
            case 3: //midterm
                intent = new Intent(MainActivity.this, ColorMatching.class);
                startActivity(intent);
                toast.makeText( getApplicationContext(), "Niña Margarette Catubig\nOpen Color Matching Game", Toast.LENGTH_SHORT).show();
                break;
            case 4: //connect 3
                intent = new Intent(MainActivity.this, Connect3.class);
                startActivity(intent);
                break;
            case 5: //Passing Intents
                intent = new Intent(MainActivity.this, PassingIntents.class);
                startActivity(intent);
                break;
//            case 6: //upcoming activity
//                intent = new Intent(MainActivity.this, PassingIntents.class);
//                startActivity(intent);
//                break;
//            case 7: //upcoming act
//                intent = new Intent(MainActivity.this, PassingIntents.class);
//                startActivity(intent);
//                break;
            default:
                System.out.println("I was called!");
        }

    }
}