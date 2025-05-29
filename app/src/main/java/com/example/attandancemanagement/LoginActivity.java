package com.example.attandancemanagement;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class LoginActivity extends AppCompatActivity {

    TextView signupText;
    EditText uName, Pass;
    Button LoginButton;

    String userCode, userPass ;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        signupText = (TextView) findViewById(R.id.signupText);
        uName = (EditText) findViewById(R.id.userLogin);
        Pass = (EditText) findViewById(R.id.passLogin);
        LoginButton = (Button) findViewById(R.id.loginButton);



        //Initialize database
        UserDatabase db = new UserDatabase(this);


        LoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userCode = uName.getText().toString().trim();
                userPass = Pass.getText().toString().trim();

                //check for data
                if (userCode.equals("") || userPass.equals(""))
                {
                    Toast.makeText(LoginActivity.this, "Enter data first..", Toast.LENGTH_SHORT).show();
                }

                else {
                    List<UserData> list = db.matchUser(userCode, userPass);
                    if (list.isEmpty())
                    {
                        Toast.makeText(LoginActivity.this, "Signup, you are not a user.", Toast.LENGTH_SHORT).show();
                    }
                    else
                    {

                        for (UserData cn : list) {

                            String name = cn.getUserName();
                            String department = cn.getDepartment();



                            if (department.equals("Employee")) {
                                Bundle user = new Bundle();
                                user.putString("name", name);
                                user.putString("code", userCode);
                                Intent allowEmp = new Intent(LoginActivity.this, AttendanceActivity.class);
                                allowEmp.putExtras(user);
                                startActivity(allowEmp);
                                finish();
                                Toast.makeText(LoginActivity.this, "Welcome " + name, Toast.LENGTH_SHORT).show();
                            }
                            else if (department.equals("Admin"))
                            {

                                Bundle admin = new Bundle();
                                admin.putString("code", userCode);
                                admin.putString("name",name);
                                Intent allowAdmin = new Intent(LoginActivity.this, AdminHomeActivity.class);
                                allowAdmin.putExtras(admin);
                                startActivity(allowAdmin);
                                finish();
                                Toast.makeText(LoginActivity.this, "Welcome " + name, Toast.LENGTH_SHORT).show();
                            }


                        }
                    }
                }

            }


        });

        signupText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent loginIntent = new Intent(LoginActivity.this,SignUpActivity.class);
                startActivity(loginIntent);
                finish();
            }
        });
    }
}