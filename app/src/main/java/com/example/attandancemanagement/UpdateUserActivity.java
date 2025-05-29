package com.example.attandancemanagement;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.Serializable;
import java.util.List;

public class UpdateUserActivity extends AppCompatActivity {

    TextView userCode;
    EditText Username, UserPhone;
    Button update, back;

    String code,name, phone, bName, bCode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_user);

        Username = (EditText) findViewById(R.id.nameEdit);
        UserPhone = (EditText) findViewById(R.id.mobileEdit);

        userCode = (TextView) findViewById(R.id.userCodeDisplay);
        update = (Button) findViewById(R.id.UpdateButton);
        back = (Button) findViewById(R.id.backAdmin);


        if (getIntent()!=null) {
            UserData updateUser = (UserData) getIntent().getSerializableExtra("userdata");

            Username.setText(updateUser.getUserName());
            userCode.setText(String.valueOf(updateUser.getCode()));
            UserPhone.setText(updateUser.getPhone());
        }
        else return;


        Bundle admin = getIntent().getExtras();
        bCode = admin.getString("code");
        bName = admin.getString("name");

        //Initialize database
        UserDatabase db = new UserDatabase(this);



        //Update Button

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                name = Username.getText().toString();
                code = userCode.getText().toString();
                phone = UserPhone.getText().toString();
                db.updateUser(name, code, phone);

                List<UserData> users = db.getAllContacts();

                for (UserData cn : users) {
                    String log = "Id: " + cn.getId() + " ,Code: " + cn.getCode() + " ,Name: " +
                            cn.getUserName() + " ,Department: " + cn.getDepartment() + " ,Password: " + cn.getPass()+ " ,Phone: " + cn.getPhone();


                    Log.d("Data: ", log);
                }

                Intent back = new Intent(UpdateUserActivity.this, ShowUsersForUpdateActivity.class);
                startActivity(back);
                finish();


                Toast.makeText(UpdateUserActivity.this, "Updates Successful.", Toast.LENGTH_SHORT).show();
            }
        });


        // Back Button

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle dataSend = new Bundle();
                dataSend.putString("code",bCode);
                dataSend.putString("name",bName);
                Intent back = new Intent(UpdateUserActivity.this, AdminHomeActivity.class);
                back.putExtras(dataSend);
                startActivity(back);
                finish();
            }
        });




    }
}