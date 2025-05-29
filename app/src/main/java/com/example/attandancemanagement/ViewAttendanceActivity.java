package com.example.attandancemanagement;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class ViewAttendanceActivity extends AppCompatActivity {

    Spinner empNameSpin;

    RecyclerView attendanceRecycler ;

    Button viewButton, back;

    TextView empName, emp;
    LinearLayout viewAttendanceLinear;
    String code, Usercode, UserDepart, Empname,name;
    ArrayList<String> allEmps= new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_attendance);

        attendanceRecycler = (RecyclerView) findViewById(R.id.attendanceRecyclerView);
        empNameSpin = (Spinner) findViewById(R.id.empSpinner);
        viewButton = (Button) findViewById(R.id.view_Attendance);
        empName = (TextView) findViewById(R.id.empNameDisplay);
        emp = (TextView) findViewById(R.id.empName);

        back = (Button) findViewById(R.id.backView);
        viewAttendanceLinear = (LinearLayout) findViewById(R.id.LinearRecycler);
        allEmps.add("Select");


        //Initialize database
        UserDatabase db = new UserDatabase(this);


        // Get data from Bundle
        Bundle user = getIntent().getExtras();
        code = user.getString("code");
        name = user.getString("name");



        List<UserData> userNames = db.getAllContacts();
        for (UserData cn : userNames) {

             Usercode = String.valueOf(cn.getCode());
             UserDepart = cn.getDepartment();


             allEmps.add(Usercode);
             allEmps.remove(code);

        }


        //Employee name Spinner
        ArrayAdapter<String> empAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, allEmps);
        empAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        empNameSpin.setAdapter(empAdapter);


        //All attendance display
        List<AttendanceData> allAttendanceList = db.getAllAttendance();
        emp.setVisibility(View.INVISIBLE);
        empName.setText("All Attendance");
        if(allAttendanceList.size() > 0 ) {
            AttendanceAdapter allAttendanceAdapter = new AttendanceAdapter(viewAttendanceLinear, allAttendanceList);
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
            attendanceRecycler.setLayoutManager(linearLayoutManager);
            attendanceRecycler.setAdapter(allAttendanceAdapter);
        }


        // View Attendance button

        viewButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                if(empNameSpin.getSelectedItem() == "Select"){
                    if(allAttendanceList.size() > 0 ) {
                        AttendanceAdapter allAttendanceAdapter = new AttendanceAdapter(viewAttendanceLinear, allAttendanceList);
                        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
                        attendanceRecycler.setLayoutManager(linearLayoutManager);
                        attendanceRecycler.setAdapter(allAttendanceAdapter);
                    }

                }
                else {
                    emp.setVisibility(View.VISIBLE);
                    String UserCode = empNameSpin.getSelectedItem().toString();

                    List<AttendanceData> attendanceDataList = db.getSingleEmpAttendance(UserCode);
                    for (AttendanceData cn : attendanceDataList) {
                        Empname = cn.getAttUserName();
                        empName.setText(Empname);
                        String log = "Id: " + cn.getAttId() + " ,Code: " + cn.getAttCode() + " ,Name: " +
                                cn.getAttUserName() + " ,Date: " + cn.getAttDate() + " ,Intime: " + cn.getAttInTime() + " ,Outtime: " + cn.getAttOutTime();

                        Log.d("Attendance: ", log);
                    }

                    AttendanceAdapter attendanceAdapter = new AttendanceAdapter(viewAttendanceLinear, attendanceDataList);
                    LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
                    attendanceRecycler.setLayoutManager(linearLayoutManager);
                    attendanceAdapter.notifyDataSetChanged();
                    attendanceRecycler.setAdapter(attendanceAdapter);

                }
            }
        });

        // Back Button

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle admin = new Bundle();
                admin.putString("code",code);
                admin.putString("name",name);
                Intent back = new Intent(ViewAttendanceActivity.this, AdminHomeActivity.class);
                back.putExtras(admin);
                startActivity(back);
                finish();
            }
        });

    }
}