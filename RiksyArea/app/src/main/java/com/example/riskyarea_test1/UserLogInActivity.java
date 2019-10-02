package com.example.riskyarea_test1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.riskyarea_test1.Interfaces.UserLogInInterface;
import com.example.riskyarea_test1.controller.UserController;
import com.example.riskyarea_test1.model.UserLogIn;

public class UserLogInActivity extends AppCompatActivity implements UserLogInInterface {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_log_in);

        final EditText txtEmail = (EditText) findViewById(R.id.txtEmailLogIn);
        final EditText txtPass = (EditText) findViewById(R.id.txtPasswordLogIn);

        Button btnLogIn = (Button) findViewById(R.id.btnLogIn);

        btnLogIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UserLogIn userLogIn = new UserLogIn();

                userLogIn.setEmail(txtEmail.getText().toString());
                userLogIn.setPassword(txtPass.getText().toString());

                UserController userController=new UserController();
                userController.setUserLogInInterface(UserLogInActivity.this);
                userController.LogIn(userLogIn);
            }
        });


    }

    @Override
    public void success() {
        Toast.makeText(UserLogInActivity.this,"Logged In",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void failed() {
        Toast.makeText(UserLogInActivity.this,"Pass Don't Match",Toast.LENGTH_SHORT).show();
    }
}
