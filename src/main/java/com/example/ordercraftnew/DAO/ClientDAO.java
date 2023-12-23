package com.example.ordercraftnew.DAO;

import com.example.ordercraftnew.DAO.InterfaceDAOClient;
import com.example.ordercraftnew.Model.Client;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ClientDAO implements InterfaceDAOClient {
    static Connection connection = DataBaseConnection.getConnection();
    private static final String INSERT_CLIENT = "INSERT INTO clients(name, email, ville) VALUES (?, ?, ?)";
    private static final String DELETE_CLIENT = "DELETE FROM clients WHERE id = ?";
    private static final String UPDATE_CLIENT = "UPDATE clients SET name=?, email=?, ville=? WHERE id = ?";
    private static final String GET_CLIENT_BY_ID = "SELECT * FROM clients WHERE id = ?";
    private static final String GET_ALL_CLIENTS = "SELECT * FROM clients";

    @Override
    public void add(Client client)
    {
        try{
            PreparedStatement ps = connection.prepareStatement(INSERT_CLIENT);
            ps.setString(1, client.getName());
            ps.setString(2, client.getEmail());
            ps.setString(3, client.getVille());

            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean delete(int id)
    {
        boolean rowDeleted;
        try {
            PreparedStatement ps = connection.prepareStatement(DELETE_CLIENT);
            ps.setInt(1, id);
            rowDeleted = ps.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return rowDeleted;
    }

    @Override
    public boolean update(Client client)
    {
        boolean rowUpdated;
        try{
            PreparedStatement ps = connection.prepareStatement(UPDATE_CLIENT);
            ps.setString(1, client.getName());
            ps.setString(2, client.getEmail());
            ps.setString(3, client.getVille());
            ps.setInt(4, client.getId());

            rowUpdated = ps.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return rowUpdated;
    }

    @Override
    public Client getById(int id)
    {
        try{
            PreparedStatement ps = connection.prepareStatement(GET_CLIENT_BY_ID);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if(rs.next())
            {
                return extractFromResultSet(rs);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return null;
    }

    @Override
    public List<Client> getAll() {
        List<Client> clients = new ArrayList<>();
        try{
            PreparedStatement ps = connection.prepareStatement(GET_ALL_CLIENTS);
            ResultSet rs = ps.executeQuery();

            while (rs.next())
            {
                clients.add(extractFromResultSet(rs));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return clients;
    }

    @Override
    public Client extractFromResultSet(ResultSet resultSet) throws SQLException {
        Client client = new Client();
        client.setId(resultSet.getInt("id"));
        client.setName(resultSet.getString("name"));
        client.setEmail(resultSet.getString("email"));
        client.setVille(resultSet.getString("ville"));
        return client;
    }
}
