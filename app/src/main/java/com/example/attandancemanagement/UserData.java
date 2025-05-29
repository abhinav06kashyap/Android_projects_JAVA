package com.example.attandancemanagement;

import java.io.Serializable;

public class UserData implements Serializable                                                    {
    String  name, department, pass, phone;
    int code, id;

    Boolean is_selected = false;

    public UserData(){   }
    public UserData(int id, int code, String name, String department, String pass, String phone)
    {
        this.id = id;
        this.code = code;
        this.name = name;
        this.department = department;
        this.pass = pass;
        this.phone = phone;



    }

    public UserData( int code, String name, String department, String pass, String phone){
        this.code = code;
        this.name = name;
        this.department = department;
        this.pass = pass;
        this.phone = phone;



    }

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }



    public int getCode()
    {
        return code;
    }

    public void setCode(int code)
    {
        this.code = code;
    }


    public String getUserName()
    {
        return name;
    }

    public void setUserName(String name)
    {
        this.name = name;
    }

    public String getDepartment()
    {
        return department;
    }

    public void setDepartment(String department)
    {
        this.department = department;
    }

    public String getPass()
    {
        return pass;
    }

    public void setPass(String pass)
    {
        this.pass = pass;
    }

    public String getPhone()
    {
        return phone;
    }

    public void setPhone(String phone)
    {
        this.phone = phone;
    }





}
