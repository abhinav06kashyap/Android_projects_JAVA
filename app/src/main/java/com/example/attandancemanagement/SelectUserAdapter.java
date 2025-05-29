package com.example.attandancemanagement;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class SelectUserAdapter extends RecyclerView.Adapter<DeleteEmployeeViewHolder>{

    List<UserData> selectUserList;
    Activity context;



    public SelectUserAdapter(Activity context, List<UserData> selectUserList) {
        this.context = context;
        this.selectUserList = selectUserList;

    }


    @NonNull
    @Override
    public DeleteEmployeeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View selectView = LayoutInflater.from(parent.getContext()).inflate(R.layout.select_user_layout, parent, false);


        DeleteEmployeeViewHolder deleteEmployeeViewHolder = new DeleteEmployeeViewHolder(selectView); // pass the view to View Holder


        return deleteEmployeeViewHolder;
    }


    @Override
    public void onBindViewHolder(@NonNull DeleteEmployeeViewHolder holder, int position) {


        holder.userCode.setText(String.valueOf(selectUserList.get(position).getCode()));
        holder.userName.setText(selectUserList.get(position).getUserName());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                Intent next = new Intent(view.getContext(),UpdateUserActivity.class);
                next.putExtra("userdata",selectUserList.get(position));
                view.getContext().startActivity(next);

            }
        });

    }

    @Override
    public int getItemCount() {
        return selectUserList.size();
    }
}
