package com.aleynakaracengel.a20173523.activity;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;

import com.aleynakaracengel.a20173523.R;
import com.aleynakaracengel.a20173523.database.BasketDao;
import com.aleynakaracengel.a20173523.database.DatabaseBasketCopy;
import com.aleynakaracengel.a20173523.database.DatabaseBasketCreator;
import com.aleynakaracengel.a20173523.model.OrderFood;
import com.google.android.material.snackbar.Snackbar;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {
    private CardView cardViewLogin, cardViewSignup, cardViewSignout, cardViewOrderFood, cardViewBasket, cardViewPrinter;

    private SharedPreferences sharedPreferences = null;
    private SharedPreferences.Editor editor = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        copyDb();

        setFindIds();

        membershipcontroller();

        setOnClicks();
    }

    private void copyDb() {
        final DatabaseBasketCopy databaseBasketCopy = new DatabaseBasketCopy(MainActivity.this);
        try {
            databaseBasketCopy.createDataBase();
        } catch (IOException e) {
            e.printStackTrace();
        }
        databaseBasketCopy.openDataBase();
    }

    private void setFindIds() {
        cardViewLogin = findViewById(R.id.cardViewLogin);
        cardViewSignup = findViewById(R.id.cardViewSignup);
        cardViewSignout = findViewById(R.id.cardViewSignout);
        cardViewOrderFood = findViewById(R.id.cardViewOrderFood);
        cardViewBasket = findViewById(R.id.cardViewBasket);
        cardViewPrinter = findViewById(R.id.cardViewPrinter);
    }

    private void membershipcontroller() {
        sharedPreferences = getSharedPreferences("membership", Context.MODE_PRIVATE);
        boolean controller = sharedPreferences.getBoolean("membership", false);

        if (controller) {
            cardViewLogin.setVisibility(View.INVISIBLE);
            cardViewSignup.setVisibility(View.INVISIBLE);
            cardViewSignout.setVisibility(View.VISIBLE);
        }else {
            cardViewLogin.setVisibility(View.VISIBLE);
            cardViewSignup.setVisibility(View.VISIBLE);
            cardViewSignout.setVisibility(View.INVISIBLE);
        }
    }

    private void setOnClicks() {
        cardViewLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });
        cardViewSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, SignupActivity.class);
                startActivity(intent);
                finish();
            }
        });
        cardViewSignout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sharedPreferences = getSharedPreferences("membership", Context.MODE_PRIVATE);
                editor = sharedPreferences.edit();

                editor.clear();
                editor.apply();

                startActivity(getIntent());

            }
        });
        cardViewOrderFood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, OrderFoodActivity.class);
                startActivity(intent);
                finish();
            }
        });
        cardViewBasket.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, BasketActivity.class);
                startActivity(intent);
                finish();
            }
        });
        cardViewPrinter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                printreceipt();

                Snackbar.make(view, "Cart slip created successfully.", Snackbar.LENGTH_LONG)
                        .setBackgroundTint(getResources().getColor(R.color.purple_700))
                        .setTextColor(getResources().getColor(R.color.white)).show();
            }
        });
    }

    private void printreceipt() {
        final DatabaseBasketCreator databaseBasketCreator = new DatabaseBasketCreator(MainActivity.this);

        StringBuilder stringBuilder = new StringBuilder();

        for (OrderFood orderFood : BasketDao.allBasketArrayList(databaseBasketCreator)
             ) {
            stringBuilder.append("ID: ").append(orderFood.getId()).append(" - Name: ").append(orderFood.getName()).append(" - Price: ").append(orderFood.getPrice()).append("\n");
        }

        File file = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).getPath(), "Fuudy Restaurant Receipt.txt");

        try {
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            fileOutputStream.write(stringBuilder.toString().getBytes());
            fileOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void onBackPressed() {
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setTitle("CLOSE APP");
        builder.setMessage("Are you sure you want to close the app ?");
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                finish();
            }
        });
        builder.setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        builder.create().show();
    }
}