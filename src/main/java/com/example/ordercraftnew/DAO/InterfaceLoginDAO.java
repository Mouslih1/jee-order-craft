package com.example.ordercraftnew.DAO;

import com.example.ordercraftnew.Model.User;

public interface InterfaceLoginDAO {
    boolean login(String email,String password);
    User getUserByEmail(String email);
}
