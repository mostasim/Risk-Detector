package com.example.riskyarea_test1.Interfaces;

import com.example.riskyarea_test1.model.UserSignUp;

import java.util.List;

public interface UserListInterface {
    void success(List<UserSignUp> users);

    void failed(String msg);
}
