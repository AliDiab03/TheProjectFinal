package com.example.dataAdmin;

import java.io.Serializable;

public class Student implements Serializable {
    private int id;
    private String firstName;
    private String lastName;
    private String birthdate;
    private boolean selected;


    public static final String TABLE_NAME = "student";
    public static final String COL_ID = "id";
    public static final String COL_FIRST_NAME = "firstName";
    public static final String COL_LAST_NAME = "lastName";
    public static final String COL_BIRTH_DATE = "birthDate";
    public static final String CREATE_TABLE = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME +
            "(" + COL_ID + " INTEGER  PRIMARY KEY AUTOINCREMENT, " + COL_FIRST_NAME + " TEXT, " +
            COL_LAST_NAME + " TEXT, " + COL_BIRTH_DATE + " INTEGER);";


    public Student(int id, String firstName, String lastName, String birthdate) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthdate = birthdate;
        this.selected = false;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(String birthdate) {
        this.birthdate = birthdate;
    }

    public String getFullName() {
        return firstName + " " + lastName;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }
}
