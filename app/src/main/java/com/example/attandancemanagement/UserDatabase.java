package com.example.attandancemanagement;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

public class UserDatabase extends SQLiteOpenHelper {

    public static final int DbVersion = 1;
    private static final String Db_NAME = "OfficeAttendanceAppDatabase.sqlite";


    // User table Data
    private static final String TABLE_USERS = "usersTable";
    private static final String ID = "_id";
    private static final String User_NAME = "name";
    private static final String User_Department = "depart";
    private static final String User_Code = "code";
    private static final String User_Pass = "password";
    private static final String User_Phone = "phone";


    //Attendance Table data

    private static final String TABLE_ATTENDANCE = "usersAttendanceTable";
    private static final String Att_ID = "_id";
    private static final String Att_User_NAME = "name";
    private static final String Att_User_Code = "code";
    private static final String Date = "date";
    private static final String In_Time = "in_time";
    private static final String Out_Time = "out_time";



    public UserDatabase(Context context) {
        super(context, Db_NAME, null, DbVersion);
    }


    //Table Created

    @Override
    public void onCreate(SQLiteDatabase db) {

        //Employee table
        String CREATE_USER_TABLE = "CREATE TABLE " + TABLE_USERS +
                "(" + ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + User_Code + " INT, " + User_NAME + " TEXT," + User_Department +
                " TEXT," + User_Pass + " TEXT, "+ User_Phone + " TEXT " + ")";
        db.execSQL(CREATE_USER_TABLE);


        //Attendance table

        String CREATE_ATTENDANCE_TABLE = "CREATE TABLE " + TABLE_ATTENDANCE +
                "(" + Att_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + Att_User_Code + " INT, " + Att_User_NAME + " TEXT," + Date +
                " TEXT," + In_Time + " TEXT," + Out_Time + " TEXT" +")";
        db.execSQL(CREATE_ATTENDANCE_TABLE);
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USERS + TABLE_ATTENDANCE);
        onCreate(db);
    }


    //Add new user in Employee table

    void addUsers(UserData userData) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(User_Code, userData.getCode());
        values.put(User_NAME, userData.getUserName());
        values.put(User_Department, userData.getDepartment());
        values.put(User_Pass, userData.getPass());
        values.put(User_Phone, userData.getPhone());


        db.insert(TABLE_USERS, null, values);
        db.close(); // closing database connection
    }


    //Mark Attendance in Attendance table

    void addAttendance(AttendanceData attendanceData) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(Att_User_Code, attendanceData.getAttCode());
        values.put(Att_User_NAME, attendanceData.getAttUserName());
        values.put(Date, attendanceData.getAttDate());
        values.put(In_Time, attendanceData.getAttInTime());
        values.put(Out_Time, attendanceData.getAttOutTime());

        db.insert(TABLE_ATTENDANCE, null, values);
        db.close(); // closing database connection
    }



    //Fetch current user data

    UserData getUserData(int id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_USERS, new String[]{ID, User_Code,
                        User_NAME, User_Department, User_Pass, User_Phone}, ID + "=?",
                new String[]{String.valueOf(id)}, null, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
        }


        UserData userData = new UserData(Integer.parseInt(cursor.getString(0)),
                Integer.parseInt(cursor.getString(1)), cursor.getString(2),
                cursor.getString(3), cursor.getString(4), cursor.getString(5));

        return userData;
    }



    // Get user data in list
    public List<UserData> getAllContacts() {
        List<UserData> UserDataList = new ArrayList<UserData>();

        String selectQuery = "SELECT  * FROM " + TABLE_USERS;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                UserData userData = new UserData();
                userData.setId(Integer.parseInt(cursor.getString(0)));
                userData.setCode(Integer.parseInt(cursor.getString(1)));
                userData.setUserName(cursor.getString(2));
                userData.setDepartment(cursor.getString(3));
                userData.setPass(cursor.getString(4));
                userData.setPhone(cursor.getString(5));


                // add data to list
                UserDataList.add(userData);
            }
                while (cursor.moveToNext());
        }

        return UserDataList;
    }



    // Get user Attendance in list
    public List<AttendanceData> getAllAttendance() {
        List<AttendanceData> AttendanceDataList = new ArrayList<AttendanceData>();

        String selectAttQuery = "SELECT  * FROM " + TABLE_ATTENDANCE;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectAttQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                AttendanceData attendanceData = new AttendanceData();
                attendanceData.setAttId(Integer.parseInt(cursor.getString(0)));
                attendanceData.setAttCode(Integer.parseInt(cursor.getString(1)));
                attendanceData.setAttUserName(cursor.getString(2));
                attendanceData.setAttDate(cursor.getString(3));
                attendanceData.setAttInTime(cursor.getString(4));
                attendanceData.setAttOutTime(cursor.getString(5));

                // add data to list
                AttendanceDataList.add(attendanceData);
            }
            while (cursor.moveToNext());
        }

        return AttendanceDataList;
    }



  /*  //Get single user for update

    public List<UserData> getSingleUserUpdate(String code) {
        List<UserData> UserUpdateList = new ArrayList<UserData>();

        String selectUserQuery = "SELECT  * FROM " + TABLE_USERS + " WHERE code = " + code ;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectUserQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                UserData userData = new UserData();
                userData.setUserName(cursor.getString(2));

                // add data to list
                UserUpdateList.add(userData);
            }
            while (cursor.moveToNext());
        }

        return UserUpdateList;
    }*/





    //Get users attendance

    public List<AttendanceData> getSingleEmpAttendance(String code) {
        List<AttendanceData> TodayAttendanceList = new ArrayList<AttendanceData>();

        String selectTodayQuery = "SELECT  * FROM " + TABLE_ATTENDANCE + " WHERE code = " + code ;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectTodayQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                AttendanceData attendanceData = new AttendanceData();
                attendanceData.setAttId(Integer.parseInt(cursor.getString(0)));
                attendanceData.setAttCode(Integer.parseInt(cursor.getString(1)));
                attendanceData.setAttUserName(cursor.getString(2));
                attendanceData.setAttDate(cursor.getString(3));
                attendanceData.setAttInTime(cursor.getString(4));
                attendanceData.setAttOutTime(cursor.getString(5));

                // add data to list
                TodayAttendanceList.add(attendanceData);
            }
            while (cursor.moveToNext());
        }

        return TodayAttendanceList;
    }



    //Find if User trying to login is in database
    public List<UserData> matchUser(String userCode, String userPass)
    {
        ArrayList<UserData> UserMatchList = new ArrayList<UserData>();

        String matchQuery = "SELECT * FROM " + TABLE_USERS + " WHERE code = "  + userCode + " AND password = '"  +userPass +"'";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(matchQuery, null);

        if(cursor.moveToFirst())
            do {

                UserData matchData = new UserData();
                matchData.setId(Integer.parseInt(cursor.getString(0)));
                matchData.setCode(Integer.parseInt(cursor.getString(1)));
                matchData.setUserName(cursor.getString(2));
                matchData.setDepartment(cursor.getString(3));
                matchData.setPass(cursor.getString(4));

                // add data to list
                UserMatchList.add(matchData);
            }
        while (cursor.moveToNext());
        return UserMatchList;

    }


    // Deleting single contact


    @NonNull
    public void deleteContact(int user_code) {

        String deleteQuery = "DELETE FROM " + TABLE_USERS + " WHERE code = "  + user_code ;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(deleteQuery, null);


        if(cursor.moveToFirst())
            do {

                UserData deleteData = new UserData();
                deleteData.setId(Integer.parseInt(cursor.getString(0)));
                deleteData.setCode(Integer.parseInt(cursor.getString(1)));
                deleteData.setUserName(cursor.getString(2));
                deleteData.setDepartment(cursor.getString(3));
                deleteData.setPass(cursor.getString(4));
            }
            while (cursor.moveToNext());

    }




    // Update Attendance row

    public List<AttendanceData> updateAttendance(String EmpCode, String outTime)
    {
        ArrayList<AttendanceData> updateAttendanceList = new ArrayList<AttendanceData>();

        String updateQuery = "UPDATE " + TABLE_ATTENDANCE +" SET out_time = '" + outTime + "'" +" WHERE code = "  + EmpCode ;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(updateQuery, null);


        if(cursor.moveToFirst()) {

            do {
                AttendanceData updateOutTime = new AttendanceData();
                updateOutTime.setAttOutTime(cursor.getString(5));

                // add data to list
                updateAttendanceList.add(updateOutTime);
            }
            while(cursor.moveToNext());
        }
        return updateAttendanceList;

    }



    // Update User Details

    public List<UserData> updateUser(String name, String code, String phone)
    {
        ArrayList<UserData> updateAttendanceList = new ArrayList<UserData>();

        String updateQuery = "UPDATE " + TABLE_USERS +" SET name = '" + name+ "', phone = '"+ phone +"' WHERE code = '"+code+"'";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(updateQuery, null);


        if(cursor.moveToFirst()) {

            do {
                UserData updateUserDetails = new UserData();
                updateUserDetails.setUserName(cursor.getString(2));
                updateUserDetails.setPhone(cursor.getString(5));

                // add data to list
                updateAttendanceList.add(updateUserDetails);
            }
            while(cursor.moveToNext());
            db.close();
        }
        return updateAttendanceList;

    }





    // Getting User Count
    public int getUserCount() {
        String countQuery = "SELECT  * FROM " + TABLE_USERS;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        cursor.close();

        // return count
        return cursor.getCount();
    }



    //Getting users for delete activity

    public List<UserData> getUsersToDeleteList(int code) {
        List<UserData> usersDeleteList = new ArrayList<UserData>();

        String selectUsersQuery = "SELECT * FROM " + TABLE_USERS + " WHERE code IS NOT " + code  ;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectUsersQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                UserData userData = new UserData();
                userData.setCode(Integer.parseInt(cursor.getString(1)));
                userData.setUserName(cursor.getString(2));

                // add data to list
                usersDeleteList.add(userData);
            }
            while (cursor.moveToNext());
        }

        return usersDeleteList;
    }


    //Getting users for Select activity

    public List<UserData> getUsersToSelectList() {
        List<UserData> usersSelectList = new ArrayList<UserData>();

        String updateUsersQuery = "SELECT * FROM " + TABLE_USERS + " WHERE depart IS NOT 'Admin'" ;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(updateUsersQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                UserData selectUserData = new UserData();
                selectUserData.setCode(Integer.parseInt(cursor.getString(1)));
                selectUserData.setUserName(cursor.getString(2));
                selectUserData.setPhone(cursor.getString(5));

                // add data to list
                usersSelectList.add(selectUserData);
            }
            while (cursor.moveToNext());
        }
        return usersSelectList;
    }




}
