package com.example.ordercraftnew.DAO;

import com.example.ordercraftnew.Model.Client;
import com.example.ordercraftnew.Model.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public interface InterfaceDAOUser {
    void add(User user);
    boolean delete(int id);
    boolean update(User user);
    User getById(int id);
    List<User> getAll();
    User extractFromResultSet(ResultSet resultSet) throws SQLException;
}
