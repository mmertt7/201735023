package com.aleynakaracengel.a20173523.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseFoodTableCreator extends SQLiteOpenHelper {
    public DatabaseFoodTableCreator(@Nullable Context context) {
        super(context, "orderfood.sqlite", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS orderfood (id INTEGER PRIMARY KEY AUTOINCREMENT, imageurl TEXT, name TEXT, price TEXT);");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS orderfood");
        onCreate(db);
    }
}
