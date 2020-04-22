package com.example.riskyarea_test1.ui.activity;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.riskyarea_test1.Interfaces.UserLogInInterface;
import com.example.riskyarea_test1.R;
import com.example.riskyarea_test1.data.controller.UserController;
import com.example.riskyarea_test1.data.model.UserLogIn;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.MultiplePermissionsReport;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.multi.MultiplePermissionsListener;

import java.util.List;

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

        Dexter.withActivity(this)
                .withPermissions(Manifest.permission.ACCESS_FINE_LOCATION,
                        Manifest.permission.ACCESS_COARSE_LOCATION,
                        Manifest.permission.INTERNET)
                .withListener(new MultiplePermissionsListener() {
                    @Override
                    public void onPermissionsChecked(MultiplePermissionsReport report) {
                        Log.e("Permssion:",""+report.areAllPermissionsGranted());
                    }

                    @Override
                    public void onPermissionRationaleShouldBeShown(List<PermissionRequest> permissions, PermissionToken token) {

                    }
                })
                .check();
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
        startActivity(new Intent(LoginActivity.this, HomeActivity.class));
        finish();

    }

    @Override
    public void failed() {
        Toast.makeText(LoginActivity.this,"Pass Don't Match",Toast.LENGTH_SHORT).show();
    }
}
