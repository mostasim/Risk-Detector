package com.example.riskyarea_test1;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.riskyarea_test1.Interfaces.UserSignUpInterface;
import com.example.riskyarea_test1.controller.UserController;
import com.example.riskyarea_test1.model.UserSignUp;


public class RegistrationActivity extends AppCompatActivity implements UserSignUpInterface {
    String gender = "Male";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);


        final EditText txtEmail = findViewById(R.id.txtEmailSignUp);
        final EditText txtName = findViewById(R.id.txtUserName);
        final EditText txtPhone = findViewById(R.id.txtPhone);
        final EditText txtPassword = findViewById(R.id.txtPasswordSignUp);
        final EditText txtAge = findViewById(R.id.txtAge);
        final RadioGroup txtGender = findViewById(R.id.radioSex);

        Button btnSignUp = findViewById(R.id.btnSignUp);
        txtGender.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                Log.e("__DEBUG__","ID: "+checkedId);
                if (checkedId == R.id.radioFemale) {
                    gender = "Female";
                } else
                    gender = "Male";
            }
        });

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UserSignUp user = new UserSignUp();
                user.setName(txtName.getText().toString());
                user.setEmail(txtEmail.getText().toString());
                user.setPhoneNumber(txtPhone.getText().toString());
                user.setPassword(txtPassword.getText().toString());
                user.setAge(Integer.parseInt(txtAge.getText().toString()));
                user.setGender(gender);
                UserController userController = new UserController();
                userController.setUserSignUpInterface(RegistrationActivity.this);
                userController.signUp(user);
            }
        });

        TextView signIn_text = findViewById(R.id.signIn_text);
        signIn_text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(RegistrationActivity.this, LoginActivity.class));
                finish();
            }
        });
    }

    @Override
    public void success() {
        Toast.makeText(RegistrationActivity.this, "Success", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void failed() {
        Toast.makeText(RegistrationActivity.this, "Fail", Toast.LENGTH_SHORT).show();
    }
}
