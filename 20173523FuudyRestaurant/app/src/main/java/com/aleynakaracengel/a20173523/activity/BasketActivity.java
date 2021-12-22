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
import com.aleynakaracengel.a20173523.adapter.BasketAdapter;
import com.aleynakaracengel.a20173523.database.BasketDao;
import com.aleynakaracengel.a20173523.database.DatabaseBasketCreator;
import com.aleynakaracengel.a20173523.model.OrderFood;
import java.util.ArrayList;

public class BasketActivity extends AppCompatActivity {
    private ImageView imageViewBasketBack = null;
    private RecyclerView recyclerViewBasket = null;
    private BasketAdapter basketAdapter = null;
    public static ArrayList<OrderFood> basketArrayList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_basket);

        setFindIds();
        setOnClicks();

        setLayoutToRecyclerView();
        setFoodToArrayList();
        setAdapterToRecyclerView();

    }

    private void setFindIds() {
        imageViewBasketBack = findViewById(R.id.imageViewBasketBack);
        recyclerViewBasket = findViewById(R.id.recyclerViewBasket);
    }

    private void setOnClicks() {
        imageViewBasketBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }

    private void setLayoutToRecyclerView() {
        recyclerViewBasket.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(BasketActivity.this, LinearLayoutManager.VERTICAL, false);
        recyclerViewBasket.setLayoutManager(linearLayoutManager);
    }

    private void setFoodToArrayList() {
        DatabaseBasketCreator databaseBasketCreator = new DatabaseBasketCreator(BasketActivity.this);
        basketArrayList = BasketDao.allBasketArrayList(databaseBasketCreator);
    }

    @SuppressLint("NotifyDataSetChanged")
    private void setAdapterToRecyclerView() {
        basketAdapter = new BasketAdapter(BasketActivity.this, basketArrayList);
        basketAdapter.notifyDataSetChanged();
        recyclerViewBasket.setAdapter(basketAdapter);
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(BasketActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}