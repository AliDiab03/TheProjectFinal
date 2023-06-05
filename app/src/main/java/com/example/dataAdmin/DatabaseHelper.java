package com.example.dataAdmin;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

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
        db.execSQL("DROP TABLE IF EXISTS " + Student.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + Subject.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + Student_Subject.TABLE_NAME);
        db.execSQL(" DROP TABLE IF EXISTS " + Presence.TABLE_NAME);
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

    public boolean insertAdmin(String userName, String email, String password) {
        SQLiteDatabase database = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Admin.COL_USERNAME, userName);
        values.put(Admin.COL_EMAIL, email);
        values.put(Admin.COL_PASSWORD, password);
        long row = database.insert(Admin.TABLE_NAME, null, values);
        return row > 0;
    }

    public int insertStudent(String firsName, String lastName, String birthDate) {
        SQLiteDatabase database = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Student.COL_FIRST_NAME, firsName);
        values.put(Student.COL_LAST_NAME, lastName);
        values.put(Student.COL_BIRTH_DATE, birthDate);
        long row = database.insert(Student.TABLE_NAME, null, values);
        return (int) row;

    }

    public boolean insertSubject(String name) {
        SQLiteDatabase database = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Subject.COL_NAME, name);
        long row = database.insert(Subject.TABLE_NAME, null, values);
        return row > 0;
    }

    public boolean insertStudent_Subject(int student_id, int subject_id) {
        SQLiteDatabase database = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Student_Subject.COL_STUDENT_ID, student_id);
        values.put(Student_Subject.COL_SUBJECT_ID, subject_id);
        long row = database.insert(Student_Subject.TABLE_NAME, null, values);
        return row > 0;

    }

    public boolean insertPresence(String month, String day, int studentId, int subjectId) {
        SQLiteDatabase database = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Presence.COL_MONTH, month);
        values.put(Presence.COL_DAY, day);
        values.put(Presence.COL_STUDENT_ID, studentId);
        values.put(Presence.COL_SUBJECT_ID, subjectId);
        long row = database.insert(Presence.TABLE_NAME, null, values);
        return row > 0;
    }


    public boolean getAuthenticateUser(String userNameOrEmail, String password) {
        SQLiteDatabase database = getReadableDatabase();
        String[] columns = {Admin.COL_USERNAME};
        String selection = "(" + Admin.COL_USERNAME + " =?  OR " + Admin.COL_EMAIL + " = ?) AND " + Admin.COL_PASSWORD + " = ?";
        String[] selectionArgs = {userNameOrEmail, userNameOrEmail, password};
        Cursor cursor = database.query(Admin.TABLE_NAME, columns, selection, selectionArgs, null, null, null);
        boolean isUserExists = cursor.moveToFirst();
        cursor.close();
        return isUserExists;
    }


    @SuppressLint("Range")
    public Admin getAdmin() {
        SQLiteDatabase database = getReadableDatabase();
        String[] columns = {Admin.COL_ID, Admin.COL_USERNAME, Admin.COL_EMAIL, Admin.COL_PASSWORD};
        Cursor cursor = database.query(Admin.TABLE_NAME, columns, null, null, null, null, null);
        Admin admin = null;
        if (cursor.moveToFirst()) {
            int id = cursor.getInt(cursor.getColumnIndex(Admin.COL_ID));
            String userName = cursor.getString(cursor.getColumnIndex(Admin.COL_USERNAME));
            String email = cursor.getString(cursor.getColumnIndex(Admin.COL_EMAIL));
            String password = cursor.getString(cursor.getColumnIndex(Admin.COL_PASSWORD));
            admin = new Admin(id, userName, email, password);
        }
        cursor.close();
        return admin;
    }

    public boolean updateAdmin(Admin admin) {
        SQLiteDatabase database = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Admin.COL_USERNAME, admin.getUserName());
        values.put(Admin.COL_EMAIL, admin.getEmail());
        values.put(Admin.COL_PASSWORD, admin.getPassword());
        String selection = Admin.COL_ID + " = ?";
        String[] selectionArgs = {String.valueOf(admin.getId())};
        int rowsAffected = database.update(Admin.TABLE_NAME, values, selection, selectionArgs);
        return rowsAffected > 0;
    }

    @SuppressLint("Range")
    public ArrayList<Student> getStudent() {
        ArrayList<Student> students = new ArrayList<>();
        SQLiteDatabase database = getReadableDatabase();
        String[] columns = {Student.COL_ID, Student.COL_FIRST_NAME, Student.COL_LAST_NAME, Student.COL_BIRTH_DATE};
        Cursor cursor = database.query(Student.CREATE_TABLE, columns, null, null, null, null, null);
        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(cursor.getColumnIndex(Student.COL_ID));
                String firstName = cursor.getString(cursor.getColumnIndex(Student.COL_FIRST_NAME));
                String lastName = cursor.getString(cursor.getColumnIndex(Student.COL_LAST_NAME));
                String dateOfBirth = cursor.getString(cursor.getColumnIndex(Student.COL_BIRTH_DATE));
                Student student = new Student(id, firstName, lastName, dateOfBirth);
                students.add(student);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return students;
    }

    @SuppressLint("Range")
    public ArrayList<Subject> getSubjects() {
        ArrayList<Subject> subjects = new ArrayList<>();
        SQLiteDatabase database = getReadableDatabase();
        String[] columns = {Subject.COL_ID, Subject.COL_NAME};
        Cursor cursor = database.query(Subject.TABLE_NAME, columns, null, null, null, null, null);
        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(cursor.getColumnIndex(Subject.COL_ID));
                String name = cursor.getString(cursor.getColumnIndex(Subject.COL_NAME));
                Subject subject = new Subject(id, name);
                subjects.add(subject);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return subjects;
    }

    @SuppressLint("Range")
    public ArrayList<Student> getStudents() {
        ArrayList<Student> students = new ArrayList<>();
        SQLiteDatabase database = getReadableDatabase();
        String[] columns = {Student.COL_ID, Student.COL_FIRST_NAME, Student.COL_LAST_NAME, Student.COL_BIRTH_DATE};
        Cursor cursor = database.query(Student.TABLE_NAME, columns, null, null, null, null, null);
        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(cursor.getColumnIndex(Student.COL_ID));
                String firstName = cursor.getString(cursor.getColumnIndex(Student.COL_FIRST_NAME));
                String lastName = cursor.getString(cursor.getColumnIndex(Student.COL_LAST_NAME));
                String birthDate = cursor.getString(cursor.getColumnIndex(Student.COL_BIRTH_DATE));
                Student student = new Student(id, firstName, lastName, birthDate);
                students.add(student);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return students;
    }


    @SuppressLint("Range")
    public ArrayList<Student> getStudentsBySubject(int subjectId) {
        ArrayList<Student> students = new ArrayList<>();
        String selectQuery = "SELECT * FROM " + Student.TABLE_NAME + " WHERE " + Student.COL_ID + " IN (SELECT " + Student_Subject.COL_STUDENT_ID + " FROM " + Student_Subject.TABLE_NAME + " WHERE " + Student_Subject.COL_SUBJECT_ID + " = " + subjectId + ")";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(cursor.getColumnIndex(Student.COL_ID));
                String fName = cursor.getString(cursor.getColumnIndex(Student.COL_FIRST_NAME));
                String lName = cursor.getString(cursor.getColumnIndex(Student.COL_LAST_NAME));

                String date = cursor.getString(cursor.getColumnIndex(Student.COL_BIRTH_DATE));
                Student student = new Student(id, fName, lName, date);
                students.add(student);
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return students;
    }


    @SuppressLint("Range")
    public ArrayList<Subject> getSubjectsByStudent(int studentId) {
        ArrayList<Subject> subjects = new ArrayList<>();
        String selectQuery = "SELECT * FROM " + Subject.TABLE_NAME + " WHERE " + Subject.COL_ID + " IN (SELECT " + Student_Subject.COL_SUBJECT_ID + " FROM " + Student_Subject.TABLE_NAME + " WHERE " + Student_Subject.COL_STUDENT_ID + " = " + studentId + ")";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(cursor.getColumnIndex(Subject.COL_ID));
                String name = cursor.getString(cursor.getColumnIndex(Subject.COL_NAME));
                Subject subject = new Subject(id, name);
                subjects.add(subject);
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return subjects;
    }

    public int getAttendedDays(int subjectId, String monthName) {
        SQLiteDatabase database = getReadableDatabase();
        String[] columns = {Presence.COL_DAY};
        String selection = Presence.COL_SUBJECT_ID + " = ? AND " + Presence.COL_MONTH + " = ?";
        String[] selectionArgs = {String.valueOf(subjectId), monthName};
        Cursor cursor = database.query(Presence.TABLE_NAME, columns, selection, selectionArgs, null, null, null);
        int attendedDays = cursor.getCount();
        cursor.close();
        return attendedDays;
    }

    public int getSubjectTotalSessions(int subjectId) {
        SQLiteDatabase database = getReadableDatabase();
        String[] columns = {Presence.COL_DAY};
        String selection = Presence.COL_SUBJECT_ID + " = ?";
        String[] selectionArgs = {String.valueOf(subjectId)};
        Cursor cursor = database.query(Presence.TABLE_NAME, columns, selection, selectionArgs, null, null, null);
        int totalSessions = cursor.getCount();
        cursor.close();
        return totalSessions;
    }

    public int getStudentAttendedSessions(int studentId, int subjectId) {
        SQLiteDatabase database = getReadableDatabase();
        String[] columns = {Presence.COL_DAY};
        String selection = Presence.COL_STUDENT_ID + " = ? AND " + Presence.COL_SUBJECT_ID + " = ?";
        String[] selectionArgs = {String.valueOf(studentId), String.valueOf(subjectId)};
        Cursor cursor = database.query(Presence.TABLE_NAME, columns, selection, selectionArgs, null, null, null);
        int attendedSessions = cursor.getCount();
        cursor.close();
        return attendedSessions;
    }



    public float calculateAttendancePercentage(int studentId, int subjectId) {
        int totalSessions = getSubjectTotalSessions( subjectId);
        int attendedSessions = getStudentAttendedSessions(studentId, subjectId);

        if (totalSessions == 0) {
            return 0; // تجنب القسمة على الصفر
        }

        float attendancePercentage = (float) attendedSessions / totalSessions * 100;
        return attendancePercentage;
    }







    public boolean deleteSubject(String id) {
        SQLiteDatabase db = getWritableDatabase();
        int rowId = db.delete(Subject.TABLE_NAME, Subject.COL_ID + "=?", new String[]{id});
        return rowId > 0;
    }

    public boolean deleteStudent(String id) {
        SQLiteDatabase db = getWritableDatabase();
        int rowId = db.delete(Student.TABLE_NAME, Student.COL_ID + "=?", new String[]{id});
        return rowId > 0;
    }


}

