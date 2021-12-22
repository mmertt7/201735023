package com.aleynakaracengel.a20173523.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseMembershipCreator extends SQLiteOpenHelper {
    public DatabaseMembershipCreator(@Nullable Context context) {
        super(context, "membership.sqlite", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS membership (id INTEGER PRIMARY KEY AUTOINCREMENT, namelastname TEXT, phonenumber TEXT, password TEXT);");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS membership");
        onCreate(db);
    }
}
