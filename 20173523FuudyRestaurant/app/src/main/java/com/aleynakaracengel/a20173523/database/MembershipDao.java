package com.aleynakaracengel.a20173523.database;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class MembershipDao {

    public boolean addUser(DatabaseMembershipCreator databaseMembershipCreator3, String namelastname, String phonenumber, String password) {

        SQLiteDatabase dbx = databaseMembershipCreator3.getWritableDatabase();

        @SuppressLint("Recycle") Cursor c = dbx.rawQuery("SELECT * FROM membership WHERE phonenumber =?", new String[]{phonenumber});

        if (!c.moveToFirst()) {
            ContentValues contentValues = new ContentValues();
            contentValues.put("namelastname", namelastname);
            contentValues.put("phonenumber", phonenumber);
            contentValues.put("password", password);
            dbx.insertOrThrow("membership", null, contentValues);
            dbx.close();
        }

        return c.moveToFirst();
    }

    @SuppressLint("Recycle")
    public boolean checkuser(DatabaseMembershipCreator databaseMembershipCreator3, String phonenumber, String password) {
        SQLiteDatabase dbx = databaseMembershipCreator3.getWritableDatabase();
        Cursor c = dbx.rawQuery("SELECT * FROM membership WHERE phonenumber=? AND password=?", new String[]{phonenumber, password});

        return c.moveToFirst();
    }

}
