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
        // هذه الدالة تستخدم في تحديث قاعدة البيانات
        // IF EXISTS هنا في الدالة يتم  حذف الجداول القديم ة عند التحديث اذا كان يوجد امر
        // وبعدها سيقوم بانشاء الجداول
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

        // هذه الدالة تقوم بانشاء الجداول عند تشغيل التطبيق لاول مرة
        //  db.execSQL تستخدم لانشاء الجداول

    }

    // ملاحظة : get Writable databse  تتيح لك الحصول على قاعدة البيانات مثلا تستطيع الاضافة في قاعدة البيانات والتعديل على قاعدة البيانات و حذف قاعدة البيانات
    // get ReadTableDatabse تستخدم  هذه الدالة للحصول على قاعدة البيانات في حالة القراءة فقط مثلا تستخدم عندما تريد استعراض او استعلام قاعدة بيانات

    public boolean insertAdmin(String userName, String email, String password) { // هذه الدالة تقوم بادخال بيانات الى جدول الادمن
        SQLiteDatabase database = getWritableDatabase();  // يتم الحصول على قاعدة البيانات با استخدام الكتابة
        ContentValues values = new ContentValues(); // يتم  تعريف كائن لتعبئة القيم التي سيتم ادخالها بالجدول
        values.put(Admin.COL_USERNAME, userName);// بااستخدام put استطيع ان اقوم بتخزين القيم بالجدول
        values.put(Admin.COL_EMAIL, email);
        values.put(Admin.COL_PASSWORD, password);
        long row = database.insert(Admin.TABLE_NAME, null, values); // هنا row يتم التحقق اذا كان رقم الصنف اكبر من صفر  يعني انه ادخال الrow قد نجد وسرجع لي تروو واذا كان خطا يرجع لي فولس
        return row > 0;
    }

    public int insertStudent(String firsName, String lastName, String birthDate) { // هذه الدالة تقوم بادخال البيانات الى الطالب
        SQLiteDatabase database = getWritableDatabase(); // يتم الحصول على قاعدة البيانات عن طريق الكتابة
        ContentValues values = new ContentValues();// يتم  تعريف كائن لتعبئة القيم التي سيتم ادخالها بالجدول
        values.put(Student.COL_FIRST_NAME, firsName);
        values.put(Student.COL_LAST_NAME, lastName);
        values.put(Student.COL_BIRTH_DATE, birthDate);
        long row = database.insert(Student.TABLE_NAME, null, values);
        return (int) row; // الى int  row  يتم تحويل القيمة القيمة المرجعة

    }

    public boolean insertSubject(String name) { // تقوم الدالة باضافة مادة جديدة الى قاعدة البيانات
        SQLiteDatabase database = getWritableDatabase(); // يتم الحصول على  قاعدة البيانات عن طريق الكتابة
        ContentValues values = new ContentValues(); // تستخدم لتخزين القيم
        values.put(Subject.COL_NAME, name);
        long row = database.insert(Subject.TABLE_NAME, null, values);
        return row > 0; // هنا row يتم التحقق اذا كان رقم الصنف اكبر من صفر  يعني انه ادخال الrow قد نجد وسرجع لي تروو واذا كان خطا يرجع لي فولس
    }

    public boolean insertStudent_Subject(int student_id, int subject_id) { // تستخدم هذه الدلة لاضافة رقم مادة ورقم طالب
        SQLiteDatabase database = getWritableDatabase(); // يتم الحصول على قاعدة البيانات عن طريق الكتابة
        ContentValues values = new ContentValues();
        values.put(Student_Subject.COL_STUDENT_ID, student_id);
        values.put(Student_Subject.COL_SUBJECT_ID, subject_id);
        long row = database.insert(Student_Subject.TABLE_NAME, null, values);
        return row > 0;

    }

    public boolean insertPresence(String month, String day, int studentId, int subjectId) {// تستخدم هذه الدالة لاضافة الحضور للطالب
        SQLiteDatabase database = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Presence.COL_MONTH, month);
        values.put(Presence.COL_DAY, day);
        values.put(Presence.COL_STUDENT_ID, studentId);
        values.put(Presence.COL_SUBJECT_ID, subjectId);
        long row = database.insert(Presence.TABLE_NAME, null, values);
        return row > 0;
    }


    // "OR": يستخدم لإجراء عملية مقارنة منطقية "أو" بين الشروط.
    //
    //"AND": يستخدم لإجراء عملية مقارنة منطقية "و" بين الشروط.
    //
    //"?": يستخدم كعلامة استبدال لقيم المتغيرات التي ستمرر في وقت التنفيذ.


    public boolean getAuthenticateUser(String userNameOrEmail, String password) { // تستخدم هذه الدالة لاستعلام والتحقق من صحة تسجيل الدخول لليوزر وكلمة السر
        SQLiteDatabase database = getReadableDatabase(); // تقوم هذه الدالة بقراءة البيانات
        String[] columns = {Admin.COL_USERNAME}; //  هنا يتم تعريف مصقوفة  columns لتحديد العمود الذي سيتم استرجاع القيمة منه وهنا سيتم استرجاع قيمة عمود اليوزرنيم
        String selection = "(" + Admin.COL_USERNAME + " =?  OR " + Admin.COL_EMAIL + " = ?) AND " + Admin.COL_PASSWORD + " = ?"; // يستخدم لتحديد الصفوف التي تطابق قيمة اسم المستخدم أو عنوان البريد الإلكتروني الممررة، وفي نفس الوقت تطابق قيمة كلمة المرور الممررة
        String[] selectionArgs = {userNameOrEmail, userNameOrEmail, password}; // تحتوي على القيم يوزر ....... يتم استبدال علامات استبدال اليوزر والايميل مرتين ويتم استبدال الباسورد مرة واحدة
        Cursor cursor = database.query(Admin.TABLE_NAME, columns, selection, selectionArgs, null, null, null);
        // يتم هذا الكود بعمل استعلام للقاعدة ويتم تحديد اسم الجدول واسم الاعمدة التي يتم استرجاعها ويمكن استخدامه للوصول للمؤشر
        boolean isUserExists = cursor.moveToFirst(); // هذه الدالة تقوم بعمل المئشر الى اول صف في الاستعلام
        cursor.close(); /// هنا يتم اغلاق المؤشر بعد ان يتم الانتهاء منه
        return isUserExists; // هنا يتم ارجاع القيمة -- لاتخاذ اجراءات اذا كان المستخدم موجود ام لا
    }


    @SuppressLint("Range")
    public Admin getAdmin() { // استعلام لجدول الادمن
        SQLiteDatabase database = getReadableDatabase();
        String[] columns = {Admin.COL_ID, Admin.COL_USERNAME, Admin.COL_EMAIL, Admin.COL_PASSWORD}; // هنا الاعمدة التي ارغب بارجاعها
        Cursor cursor = database.query(Admin.TABLE_NAME, columns, null, null, null, null, null);  // تستخدم الدالة query() لتنفيذ الاستعلام في قاعدة البيانات، وتعيد كائن Cursor الذي يحتوي على الصفوف المطابقة.
        Admin admin = null;
        if (cursor.moveToFirst()) {
            // اذا تم العثور على صف مشابه فسيتم تنفيذ الشرط اللي بالداخل

            int id = cursor.getInt(cursor.getColumnIndex(Admin.COL_ID));    //يستخدم للحصول على مؤشر العمود الذي يطابق اسم البعمود و ايضا اللي بداخل البراميتر لاستخراج قيمة العمودة المجددة بالكيرسر
            String userName = cursor.getString(cursor.getColumnIndex(Admin.COL_USERNAME));
            String email = cursor.getString(cursor.getColumnIndex(Admin.COL_EMAIL));
            String password = cursor.getString(cursor.getColumnIndex(Admin.COL_PASSWORD));
            admin = new Admin(id, userName, email, password);
        }
        cursor.close(); // يتم اغلاق الكيرسر بعد الانتهاء منه لتحرير البيانات والحفاظ على الاداء
        return admin;
    }

    public boolean updateAdmin(Admin admin) { // هذه الدالة تقوم بتحديث بيانات الادمن
        SQLiteDatabase database = getWritableDatabase(); // يتم الحصول على قاعدة بيانات قابلة للكتابة
        ContentValues values = new ContentValues(); // يتم تعريف كائن لاضافة قيم الجديدة
        values.put(Admin.COL_USERNAME, admin.getUserName()); // لاسترداد قيمة البريد الإلكتروني الجديد الذي تم تمريره كمعامل لدالة updateAdmin(). وتُع  يد الدالة قيمة البريد الإلكتروني الجديد.
        values.put(Admin.COL_EMAIL, admin.getEmail());
        values.put(Admin.COL_PASSWORD, admin.getPassword());
        String selection = Admin.COL_ID + " = ?"; // هنا يوجد الشرط لتحديد الصف الذي سيتم تحديثه بناءً على قيمة المعرف
        String[] selectionArgs = {String.valueOf(admin.getId())}; // تستخدم هذه المصفوفة لتوفير قيمة المعرّف كبديل للرمز الاستبدال "؟" في الشرط لتحديد الصفوف التي ستتم تحديثها في قاعدة البيانات.
        int rowsAffected = database.update(Admin.TABLE_NAME, values, selection, selectionArgs); // هكذا تتم عملية التحديث على البيانات
        //    Admin.TABLE_NAME: يُمثل اسم جدول الإداريين الذي سيتم تحديث الصفوف فيه.
        //    values: هو كائن ContentValues الذي يحتوي على القيم الجديدة التي سيتم تحديثها في الصفوف المطابقة.
        //    selection: هو الشرط الذي يحدد الصفوف التي ستتم تحديثها. يُمثل selection الجزء "WHERE" من الاستعلام.
        //    selectionArgs: هي مصفوفة القيم التي سيتم تعويض الرمز الاستبدال "؟" بها في الشرط. تُستخدم لتحديد قيمة المعرّف المحدد في الشرط.
        return rowsAffected > 0;
    }


    @SuppressLint("Range")
    public ArrayList<Subject> getSubjects() { // استعلام المواد
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
    public ArrayList<Student> getStudents() {// استعلام الطلاب
        ArrayList<Student> students = new ArrayList<>();// تم تعريف اري لست لتخزين بها
        SQLiteDatabase database = getReadableDatabase();// نحتاج الى قاعدة بيانات للقراءة
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
    public ArrayList<Student> getStudentsBySubject(int subjectId) { // ينم استعلام الطلاب المرتبطين بالمادة
        ArrayList<Student> students = new ArrayList<>();
        String selectQuery = "SELECT * FROM " + Student.TABLE_NAME + " WHERE " + Student.COL_ID + " IN (SELECT " + Student_Subject.COL_STUDENT_ID + " FROM " + Student_Subject.TABLE_NAME + " WHERE " + Student_Subject.COL_SUBJECT_ID + " = " + subjectId + ")";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null); // استخدام الاستعلام السابق لتنفيذه على قاعدة البيانات واسترجاع النتائج في كائن

        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(cursor.getColumnIndex(Student.COL_ID)); //  استرداد القيمة المحددة من الكيرسر
                String fName = cursor.getString(cursor.getColumnIndex(Student.COL_FIRST_NAME));
                String lName = cursor.getString(cursor.getColumnIndex(Student.COL_LAST_NAME));

                String date = cursor.getString(cursor.getColumnIndex(Student.COL_BIRTH_DATE));
                Student student = new Student(id, fName, lName, date);
                students.add(student);
            } while (cursor.moveToNext());
        }

        cursor.close(); // إغلاق كائن Cursor وإغلاق قاعدة البيانات.
        db.close();
        return students; // إرجاع قائمة الطلاب المسترجعة.
    }


    @SuppressLint("Range")
    public ArrayList<Subject> getSubjectsByStudent(int studentId) { // استعلام المواد المرتبطة بالطالب
        ArrayList<Subject> subjects = new ArrayList<>();
        String selectQuery = "SELECT * FROM " + Subject.TABLE_NAME + " WHERE " + Subject.COL_ID + " IN (SELECT " + Student_Subject.COL_SUBJECT_ID + " FROM " + Student_Subject.TABLE_NAME + " WHERE " + Student_Subject.COL_STUDENT_ID + " = " + studentId + ")";
        // استعلام يقوم بارجاع قيمة المعرف الايدي بحيث يجب ان يكون في جدول المادة المرتبط بها الطلاب
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

    public int getAttendedDays(int subjectId, String monthName) { //اختصار، الوظيفة تقوم بحساب عدد الأيام التي حضرها الطالب في موضوع معين خلال شهر معين
        SQLiteDatabase database = getReadableDatabase();
        String[] columns = {Presence.COL_DAY}; // يحدد الأعمدة التي ستسترجع من جدول الحضور وتخزن في مصفوفة.
        String selection = Presence.COL_SUBJECT_ID + " = ? AND " + Presence.COL_MONTH + " = ?";  // يحدد الشروط التي يجب توافرها لاسترجاع الصفوف المحضرة
        String[] selectionArgs = {String.valueOf(subjectId), monthName}; // يتم تعيين قيم الشروط في مصفوفة.
        Cursor cursor = database.query(Presence.TABLE_NAME, columns, selection, selectionArgs, null, null, null);
        int attendedDays = cursor.getCount(); // يستخرج عدد الصفوف في النتيجة ويخزنه.
        cursor.close();
        return attendedDays; // بتم ارجاع عدد الايام
    }

    public int getSubjectTotalSessions(int subjectId) { // تقوم بحساب عدد المحاضرات لمادة معينة
        SQLiteDatabase database = getReadableDatabase(); // تستعد قاعدة البيانات للقراءة.
        String[] columns = {Presence.COL_DAY}; // تحدد الأعمدة التي ستسترجعها من جدول الحضور.
        String selection = Presence.COL_SUBJECT_ID + " = ?"; // تحدد الشروط للاسترجاع المرتبطة بمعرف الموضوع.
        String[] selectionArgs = {String.valueOf(subjectId)};
        Cursor cursor = database.query(Presence.TABLE_NAME, columns, selection, selectionArgs, null, null, null);
        int totalSessions = cursor.getCount();
        cursor.close();
        return totalSessions;
    }

    public int getStudentAttendedSessions(int studentId, int subjectId) { // تقوم بحساب عدد عدد حضور الطالب لمادة معينة
        SQLiteDatabase database = getReadableDatabase();
        String[] columns = {Presence.COL_DAY};
        String selection = Presence.COL_STUDENT_ID + " = ? AND " + Presence.COL_SUBJECT_ID + " = ?";
        String[] selectionArgs = {String.valueOf(studentId), String.valueOf(subjectId)};
        Cursor cursor = database.query(Presence.TABLE_NAME, columns, selection, selectionArgs, null, null, null);
        int attendedSessions = cursor.getCount();
        cursor.close();
        return attendedSessions;
    }


    public float calculateAttendancePercentage(int studentId, int subjectId) { // هذه الدالة تقوم بحساب نسبة الحضور للطالب
        int totalSessions = getSubjectTotalSessions(subjectId); // تحصل على إجمالي عدد المحاضرات للمادة باستخدام
        int attendedSessions = getStudentAttendedSessions(studentId, subjectId);  // تحصل على عدد جلسات الحضور للطالب باستخدام

        if (totalSessions == 0) {   // إذا كان عدد الجلسات الكلي هو صفر، فتعيد قيمة صفر لتجنب القسمة على الصفر.
            return 0;
        }

        float attendancePercentage = (float) attendedSessions / totalSessions * 100;  // تحسب نسبة الحضور عن طريق قسمة عدد جلسات الحضور على الجلسات الكلية وتضرب النتيجة في 100.
        return attendancePercentage;
    }


    public boolean deleteSubject(String id) { // تقوم بحذف مادة معينة باستخدام المعرف الخاص به
        SQLiteDatabase db = getWritableDatabase();
        int rowId = db.delete(Subject.TABLE_NAME, Subject.COL_ID + "=?", new String[]{id});   // تستخدم دالة delete() لحذف السجل المرتبط بمعرف المادة من جدول المادة
        return rowId > 0;
    }

    public boolean deleteStudent(String id) {  // تقوم بحذف طالب معين بااستخدام المعرف الخاص به
        SQLiteDatabase db = getWritableDatabase();
        int rowId = db.delete(Student.TABLE_NAME, Student.COL_ID + "=?", new String[]{id}); // تستخدم دالة delete() لحذف السجل المرتبط بمعرف الطالب من جدول الطالب
        return rowId > 0;
    }


}

