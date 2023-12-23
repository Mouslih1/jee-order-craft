package com.example.ordercraftnew.DAO;

import com.example.ordercraftnew.Model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginDAO implements InterfaceLoginDAO{
    static Connection connection = DataBaseConnection.getConnection();
    static final String LOGIN_USER = "SELECT * FROM users WHERE email = ?";

    @Override
    public boolean login(String email, String password) {

        try{
            PreparedStatement ps = connection.prepareStatement(LOGIN_USER);
            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();
            if(rs.next())
            {
                return rs.getString("password").equals(password);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return false;
    }

    @Override
    public User getUserByEmail(String email) {
        try{
            PreparedStatement ps = connection.prepareStatement(LOGIN_USER);
            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();
            if(rs.next())
            {
                User user = new User();
                user.setEmail(rs.getString("email"));
                user.setName(rs.getString("name"));
                user.setPassword(rs.getString("password"));
                return user;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }


    public void addUser(User user)
    {
        try{
            String query = "INSERT INTO users (email, password) VALUES (?, ?)";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, user.getEmail());
            ps.setString(2, user.getPassword());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
