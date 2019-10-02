package com.example.riskyarea_test1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.riskyarea_test1.Interfaces.UserSignUpInterface;
import com.example.riskyarea_test1.controller.UserController;
import com.example.riskyarea_test1.model.UserSignUp;

public class UserSignUpActivity extends AppCompatActivity implements UserSignUpInterface {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_sign_up);

        final EditText txtEmail = (EditText) findViewById(R.id.txtEmailSignUp);
        final EditText txtName = (EditText) findViewById(R.id.txtNameSignUp);
        final EditText txtPhone = (EditText) findViewById(R.id.txtPhoneSignUp);
        final EditText txtPassword = (EditText) findViewById(R.id.txtPasswordSignUp);
        final EditText txtAge = (EditText) findViewById(R.id.txtAgeSignUp);
        final EditText txtGender = (EditText) findViewById(R.id.txtGenderSignUp);

        Button btnSignUp = (Button) findViewById(R.id.btnSignUp);

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UserSignUp user =new UserSignUp();
                user.setName(txtName.getText().toString());
                user.setEmail(txtEmail.getText().toString());
                user.setPhoneNumber(txtPhone.getText().toString());
                user.setPassword(txtPassword.getText().toString());
                user.setAge(Integer.parseInt(txtAge.getText().toString()));
                user.setGender(txtGender.getText().toString());

                UserController userController = new UserController();
                userController.setUserSignUpInterface(UserSignUpActivity.this);
                userController.signUp(user);
            }
        });
    }

    @Override
    public void success() {
        Toast.makeText(UserSignUpActivity.this,"Success",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void faield() {
        Toast.makeText(UserSignUpActivity.this,"Fail",Toast.LENGTH_SHORT).show();
    }
}
