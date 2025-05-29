package com.example.attandancemanagement;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.SimpleTimeZone;

public class AttendanceActivity extends AppCompatActivity {

    String InTime, OutTime, Today;
    TextView Username, userCode, inDisplay, outDisplay, date;
    Button InButton, OutButton, backButton;

    String name,code;

    String c = null;
    String d = null,  in = null, ot = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attandance);

        userCode = (TextView) findViewById(R.id.userCode);
        Username = (TextView) findViewById(R.id.userName);
        InButton = (Button) findViewById(R.id.inTime);
        OutButton = (Button) findViewById(R.id.outTime);
        backButton = (Button) findViewById(R.id.back);
        inDisplay = (TextView) findViewById(R.id.InDisplay);
        outDisplay = (TextView) findViewById(R.id.OutDisplay);
        date = (TextView) findViewById(R.id.CurrDate);

    //Initialize database
        UserDatabase db = new UserDatabase(this);


        // Today Date

        Today = new SimpleDateFormat("dd/MM/YY").format(Calendar.getInstance().getTime()).toString();
        date.setText(Today);


        // Fixing Name and Code from bundle

        Bundle user = getIntent().getExtras();
        name = user.getString("name");
        code = user.getString("code");

        Username.setText(name);
        userCode.setText(code);




        List<AttendanceData> TodayList = db.getSingleEmpAttendance(code);

           if(TodayList!=null&&TodayList.size()>0) {


               for (AttendanceData cn : TodayList) {

                   d = cn.getAttDate();
                   c = String.valueOf(cn.getAttCode());
                   in = cn.getAttInTime();
                   ot = cn.getAttOutTime();

               }

                   if (d.equals(Today) && in != null) {
                       InButton.setVisibility(View.INVISIBLE);
                       inDisplay.setText("Your InTime is: " + in);
                       outDisplay.setText("Your OutTime is: " + ot);
                   }

           }


        // InTime Button

     InButton.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View v) {

             InTime = new SimpleDateFormat("HH:mm:ss").format(Calendar.getInstance().getTime()).toString();

             inDisplay.setText("Your InTime is: " + InTime);


             InButton.setVisibility(View.INVISIBLE);


             //check if user has already given intime for the day


                 db.addAttendance(new AttendanceData(Integer.parseInt(code), name, Today, InTime, ""));


                 List<AttendanceData> attendance = db.getAllAttendance();

                 for (AttendanceData cn : attendance) {
                     String log = "Id: " + cn.getAttId() + " ,Code: " + cn.getAttCode() + " ,Name: " +
                             cn.getAttUserName() + " ,Date: " + cn.getAttDate() + " ,Intime: " + cn.getAttInTime()+ " ,Outtime: " + cn.getAttOutTime();

                     Log.d("Attendance: ", log);
                 }

             }

     });

        // OutTime Button

        OutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                OutTime = new SimpleDateFormat("HH:mm:ss").format(Calendar.getInstance().getTime()).toString();

                outDisplay.setText("Your OutTime is: "+OutTime);

                 db.updateAttendance(code, OutTime);

                List<AttendanceData> attendance = db.getAllAttendance();

                for (AttendanceData cn : attendance)
                {
                    String log = "Id: " + cn.getAttId() + " ,Code: " + cn.getAttCode() + " ,Name: " +
                            cn.getAttUserName() + " ,Date: " + cn.getAttDate() + " ,Intime: " + cn.getAttInTime() + " ,Outtime: " + cn.getAttOutTime();

                    Log.d("Attendance: ", log);
                }


                }
        });

        //BackButton

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle dataSend = new Bundle();
                dataSend.putString("code",code);
                dataSend.putString("name",name);
                Intent back = new Intent(AttendanceActivity.this,LoginActivity.class);
                back.putExtras(dataSend);
                startActivity(back);
                finish();
            }
        });



    }
}