package com.example.riskyarea_test1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.TextView;

import com.example.riskyarea_test1.Interfaces.UserListInterface;
import com.example.riskyarea_test1.controller.UserController;
import com.example.riskyarea_test1.model.UserSignUp;

import java.util.List;

public class UserListActivity extends AppCompatActivity implements UserListInterface {
    TextView textView;
    private RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_list);

        textView = findViewById(R.id.tvText);

        UserController userController = new UserController();
        userController.setUserListInterface(UserListActivity.this);
        userController.showUserList();
    }


    @Override
    public void success(List<UserSignUp> users) {
        textView.setText(users.get(users.size()-1).getName());
    }

    @Override
    public void failed(String msg) {

    }
}
