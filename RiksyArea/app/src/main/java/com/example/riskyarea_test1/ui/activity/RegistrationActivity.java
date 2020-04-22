package com.example.riskyarea_test1.ui.activity;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.riskyarea_test1.Interfaces.UserSignUpInterface;
import com.example.riskyarea_test1.R;
import com.example.riskyarea_test1.data.controller.DoctorController;
import com.example.riskyarea_test1.data.dto.DoctorDto;
import com.example.riskyarea_test1.ui.view_model.DoctorListViewModel;

/**
 * @author Mahadi Hasan Joy
 * @version 1.0
 * @since 2019-10-14
 */
public class RegistrationActivity extends AppCompatActivity implements UserSignUpInterface {
    String gender = "Male";

    public static boolean isValidEmail(CharSequence target) {
        return (!TextUtils.isEmpty(target) && Patterns.EMAIL_ADDRESS.matcher(target).matches());
    }

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
        final Spinner spinnerSpecialize = findViewById(R.id.txtSpecializedIn);
        final ImageButton ivSpinnerArrow = findViewById(R.id.ivSpinnerArrow);
        final EditText txtLocationWantToServe = findViewById(R.id.txtLocationWantToServe);

        Button btnSignUp = findViewById(R.id.btnSignUp);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, getResources()
                .getStringArray(R.array.specialized_in));//setting the country_array to spinner
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerSpecialize.setAdapter(adapter);
        spinnerSpecialize.setSelection(0);
        spinnerSpecialize.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1,
                                       int position, long id) {
                String[] selected = getResources().getStringArray(R.array.specialized_in);
                Log.e("SPINNER ", selected[position]);
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
            }
        });
        ivSpinnerArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                spinnerSpecialize.performClick();
            }
        });
        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(txtDegree.getText())
                        || TextUtils.isEmpty(txtEmail.getText())
                        || TextUtils.isEmpty(txtName.getText())
                        || TextUtils.isEmpty(txtLocationWantToServe.getText())
                        || TextUtils.isEmpty(txtWorkInPlace.getText())
                        || TextUtils.isEmpty(txtPhone.getText())) {
                    Toast.makeText(RegistrationActivity.this, "All field must be filled", Toast.LENGTH_SHORT).show();
                    return;
                }
                if ((spinnerSpecialize.getSelectedItem().toString().equalsIgnoreCase("Select"))) {
                    Toast.makeText(RegistrationActivity.this, "Invalid specialize selection", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (!TextUtils.isDigitsOnly(txtPhone.getText())) {
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
                doctorDto.setSpecializedIn(spinnerSpecialize.getSelectedItem().toString());

                if (isValidEmail(txtEmail.getText())) {
                    registerUser(doctorDto);
                } else {
                    Toast.makeText(RegistrationActivity.this, "Invalid Email", Toast.LENGTH_SHORT).show();
                }

            }
        });

    }

    private void registerUser(DoctorDto doctorDto) {
        DoctorController userController = DoctorController.getController();
        userController.registerDoctor(doctorDto).observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                if (aBoolean) {
                    DoctorController doctorController = DoctorController.getController();
                    doctorController.fetchDoctorList();
                    Toast.makeText(RegistrationActivity.this, "Successfully submitted", Toast.LENGTH_SHORT).show();
                    finish();
                } else {
                    Toast.makeText(RegistrationActivity.this, "Email already exists", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public void success() {
        Toast.makeText(RegistrationActivity.this, "Successfully submitted", Toast.LENGTH_SHORT).show();
        finish();
    }

    @Override
    public void failed() {
        Toast.makeText(RegistrationActivity.this, "Email already exists", Toast.LENGTH_SHORT).show();
    }
}
