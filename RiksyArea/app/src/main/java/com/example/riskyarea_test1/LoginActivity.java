package com.example.riskyarea_test1;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.riskyarea_test1.Interfaces.UserLogInInterface;
import com.example.riskyarea_test1.controller.UserController;
import com.example.riskyarea_test1.model.UserLogIn;

public class LoginActivity extends AppCompatActivity implements UserLogInInterface {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        final EditText txtEmail = findViewById(R.id.txtEmailLogIn);
        final EditText txtPass = findViewById(R.id.txtPasswordLogIn);
        TextView signUp_text = findViewById(R.id.signUp_text);
        Button btnLogIn = findViewById(R.id.btnLogIn);

        btnLogIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UserLogIn userLogIn = new UserLogIn();

                userLogIn.setEmail(txtEmail.getText().toString());
                userLogIn.setPassword(txtPass.getText().toString());

                UserController userController=new UserController();
                userController.setUserLogInInterface(LoginActivity.this);
                userController.LogIn(userLogIn);
            }
        });


        signUp_text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, RegistrationActivity.class));
                finish();
            }
        });
    }

    @Override
    public void success() {
        Toast.makeText(LoginActivity.this,"Logged In",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void failed() {
        Toast.makeText(LoginActivity.this,"Pass Don't Match",Toast.LENGTH_SHORT).show();
    }
}
