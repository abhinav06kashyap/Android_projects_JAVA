package com.example.attandancemanagement;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class SignUpActivity extends AppCompatActivity {


    Button IdButton, createAccount;
    EditText fname, lname, pass, phone;
    TextView toLog;
    RadioButton empRb, adminRb;


    final int randomId = new Random().nextInt(9999) + 4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        fname = (EditText) findViewById(R.id.fnEdit);
        lname = (EditText) findViewById(R.id.lnEdit);
        phone = (EditText) findViewById(R.id.phoneEdit);
        pass = (EditText) findViewById(R.id.passEdit);
        empRb = (RadioButton) findViewById(R.id.emp);
        adminRb = (RadioButton) findViewById(R.id.admin);
        IdButton = (Button) findViewById(R.id.GeneratedId);
        createAccount = (Button) findViewById(R.id.Create);
        toLog = (TextView) findViewById(R.id.toLogin);


        //Initialize database
        UserDatabase db = new UserDatabase(this);



        //Random Id for user

        IdButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                IdButton.setText( String.valueOf(randomId));
            }
        });



        //Create account


        createAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //check radio button
                String rbName = null;
                if (empRb.isChecked()) {
                    rbName = empRb.getText().toString();
                }
                else if (adminRb.isChecked()) {
                    rbName = adminRb.getText().toString();
                }
                else {
                    rbName = "";
                }

                if (phone.length()<10)
                {
                    Toast.makeText(SignUpActivity.this, "Enter Valid Phone no.", Toast.LENGTH_SHORT).show();
                    phone.getText().clear();
                }


                //check if your has entered any data or not

                else if (fname.getText().equals("") || lname.getText().equals("") || pass.getText().equals("") || IdButton.getText().equals("Get Id") || rbName.equals("") || phone.getText().equals(""))
                {
                    Toast.makeText(SignUpActivity.this, "Fill all the details please..", Toast.LENGTH_SHORT).show();
                }
                else
                {

                    // Inserting Employees in database

                    db.addUsers(new UserData(Integer.parseInt(IdButton.getText().toString()), fname.getText().toString().trim() + " " + lname.getText().toString().trim(),
                            rbName, pass.getText().toString(), phone.getText().toString()));


                    // Reading all contacts
                    Log.d("Reading: ", "Reading all contacts..");
                    List<UserData> users = db.getAllContacts();

                    for (UserData cn : users) {
                        String log = "Id: " + cn.getId() + " ,Code: " + cn.getCode() + " ,Name: " +
                                cn.getUserName() + " ,Department: " + cn.getDepartment() + " ,Password: "
                                + cn.getPass()+ " ,Phone: " + cn.getPhone() ;


                        Log.d("Data: ", log);
                    }

                    //send new user to Login

                    Intent loginIndent = new Intent(SignUpActivity.this, LoginActivity.class);
                    startActivity(loginIndent);
                    finish();
                    Toast.makeText(SignUpActivity.this, "Registration Successful", Toast.LENGTH_SHORT).show();
                }
            }
        });



        //Go to Login text

        toLog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {

                Intent loginIntent = new Intent(SignUpActivity.this,LoginActivity.class);
                startActivity(loginIntent);
                finish();
            }
        });

    }


}


