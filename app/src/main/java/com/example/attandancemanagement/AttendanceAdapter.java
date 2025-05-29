package com.example.attandancemanagement;

import android.app.Activity;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class AttendanceAdapter extends RecyclerView.Adapter<AttendanceViewHolder>{

    List<AttendanceData> attendanceDataList;
    LinearLayout context;

    DateFormat df = new SimpleDateFormat("HH:mm:ss");



    public AttendanceAdapter(LinearLayout context, List<AttendanceData> attendanceDataList) {
        this.context = context;
        this.attendanceDataList = attendanceDataList;
    }


    @NonNull
    @Override
    public AttendanceViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View statesView = LayoutInflater.from(parent.getContext()).inflate(R.layout.attendance_display_layout, parent, false);

        AttendanceViewHolder attendanceViewHolder = new AttendanceViewHolder(statesView); // pass the view to View Holder

        return attendanceViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull AttendanceViewHolder holder, int position)
    {
        holder.code.setText(String.valueOf(attendanceDataList.get(position).getAttCode()));
        holder.name.setText(attendanceDataList.get(position).getAttUserName());
        holder.date.setText(attendanceDataList.get(position).getAttDate());
        holder.inTime.setText(attendanceDataList.get(position).getAttInTime());
        holder.outTime.setText(attendanceDataList.get(position).getAttOutTime());

        Date inTime = null;
        Date outTime = null;
        try {
            inTime = df.parse(attendanceDataList.get(position).getAttInTime());
            outTime = df.parse(attendanceDataList.get(position).getAttOutTime());

            int dateDiff = (int) (outTime.getTime() - inTime.getTime());
            float hours = TimeUnit.MILLISECONDS.toMinutes(dateDiff);
            DecimalFormat d1 = new DecimalFormat("#.#");
            holder.hours.setText(""+d1.format(hours/60));
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }


    }

    @Override
    public int getItemCount() {
        return attendanceDataList.size();
    }

    public void onRefreshAdapter(List<AttendanceData> attendanceDataList) {
        attendanceDataList = attendanceDataList;
        notifyDataSetChanged();

    }


}
