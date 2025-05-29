package com.example.attandancemanagement;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class DeleteEmployeeAdapter extends RecyclerView.Adapter<DeleteEmployeeViewHolder>{

    ArrayList<String> checkList;
    ArrayList<String> checkListCount;
    List<UserData> deleteUserList;
    LinearLayout context;


    public DeleteEmployeeAdapter(LinearLayout context, List<UserData> deleteUserList) {
        this.context = context;
        this.deleteUserList = deleteUserList;
    }


    @NonNull
    @Override
    public DeleteEmployeeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View deleteView = LayoutInflater.from(parent.getContext()).inflate(R.layout.delete_emp_layout, parent, false);


        DeleteEmployeeViewHolder deleteEmployeeViewHolder = new DeleteEmployeeViewHolder(deleteView); // pass the view to View Holder


        return deleteEmployeeViewHolder;
    }


    @Override
    public void onBindViewHolder(@NonNull DeleteEmployeeViewHolder holder, int position) {

        holder.code.setText(String.valueOf(deleteUserList.get(position).getCode()));
        holder.name.setText(deleteUserList.get(position).getUserName());

        if(deleteUserList.get(position).is_selected == false)
        {
            holder.deleteUserCheck.setChecked(false);
        }
        else {
            holder.deleteUserCheck.setChecked(true);
        }

        holder.deleteUserCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(holder.deleteUserCheck.isChecked()) deleteUserList.get(position).is_selected = true;
                else  deleteUserList.get(position).is_selected = false;
            }
        });

        /*if(checkList != null && checkList.size()>0) {
            holder.deleteUserCheck.setText(checkList.get(position));

            if(holder.deleteUserCheck.isChecked())
            {
                checkListCount.add(checkList.get(position));
            }
            else
            {
                checkListCount.remove(checkList.get(position));
            }

        }*/
    }

    @Override
    public int getItemCount() {
        return deleteUserList.size();
    }
}
