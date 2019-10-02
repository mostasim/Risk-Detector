package com.example.riskyarea_test1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final Button btnLogin = (Button) findViewById(R.id.btnUser);
        final Button btnSignUp = (Button) findViewById(R.id.btnAdd);
        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openSignUpActivity();
            }
        });
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openLogInActivity();
            }
        });

    }

    public void openSignUpActivity()
    {
        Intent intent = new Intent(this, UserSignUpActivity.class);
        startActivity(intent);

    }

    public void openLogInActivity()
    {
        Intent intent=new Intent(this,UserLogInActivity.class);
        startActivity(intent);
    }
}
