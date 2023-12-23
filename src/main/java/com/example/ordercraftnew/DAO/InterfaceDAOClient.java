package com.example.ordercraftnew.DAO;

import com.example.ordercraftnew.Model.Client;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public interface InterfaceDAOClient {
    void add(Client client);
    boolean delete(int id);
    boolean update(Client client);
    Client getById(int id);
    List<Client> getAll();
    Client extractFromResultSet(ResultSet resultSet) throws SQLException;
}
