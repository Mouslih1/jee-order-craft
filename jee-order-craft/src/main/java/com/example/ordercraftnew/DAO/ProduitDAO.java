package com.example.ordercraftnew.DAO;


import com.example.ordercraftnew.Model.Produit;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProduitDAO implements InterfaceDAOProduit {
    static Connection connection = DataBaseConnection.getConnection();
    private static final String INSERT_PRODUIT = "INSERT INTO produits(name, description, prix, quantite_produit) VALUES (?, ?, ?,?)";
    private static final String DELETE_PRODUIT = "DELETE FROM produits WHERE id = ?";
    private static final String UPDATE_PRODUIT = "UPDATE produits SET name= ?, description=?, prix = ?, quantite_produit = ? WHERE id = ?";
    private static final String GET_PRODUIT_BY_ID = "SELECT * FROM produits WHERE id = ?";
    private static final String GET_ALL_PRODUITS = "SELECT * FROM produits";

    @Override
    public void add(Produit produit)
    {
        try{
            PreparedStatement ps = connection.prepareStatement(INSERT_PRODUIT);
            ps.setString(1, produit.getName());
            ps.setString(2, produit.getDescription());
            ps.setDouble(3, produit.getPrix());
            ps.setInt(4, produit.getQuantite_produit());

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
            PreparedStatement ps = connection.prepareStatement(DELETE_PRODUIT);
            ps.setInt(1, id);
            rowDeleted = ps.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return rowDeleted;
    }

    @Override
    public boolean update(Produit produit)
    {
        boolean rowUpdated;
        try{
            PreparedStatement ps = connection.prepareStatement(UPDATE_PRODUIT);
            ps.setString(1, produit.getName());
            ps.setString(2, produit.getDescription());
            ps.setDouble(3, produit.getPrix());
            ps.setInt(4, produit.getQuantite_produit());
            ps.setInt(5, produit.getId());

            rowUpdated = ps.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return rowUpdated;
    }

    @Override
    public Produit getById(int id)
    {
        try{
            PreparedStatement ps = connection.prepareStatement(GET_PRODUIT_BY_ID);
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
    public List<Produit> getAll()
    {
        List<Produit> produits = new ArrayList<>();
        try{
            PreparedStatement ps = connection.prepareStatement(GET_ALL_PRODUITS);
            ResultSet rs = ps.executeQuery();

            while (rs.next())
            {
                produits.add(extractFromResultSet(rs));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return produits;
    }

    @Override
    public Produit extractFromResultSet(ResultSet resultSet) throws SQLException
    {
        Produit produit = new Produit();
        produit.setId(resultSet.getInt("id"));
        produit.setName(resultSet.getString("name"));
        produit.setDescription(resultSet.getString("description"));
        produit.setPrix(resultSet.getDouble("prix"));
        produit.setQuantite_produit(resultSet.getInt("quantite_produit"));
        return produit;
    }

}
