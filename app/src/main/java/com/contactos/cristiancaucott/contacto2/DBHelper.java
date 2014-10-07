package com.contactos.cristiancaucott.contacto2;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper  extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 4;
    private static final String DATABASE_NAME = "contacto.db";

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TABLE_STUDENT = "CREATE TABLE " + Contacto.TABLE  + "("
                + Contacto.KEY_ID  + " INTEGER PRIMARY KEY AUTOINCREMENT ,"
                + Contacto.KEY_name + " TEXT, "
                + Contacto.KEY_age + " INTEGER, "
                + Contacto.KEY_email + " TEXT )";

        db.execSQL(CREATE_TABLE_STUDENT);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed, all data will be gone!!!
        db.execSQL("DROP TABLE IF EXISTS " + Contacto.TABLE);

        // Create tables again
        onCreate(db);

    }

}
