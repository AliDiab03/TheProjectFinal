package com.example.dataAdmin;

public class Student_Subject {
    private int id;
    private int student_id;
    private int subject_id;


    public static final String TABLE_NAME = "student_subject";
    public static final String COL_ID = "id";
    public static final String COL_STUDENT_ID = "studentID";
    public static final String COL_SUBJECT_ID = "subject_id";

    public static final String CREATE_TABLE = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + "(" +
            COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            COL_STUDENT_ID + " INTEGER, " +
            COL_SUBJECT_ID + " INTEGER);";


    public Student_Subject(int id, int student_id, int subject_id) {
        this.id = id;
        this.student_id = student_id;
        this.subject_id = subject_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getStudent_id() {
        return student_id;
    }

    public void setStudent_id(int student_id) {
        this.student_id = student_id;
    }

    public int getSubject_id() {
        return subject_id;
    }

    public void setSubject_id(int subject_id) {
        this.subject_id = subject_id;
    }
}
