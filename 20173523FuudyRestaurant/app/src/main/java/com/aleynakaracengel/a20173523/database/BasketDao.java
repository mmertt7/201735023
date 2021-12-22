package com.aleynakaracengel.a20173523.database;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.aleynakaracengel.a20173523.model.OrderFood;
import java.util.ArrayList;

public class BasketDao {

    public void addBasket(DatabaseBasketCreator databaseBasketCreator, String imageurl, String name, double price) {
        SQLiteDatabase dbx = databaseBasketCreator.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("imageurl", imageurl);
        contentValues.put("name", name);
        contentValues.put("price", price);
        dbx.insertOrThrow("basket", null, contentValues);
        dbx.close();
    }

    public static ArrayList<OrderFood> allBasketArrayList(DatabaseBasketCreator databaseBasketCreator) {
        ArrayList<OrderFood> basketArrayList = new ArrayList<>();
        SQLiteDatabase dbx = databaseBasketCreator.getWritableDatabase();
        @SuppressLint("Recycle") Cursor c = dbx.rawQuery("SELECT * FROM basket", null);

        while (c.moveToNext()) {
            @SuppressLint("Range") OrderFood orderFood = new OrderFood(c.getInt(c.getColumnIndex("id")), c.getString(c.getColumnIndex("imageurl")), c.getString(c.getColumnIndex("name")), c.getInt(c.getColumnIndex("price")));
            basketArrayList.add(orderFood);
        }

        return basketArrayList;
    }

    public void deleteFoodBasket(DatabaseBasketCreator databaseBasketCreator, int id) {
        SQLiteDatabase dbx = databaseBasketCreator.getWritableDatabase();
        dbx.delete("basket", "id=?", new String[]{String.valueOf(id)});
        dbx.close();
    }

}
