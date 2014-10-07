package com.contactos.cristiancaucott.contacto2;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import java.util.ArrayList;
import java.util.HashMap;

public class ContactRepo {
    private DBHelper dbHelper;

    public ContactRepo(Context context) {
        dbHelper = new DBHelper(context);
    }

    public int insert(Contacto c) {

        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Contacto.KEY_age, c.age);
        values.put(Contacto.KEY_email,c.email);
        values.put(Contacto.KEY_name, c.name);

        long id = db.insert(Contacto.TABLE, null, values);
        db.close();
        return (int) id;
    }

    public void delete(int student_Id) {

        SQLiteDatabase db = dbHelper.getWritableDatabase();
        db.delete(Contacto.TABLE, Contacto.KEY_ID + "= ?", new String[] { String.valueOf(student_Id) });
        db.close();
    }

    public void update(Contacto c) {

        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(Contacto.KEY_age, c.age);
        values.put(Contacto.KEY_email,c.email);
        values.put(Contacto.KEY_name, c.name);

        db.update(Contacto.TABLE, values, Contacto.KEY_ID + "= ?", new String[] { String.valueOf(c.contacto_id) });
        db.close();
    }

    public ArrayList<HashMap<String, String>>  getContactList() {

        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String selectQuery =  "SELECT  " +
                Contacto.KEY_ID + "," +
                Contacto.KEY_name + "," +
                Contacto.KEY_email + "," +
                Contacto.KEY_age +
                " FROM " + Contacto.TABLE;

        //Contacto c = new Contacto();
        ArrayList<HashMap<String, String>> studentList = new ArrayList<HashMap<String, String>>();

        Cursor cursor = db.rawQuery(selectQuery, null);
        // looping through all rows and adding to list

        if (cursor.moveToFirst()) {
            do {
                HashMap<String, String> c = new HashMap<String, String>();
                c.put("id", cursor.getString(cursor.getColumnIndex(Contacto.KEY_ID)));
                c.put("name", cursor.getString(cursor.getColumnIndex(Contacto.KEY_name)));
                studentList.add(c);

            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return studentList;

    }

    public Contacto getContactById(int Id){
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String selectQuery =  "SELECT  " +
                Contacto.KEY_ID + "," +
                Contacto.KEY_name + "," +
                Contacto.KEY_email + "," +
                Contacto.KEY_age +
                " FROM " + Contacto.TABLE
                + " WHERE " +
                Contacto.KEY_ID + "=?";

        int iCount =0;
        Contacto c = new Contacto();

        Cursor cursor = db.rawQuery(selectQuery, new String[] { String.valueOf(Id) } );

        if (cursor.moveToFirst()) {
            do {
                c.contacto_id =cursor.getInt(cursor.getColumnIndex(Contacto.KEY_ID));
                c.name =cursor.getString(cursor.getColumnIndex(Contacto.KEY_name));
                c.email  =cursor.getString(cursor.getColumnIndex(Contacto.KEY_email));
                c.age =cursor.getInt(cursor.getColumnIndex(Contacto.KEY_age));

            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return c;
    }

}