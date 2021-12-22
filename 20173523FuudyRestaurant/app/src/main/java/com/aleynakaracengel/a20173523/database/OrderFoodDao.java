package com.aleynakaracengel.a20173523.database;

import android.annotation.SuppressLint;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.aleynakaracengel.a20173523.model.OrderFood;
import java.util.ArrayList;

public class OrderFoodDao {

    public static ArrayList<OrderFood> allOrderFoods(DatabaseFoodTableCreator databaseFoodTableCreator) {
        ArrayList<OrderFood> orderFoodArrayList = new ArrayList<>();

        SQLiteDatabase sqLiteDatabase = databaseFoodTableCreator.getWritableDatabase();

        @SuppressLint("Recycle") Cursor c = sqLiteDatabase.rawQuery("SELECT * FROM orderfood", null);

        while (c.moveToNext()) {
            @SuppressLint("Range") OrderFood orderFood = new OrderFood(c.getInt(c.getColumnIndex("id")), c.getString(c.getColumnIndex("imageurl")), c.getString(c.getColumnIndex("name")), c.getInt(c.getColumnIndex("price")));
            orderFoodArrayList.add(orderFood);
        }

        return orderFoodArrayList;
    }

}
