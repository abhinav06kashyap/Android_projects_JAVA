package com.example.attandancemanagement;

import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

public class DeleteEmployeeViewHolder extends RecyclerView.ViewHolder {

    TextView code,name, userCode,userName;
    CheckBox deleteUserCheck;

    public DeleteEmployeeViewHolder(View itemView) {

        super(itemView);

        // get the reference of item view's
        code = (TextView) itemView.findViewById(R.id.Empcode);
        name = (TextView) itemView.findViewById(R.id.Empname);

        userCode = (TextView) itemView.findViewById(R.id.Usercode);
        userName = (TextView) itemView.findViewById(R.id.Username);
        deleteUserCheck = (CheckBox) itemView.findViewById(R.id.delete_user_check);


    }
}
