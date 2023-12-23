package com.example.ordercraftnew.DAO;

import com.example.ordercraftnew.Model.Commande;
import com.example.ordercraftnew.Model.Produit;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public interface InterfaceDAOProduit {
    void add(Produit produit);
    boolean delete(int id);
    boolean update(Produit produit);
    Produit getById(int id);
    List<Produit> getAll();
    Produit extractFromResultSet(ResultSet resultSet) throws SQLException;
}
