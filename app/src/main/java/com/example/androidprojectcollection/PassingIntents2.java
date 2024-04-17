package com.example.androidprojectcollection;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class PassingIntents2 extends AppCompatActivity {

    Button balik;
    TextView[] results = new TextView[11];

    int[] txts_id = {
            R.id.firstName,
            R.id.lastName,
            R.id.gender,
            R.id.birthdate,
            R.id.phoneNumber,
            R.id.emailAdd,
            R.id.residence,
            R.id.yrlvl,
            R.id.prog,
            R.id.depart,
            R.id.idnum
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_passing_intents2);

        for (int i = 0; i< txts_id.length; i++){
            results[i] = (TextView) findViewById(txts_id[i]);
        }

        Intent intent = getIntent();

        String[] inputs = new String[11];

        inputs[0] = intent.getStringExtra("fname_key");
        inputs[1] = intent.getStringExtra("lname_key");
        inputs[2] = intent.getStringExtra("gender_key");
        inputs[3] = intent.getStringExtra("bdate_key");
        inputs[4] = intent.getStringExtra("pnum_key");
        inputs[5] = intent.getStringExtra("email_key");
        inputs[6] = intent.getStringExtra("address_key");
        inputs[7] = intent.getStringExtra("yearlvl_key");
        inputs[8] = intent.getStringExtra("prog_key");
        inputs[9] = intent.getStringExtra("department_key");
        inputs[10] = intent.getStringExtra("idnumber_key");

        for (int i = 0; i< 11; i++){
            results[i].setText(inputs[i]);
        }

        balik = (Button) findViewById(R.id.balik);
        balik.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }
}