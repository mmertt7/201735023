package com.aleynakaracengel.a20173523.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseBasketCreator extends SQLiteOpenHelper {
    public DatabaseBasketCreator(@Nullable Context context) {
        super(context, "basket.sqlite", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS basket (id INTEGER PRIMARY KEY AUTOINCREMENT, imageurl TEXT, name TEXT, price TEXT);");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS basket");
        onCreate(db);
    }
}
