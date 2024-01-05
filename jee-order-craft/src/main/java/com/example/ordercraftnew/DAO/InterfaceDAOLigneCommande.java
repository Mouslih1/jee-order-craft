package com.example.ordercraftnew.DAO;

import com.example.ordercraftnew.Model.Commande;
import com.example.ordercraftnew.Model.CommandeProduit;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public interface InterfaceDAOLigneCommande {
    CommandeProduit add(CommandeProduit commandeProduit);
    CommandeProduit getById(int id);
    List<CommandeProduit> getAll();
    CommandeProduit extractFromResultSet(ResultSet resultSet) throws SQLException;
}
