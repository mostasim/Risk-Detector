package com.example.riskyarea_test1.ui.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.riskyarea_test1.Interfaces.UserSignUpInterface;
import com.example.riskyarea_test1.R;
import com.example.riskyarea_test1.data.controller.DoctorController;
import com.example.riskyarea_test1.data.dto.DoctorDto;
import com.example.riskyarea_test1.ui.fragment.DoctorListViewModel;

/**
 * @author Mahadi Hasan Joy
 * @version 1.0
 * @since 2019-10-14
 */
public class RegistrationActivity extends AppCompatActivity implements UserSignUpInterface {
    String gender = "Male";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        DoctorListViewModel mViewModel = ViewModelProviders.of(this).get(DoctorListViewModel.class);

        final EditText txtEmail = findViewById(R.id.txtEmailSignUp);
        final EditText txtName = findViewById(R.id.txtUserName);
        final EditText txtPhone = findViewById(R.id.txtPhone);
        final EditText txtWorkInPlace = findViewById(R.id.txtWorkInPlace);
        final EditText txtDegree = findViewById(R.id.txtDegree);
        final EditText txtSpecializedIn = findViewById(R.id.txtSpecializedIn);
        final EditText txtLocationWantToServe = findViewById(R.id.txtLocationWantToServe);

        Button btnSignUp = findViewById(R.id.btnSignUp);


        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DoctorDto doctorDto = new DoctorDto();
                doctorDto.setName(txtName.getText().toString());
                doctorDto.setEmail(txtEmail.getText().toString());
                doctorDto.setDegree(txtDegree.getText().toString());
                doctorDto.setLocationWantToServeIn(txtLocationWantToServe.getText().toString());
                doctorDto.setPhone(txtPhone.getText().toString());
                doctorDto.setWorkingPlace(txtWorkInPlace.getText().toString());
                doctorDto.setSpecializedIn(txtSpecializedIn.getText().toString());
                registerUser(doctorDto);
            }
        });

    }

    private void registerUser(DoctorDto doctorDto) {
        DoctorController userController = new DoctorController();
        userController.registerDoctor(doctorDto).observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                if (aBoolean) {
                    Toast.makeText(RegistrationActivity.this, "Success", Toast.LENGTH_SHORT).show();
                    finish();
                } else {
                    Toast.makeText(RegistrationActivity.this, "Email Exists", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public void success() {
        Toast.makeText(RegistrationActivity.this, "Success", Toast.LENGTH_SHORT).show();
        finish();
    }

    @Override
    public void failed() {
        Toast.makeText(RegistrationActivity.this, "Email Exists", Toast.LENGTH_SHORT).show();
    }
}
