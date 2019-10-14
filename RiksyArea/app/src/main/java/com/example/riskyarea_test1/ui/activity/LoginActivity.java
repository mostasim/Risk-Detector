package com.example.riskyarea_test1.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.riskyarea_test1.Interfaces.UserLogInInterface;
import com.example.riskyarea_test1.R;
/**
 * @author Mahadi Hasan Joy
 * @version 1.0
 * @since 2019-10-14
 */
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
//                UserLogIn userLogIn = new UserLogIn();
//
//                userLogIn.setEmail(txtEmail.getText().toString());
//                userLogIn.setPassword(txtPass.getText().toString());
//
//                UserController userController=new UserController();
//                userController.setUserLogInInterface(LoginActivity.this);
//                userController.LogIn(userLogIn);

                success();
                finish();
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
        startActivity(new Intent(LoginActivity.this, BottomNavActivity.class));
    }

    @Override
    public void failed() {
        Toast.makeText(LoginActivity.this,"Pass Don't Match",Toast.LENGTH_SHORT).show();
    }
}
