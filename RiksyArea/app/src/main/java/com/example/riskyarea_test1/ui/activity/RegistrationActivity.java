package com.example.riskyarea_test1.ui.activity;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
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
        androidx.appcompat.widget.Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setNavigationIcon(R.drawable.back_button);
        setSupportActionBar(toolbar);
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
                if (TextUtils.isEmpty(txtDegree.getText())
                        || TextUtils.isEmpty(txtEmail.getText())
                        || TextUtils.isEmpty(txtName.getText())
                        || TextUtils.isEmpty(txtLocationWantToServe.getText())
                        || TextUtils.isEmpty(txtWorkInPlace.getText())
                        || TextUtils.isEmpty(txtPhone.getText())
                        || TextUtils.isEmpty(txtSpecializedIn.getText())){
                    Toast.makeText(RegistrationActivity.this, "All field must be filled", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (!TextUtils.isDigitsOnly(txtPhone.getText()) ){
                    Toast.makeText(RegistrationActivity.this, "Invalid Phone Number", Toast.LENGTH_SHORT).show();
                    return;
                }
                DoctorDto doctorDto = new DoctorDto();
                doctorDto.setName(txtName.getText().toString());
                doctorDto.setEmail(txtEmail.getText().toString());
                doctorDto.setDegree(txtDegree.getText().toString());
                doctorDto.setLocationWantToServeIn(txtLocationWantToServe.getText().toString());
                doctorDto.setPhone(txtPhone.getText().toString());
                doctorDto.setWorkingPlace(txtWorkInPlace.getText().toString());
                doctorDto.setSpecializedIn(txtSpecializedIn.getText().toString());

                if (isValidEmail(txtEmail.getText())){
                    registerUser(doctorDto);
                }else {
                    Toast.makeText(RegistrationActivity.this, "Invalid Email", Toast.LENGTH_SHORT).show();
                }

            }
        });

    }
    public static boolean isValidEmail(CharSequence target) {
        return (!TextUtils.isEmpty(target) && Patterns.EMAIL_ADDRESS.matcher(target).matches());
    }
    private void registerUser(DoctorDto doctorDto) {
        DoctorController userController = new DoctorController();
        userController.registerDoctor(doctorDto).observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                if (aBoolean) {
                    DoctorController doctorController = new DoctorController();
                    doctorController.getDoctorList();
                    Toast.makeText(RegistrationActivity.this, "Success", Toast.LENGTH_SHORT).show();
                    finish();
                } else {
                    Toast.makeText(RegistrationActivity.this, "Email already exists", Toast.LENGTH_SHORT).show();
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
