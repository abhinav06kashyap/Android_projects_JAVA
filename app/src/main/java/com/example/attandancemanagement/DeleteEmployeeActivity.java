package com.example.attandancemanagement;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.List;

public class DeleteEmployeeActivity extends AppCompatActivity {

    Button back,delete;
    RecyclerView deleteRecyclerView;
    LinearLayout deleteEmpLinear;
    List<UserData> deleteEmpList;

    String datacode,name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_employee);
        back = (Button) findViewById(R.id.backDelete);
        deleteRecyclerView = (RecyclerView) findViewById(R.id.deleteRecyclerView);
        delete = (Button) findViewById(R.id.deleteUsers);



        // Set Admin name and code from bundle
        Bundle homeadmin = getIntent().getExtras();
        datacode = homeadmin.getString("code");
        name = homeadmin.getString("name");

        //Initialize database
        UserDatabase db = new UserDatabase(this);



        //Populate Recycler View

        Bundle admin = getIntent().getExtras();
        int code = Integer.parseInt(admin.getString("code"));

         deleteEmpList = db.getUsersToDeleteList(code);

        DeleteEmployeeAdapter deleteEmployeeAdapter = new DeleteEmployeeAdapter(deleteEmpLinear, deleteEmpList);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        deleteRecyclerView.setLayoutManager(linearLayoutManager);
        deleteEmployeeAdapter.notifyDataSetChanged();
        deleteRecyclerView.setAdapter(deleteEmployeeAdapter);



        //Delete Button

       // db.deleteContact(deleteCode);

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                for (UserData cn : deleteEmpList) {
                    int deleteCode;
                    if (cn.is_selected) {
                        deleteCode = cn.getCode();
                        db.deleteContact(deleteCode);

                        String log = "Id: " + cn.getId() + " ,Code: " + cn.getCode() + " ,Name: " +
                                cn.getUserName() + " ,Department: " + cn.getDepartment() + " ,Password: " + cn.getPass() + ", IsSelected:" + cn.is_selected;


                        Log.d("User: ", log);
                        Bundle dataSend = new Bundle();
                        dataSend.putString("code",datacode);
                        dataSend.putString("name",name);
                        Intent login = new Intent(DeleteEmployeeActivity.this, AdminHomeActivity.class);
                        login.putExtras(dataSend);
                        startActivity(login);
                        Toast.makeText(DeleteEmployeeActivity.this, "delete succesfull", Toast.LENGTH_SHORT).show();
                        break;

                    }
                    else
                    {
                        Toast.makeText(DeleteEmployeeActivity.this, "Select to delete", Toast.LENGTH_SHORT).show();
                    }


                }



                List<UserData> users = db.getAllContacts();

                for (UserData c : users) {
                    String log = "Id: " + c.getId() + " ,Code: " + c.getCode() + " ,Name: " +
                            c.getUserName() + " ,Department: " + c.getDepartment() + " ,Password: " + c.getPass();


                    Log.d("Data: ", log);
                }

            }
        });


        // Back Button

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle dataSend = new Bundle();
                dataSend.putString("code",datacode);
                dataSend.putString("name",name);
                Intent back = new Intent(DeleteEmployeeActivity.this, AdminHomeActivity.class);
                back.putExtras(dataSend);
                startActivity(back);
            }
        });

    }
}