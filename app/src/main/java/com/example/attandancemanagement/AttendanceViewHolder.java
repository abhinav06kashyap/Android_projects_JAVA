package com.example.attandancemanagement;

import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

public class AttendanceViewHolder extends RecyclerView.ViewHolder {
    TextView code,name,date, inTime, outTime, hours;
    public AttendanceViewHolder(View itemView) {
        super(itemView);

        // get the reference of item view's
        code = (TextView) itemView.findViewById(R.id.code);
        name = (TextView) itemView.findViewById(R.id.name);
        date = (TextView) itemView.findViewById(R.id.dateDisplay);
        inTime = (TextView) itemView.findViewById(R.id.inTimeDisplay);
        outTime = (TextView) itemView.findViewById(R.id.outTimeDisplay);
        hours = (TextView) itemView.findViewById(R.id.hoursDisplay);

    }
}
