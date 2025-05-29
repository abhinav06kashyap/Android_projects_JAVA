package com.example.attandancemanagement;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class AttendanceData {

    String name, date, inTime, outTime;
    int code, id;

    public AttendanceData() {}

    public AttendanceData(int id, int code, String name, String date, String inTime, String outTime) {
        this.id = id;
        this.code = code;
        this.name = name;
        this.date = date;
        this.outTime = outTime;
        this.inTime = inTime;



    }

    public AttendanceData(int code, String name, String date, String inTime, String outTime) {
        this.code = code;
        this.name = name;
        this.date = date;
        this.outTime = outTime;
        this.inTime = inTime;


    }


    public int getAttId() {
        return id;
    }

    public void setAttId(int id) {
        this.id = id;
    }


    public int getAttCode() {
        return code;
    }

    public void setAttCode(int code) {
        this.code = code;
    }

    public String getAttUserName() {
        return name;
    }

    public void setAttUserName(String name) {
        this.name = name;
    }

    public String getAttDate() {
        return date;
    }

    public void setAttDate(String date) {
        this.date = date;
    }

    public String getAttInTime() {
        return inTime;
    }

    public void setAttInTime(String inTime) {
        this.inTime = inTime;
    }

    public String getAttOutTime() {
        return outTime;
    }

    public void setAttOutTime(String outTime) {
        this.outTime = outTime;
    }




}
