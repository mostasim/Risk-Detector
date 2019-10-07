package com.example.riskyarea_test1.Interfaces;

import com.example.riskyarea_test1.model.UserList;

import java.util.List;

public interface UserListInterface {
    void success(List<UserList> users);

    void failed(String msg);
}
