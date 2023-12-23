package com.example.ordercraftnew.DAO;

import com.example.ordercraftnew.Model.CommandeProduit;

import java.sql.*;
import java.time.LocalDate;

public class LigneCommandeDAO implements InterfaceDAOLigneCommande{
    static Connection connection = DataBaseConnection.getConnection();
    private static final String INSERT_LIGNE_COMMANDE = "INSERT INTO commande_produits(id_commande, id_produit) VALUES (?, ?)";
    private static final String UPDATE_LIGNE_COMMANDE = "UPDATE commande_produits SET prix_total=? WHERE id_commande = ?";

    @Override
    public CommandeProduit add(CommandeProduit commandeProduit)
    {
        try{
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT_LIGNE_COMMANDE);
            preparedStatement.setInt(1, commandeProduit.getCommande().getId());
            preparedStatement.setInt(2, commandeProduit.getProduit().getId());

            preparedStatement.executeUpdate();

            PreparedStatement ps = connection.prepareStatement("SELECT MAX(id) AS MAX_ID FROM commande_produits");
            ResultSet rs = ps.executeQuery();

            if(rs.next())
            {
                commandeProduit.setId(rs.getInt("MAX_ID"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return commandeProduit;
    }

    @Override
    public CommandeProduit getById(int id) {
        return null;
    }
}
