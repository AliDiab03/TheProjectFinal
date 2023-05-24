package com.example.dataAdmin;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "project_final_db";
    private static final int DATABASE_VERSION = 1;

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // إذا كان هناك ترقية لقاعدة البيانات، يمكنك التعامل معها هنا
        db.execSQL("DROP TABLE IF EXISTS " + Admin.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS "+Student.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS "+Subject.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS "+Student_Subject.TABLE_NAME);
        db.execSQL(" DROP TABLE IF EXISTS "+Presence.TABLE_NAME);
        onCreate(db);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(Admin.CREATE_TABLE);
        db.execSQL(Student.CREATE_TABLE);
        db.execSQL(Subject.CREATE_TABLE);
        db.execSQL(Student_Subject.CREATE_TABLE);
        db.execSQL(Presence.CREATE_TABLE);



    }
}
