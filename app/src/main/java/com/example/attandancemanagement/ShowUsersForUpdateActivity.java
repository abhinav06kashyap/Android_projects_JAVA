package com.example.attandancemanagement;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import java.util.List;

public class ShowUsersForUpdateActivity extends AppCompatActivity {

    RecyclerView selectRecyclerView;
    Button back;
   Activity context;

   String code, name;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_users_for_update);
        selectRecyclerView = (RecyclerView) findViewById(R.id.selectUserRecycler);
        back = (Button) findViewById(R.id.backHome) ;


        Bundle admin = getIntent().getExtras();
        code = admin.getString("code");
        name = admin.getString("name");

        //Initialize database
        UserDatabase db = new UserDatabase(this);



        //Populate Recycler View

        List<UserData> selectUserList = db.getUsersToSelectList();
        SelectUserAdapter selectUserAdapter = new SelectUserAdapter(context, selectUserList);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        selectRecyclerView.setLayoutManager(linearLayoutManager);
        selectUserAdapter.notifyDataSetChanged();
        selectRecyclerView.setAdapter(selectUserAdapter);



        // Back Button

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Bundle dataSend = new Bundle();
                dataSend.putString("code",code);
                dataSend.putString("name",name);
                Intent back = new Intent(ShowUsersForUpdateActivity.this, AdminHomeActivity.class);
                back.putExtras(dataSend);
                startActivity(back);
            }
        });



    }
}