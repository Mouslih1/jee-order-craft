package com.example.ordercraftnew.DAO;

import com.example.ordercraftnew.Model.Commande;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public interface InterfaceDAOCommande {
    Commande add(Commande commande);
    boolean delete(int id);
    boolean update(Commande commande);
    Commande getById(int id);
    List<Commande> getAll();
    Commande extractFromResultSet(ResultSet resultSet) throws SQLException;
}
