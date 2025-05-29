package com.example.attandancemanagement;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class AdminHomeActivity extends AppCompatActivity {


    String code,name;
    TextView AdminCode, AdminName;
    Button viewAttendance, addEmployee, editEmployee, deleteEmployee;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_home);

        viewAttendance = (Button) findViewById(R.id.view_Button);
        addEmployee = (Button) findViewById(R.id.add_Button);
        editEmployee = (Button) findViewById(R.id.edit_Button);
        deleteEmployee = (Button) findViewById(R.id.delete_Button);
        AdminName = (TextView) findViewById(R.id.adminName);
        AdminCode = (TextView) findViewById(R.id.adminCode);


        // Set Admin name and code from bundle
        Bundle admin = getIntent().getExtras();
        code = admin.getString("code");
        name = admin.getString("name");

        AdminCode.setText(code);
        AdminName.setText(name);

        // View Attendance Button

        viewAttendance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Bundle viewAttendance = new Bundle();
                viewAttendance.putString("code",code);
                viewAttendance.putString("name",name);
                Intent viewIntent = new Intent(AdminHomeActivity.this, ViewAttendanceActivity.class);
                viewIntent.putExtras(viewAttendance);
                startActivity(viewIntent);
            }
        });


        // Add Employee Button

        addEmployee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent addIntent = new Intent(AdminHomeActivity.this,SignUpActivity.class);
                startActivity(addIntent);
            }
        });



        // Delete Employee Button

        deleteEmployee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Bundle codeSend = new Bundle();
                codeSend.putString("code",code);
                codeSend.putString("name",name);
                Intent delete = new Intent(AdminHomeActivity.this,DeleteEmployeeActivity.class);
                delete.putExtras(codeSend);
                startActivity(delete);
            }
        });


        //Edit Employee Data

        editEmployee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle dataSend = new Bundle();
                dataSend.putString("code",code);
                dataSend.putString("name",name);
                Intent editIntent = new Intent(AdminHomeActivity.this,ShowUsersForUpdateActivity.class);
                editIntent.putExtras(dataSend);
                startActivity(editIntent);
            }
        });

    }
}