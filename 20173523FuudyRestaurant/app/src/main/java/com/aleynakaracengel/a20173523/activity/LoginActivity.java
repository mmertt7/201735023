package com.aleynakaracengel.a20173523.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.TextView;

import com.aleynakaracengel.a20173523.R;
import com.aleynakaracengel.a20173523.database.DatabaseMembershipCopy;
import com.aleynakaracengel.a20173523.database.DatabaseMembershipCreator;
import com.aleynakaracengel.a20173523.database.MembershipDao;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;

import java.io.IOException;

public class LoginActivity extends AppCompatActivity {
    private TextInputEditText textInputEditTextPhoneNumber, textInputEditTextPassword = null;
    private CardView cardViewLogin = null;
    private TextView textViewHaveAnyAccount = null;

    private SharedPreferences sharedPreferences = null;
    private SharedPreferences.Editor editor = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        copyDb();

        getShared();

        setFindIds();

        setOnClicks();
    }

    private void copyDb() {
        final DatabaseMembershipCopy databaseMembershipCopy = new DatabaseMembershipCopy(LoginActivity.this);
        try {
            databaseMembershipCopy.createDataBase();
        }catch (IOException e) {
            Log.e("Hata", e.getMessage());
        }
    }

    private void getShared() {
        sharedPreferences = getSharedPreferences("membership", Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
    }

    private void setFindIds() {
        textInputEditTextPhoneNumber = findViewById(R.id.textInputEditTextPhoneNumber);
        textInputEditTextPassword = findViewById(R.id.textInputEditTextPassword);
        cardViewLogin = findViewById(R.id.cardViewLogin);
        textViewHaveAnyAccount = findViewById(R.id.textViewHaveAnyAccount);
    }

    private void setOnClicks() {
        cardViewLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String phonenumber = textInputEditTextPhoneNumber.getText().toString().trim();
                String password = textInputEditTextPassword.getText().toString();

                checkInformation(view, phonenumber, password);

            }
        });
        textViewHaveAnyAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, SignupActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    private void checkInformation(View view, String phonenumber, String password) {
        if (Patterns.PHONE.matcher(phonenumber).matches() && password.length() >= 5) {
            final DatabaseMembershipCreator databaseMembershipCreator = new DatabaseMembershipCreator(LoginActivity.this);

            boolean controller = new MembershipDao().checkuser(databaseMembershipCreator, phonenumber, password);

            sharedPreferences = getSharedPreferences("membership", Context.MODE_PRIVATE);
            editor = sharedPreferences.edit();

            if (controller) {
                editor.clear();
                editor.putBoolean("membership", true);
                editor.apply();

                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }else {
                Snackbar.make(view, "No such user found", Snackbar.LENGTH_SHORT).show();
            }

        }
    }


    @Override
    public void onBackPressed() {
        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}