package com.aleynakaracengel.a20173523.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.aleynakaracengel.a20173523.R;
import com.aleynakaracengel.a20173523.adapter.OrderFoodAdapter;
import com.aleynakaracengel.a20173523.database.DatabaseOrderfoodCopy;
import com.aleynakaracengel.a20173523.database.DatabaseFoodTableCreator;
import com.aleynakaracengel.a20173523.database.OrderFoodDao;
import com.aleynakaracengel.a20173523.model.OrderFood;

import java.io.IOException;
import java.util.ArrayList;

public class OrderFoodActivity extends AppCompatActivity {
    private ImageView imageViewOrderFoodBack = null;
    private RecyclerView recyclerViewOrderFood = null;
    private OrderFoodAdapter orderFoodAdapter = null;
    private ArrayList<OrderFood> orderFoodArrayList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_food);

        copyDb();

        setFindIds();
        setOnClicks();

        setLayoutToRecyclerView();
        setFoodToArrayList();
        setAdapterToRecyclerView();
    }

    private void copyDb() {
        final DatabaseOrderfoodCopy databaseOrderfoodCopy = new DatabaseOrderfoodCopy(OrderFoodActivity.this);
        try {
            databaseOrderfoodCopy.createDataBase();
        } catch (IOException e) {
            e.printStackTrace();
        }
        databaseOrderfoodCopy.openDataBase();
    }

    private void setFindIds() {
        imageViewOrderFoodBack = findViewById(R.id.imageViewOrderFoodBack);
        recyclerViewOrderFood = findViewById(R.id.recyclerViewOrderFood);
    }

    private void setOnClicks() {
        imageViewOrderFoodBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }

    private void setLayoutToRecyclerView() {
        recyclerViewOrderFood.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(OrderFoodActivity.this, LinearLayoutManager.VERTICAL, false);
        recyclerViewOrderFood.setLayoutManager(linearLayoutManager);
    }

    private void setFoodToArrayList() {
        final DatabaseFoodTableCreator databaseFoodTableCreator = new DatabaseFoodTableCreator(OrderFoodActivity.this);
        orderFoodArrayList = OrderFoodDao.allOrderFoods(databaseFoodTableCreator);
    }

    @SuppressLint("NotifyDataSetChanged")
    private void setAdapterToRecyclerView() {
        orderFoodAdapter = new OrderFoodAdapter(OrderFoodActivity.this, orderFoodArrayList);
        orderFoodAdapter.notifyDataSetChanged();
        recyclerViewOrderFood.setAdapter(orderFoodAdapter);
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(OrderFoodActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}