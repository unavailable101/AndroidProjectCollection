package com.example.androidprojectcollection;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;

public class PassingIntents extends AppCompatActivity {

    Button clear_form, submit_form;
    EditText fname, lname, birthdate, phone, email, address, yearlevel, program, department, idnumber;
    RadioButton male, female, others;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_passing_intents);

        //buttons
        clear_form = (Button) findViewById(R.id.clear_form);
        submit_form = (Button) findViewById(R.id.submit_form);

        //edit texts
        fname = (EditText) findViewById(R.id.fname);
        lname = (EditText) findViewById(R.id.lname);
        birthdate = (EditText) findViewById(R.id.bdate);
        phone = (EditText) findViewById(R.id.phone);
        email = (EditText) findViewById(R.id.email);
        yearlevel = (EditText) findViewById(R.id.yearlevel);
        program = (EditText) findViewById(R.id.program);
        department = (EditText) findViewById(R.id.department);
        idnumber = (EditText) findViewById(R.id.idnumber);
        address = (EditText) findViewById(R.id.address);

        //radio buttons
        male = (RadioButton) findViewById(R.id.male);
        female = (RadioButton) findViewById(R.id.female);
        others = (RadioButton) findViewById(R.id.others);


        submit_form.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String firstName = fname.getText().toString();
                String lastName = lname.getText().toString();

                String gender;
                if (male.isChecked()) gender = "Male";
                else if (female.isChecked()) gender = "Female";
                else if (others.isChecked()) gender = "Others";
                else gender = "Unknown";

                String bDate = birthdate.getText().toString();
                String pNumber = phone.getText().toString();
                String emailAdd = email.getText().toString();
                String add = address.getText().toString();
                String yrlvl = yearlevel.getText().toString();
                String prog = program.getText().toString();
                String depart = department.getText().toString();
                String idno = idnumber.getText().toString();

                Intent intent = new Intent (PassingIntents.this, PassingIntents2.class);
                intent.putExtra("fname_key", firstName);
                intent.putExtra("lname_key", lastName);
                intent.putExtra("gender_key", gender);
                intent.putExtra("bdate_key", bDate);
                intent.putExtra("pnum_key", pNumber);
                intent.putExtra("email_key", emailAdd);
                intent.putExtra("address_key", add);
                intent.putExtra("yearlvl_key", yrlvl);
                intent.putExtra("prog_key", prog);
                intent.putExtra("department_key", depart);
                intent.putExtra("idnumber_key", idno);

                startActivity(intent);

            }
        });

        clear_form.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fname.setText("First Name");
                lname.setText("Last Name");

                String gender;
                if (male.isChecked()) male.setChecked(false);
                else if (female.isChecked()) female.setChecked(false);
                else if (others.isChecked()) others.setChecked(false);
                else gender = "Unknown";

                birthdate.setText("");
                phone.setText("");
                email.setText("");
                address.setText("");
                yearlevel.setText("");
                program.setText("");
                department.setText("");
                idnumber.setText("");
            }
        });
    }
}