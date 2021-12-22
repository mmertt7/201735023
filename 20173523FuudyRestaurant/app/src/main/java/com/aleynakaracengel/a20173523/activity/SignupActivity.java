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

public class SignupActivity extends AppCompatActivity {
    private TextInputEditText textInputEditTextSignupNameLastname, textInputEditTextSignupPhoneNumber, textInputEditTextSignupPassword = null;
    private CardView cardViewSignup = null;
    private TextView textViewHaveAlreadyAccount = null;

    private SharedPreferences sharedPreferences = null;
    private SharedPreferences.Editor editor = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        copyDb();

        getShared();

        setFindIds();

        setOnClicks();
    }

    private void copyDb() {
        final DatabaseMembershipCopy databaseMembershipCopy = new DatabaseMembershipCopy(SignupActivity.this);
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
        textInputEditTextSignupNameLastname = findViewById(R.id.textInputEditTextSignupNameLastname);
        textInputEditTextSignupPhoneNumber = findViewById(R.id.textInputEditTextSignupPhoneNumber);
        textInputEditTextSignupPassword = findViewById(R.id.textInputEditTextSignupPassword);
        cardViewSignup = findViewById(R.id.cardViewSignup);
        textViewHaveAlreadyAccount = findViewById(R.id.textViewHaveAlreadyAccount);
    }

    private void setOnClicks() {
        cardViewSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String namelastname = textInputEditTextSignupNameLastname.getText().toString().trim();
                String phonenumber = textInputEditTextSignupPhoneNumber.getText().toString().trim();
                String password = textInputEditTextSignupPassword.getText().toString();

                checkInformation(view, namelastname, phonenumber, password);

            }
        });
        textViewHaveAlreadyAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SignupActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    private void checkInformation(View view, String namelastname, String phonenumber, String password) {
        if (namelastname.length() >= 5 && Patterns.PHONE.matcher(phonenumber).matches() && password.length() >= 5) {
            final DatabaseMembershipCreator databaseMembershipCreator = new DatabaseMembershipCreator(SignupActivity.this);

            boolean controller = new MembershipDao().addUser(databaseMembershipCreator, namelastname, phonenumber, password);

            if (controller) {
                Snackbar.make(view, "This phone number has been used", Snackbar.LENGTH_SHORT).show();
            }else {
                editor.clear();
                editor.putBoolean("membership", true);
                editor.apply();

                Intent intent = new Intent(SignupActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }

        }
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(SignupActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}