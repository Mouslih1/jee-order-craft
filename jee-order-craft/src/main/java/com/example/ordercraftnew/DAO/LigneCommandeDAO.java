package com.example.ordercraftnew.DAO;

import com.example.ordercraftnew.Model.Commande;
import com.example.ordercraftnew.Model.CommandeProduit;
import com.example.ordercraftnew.Model.Produit;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LigneCommandeDAO implements InterfaceDAOLigneCommande{

    static Connection connection = DataBaseConnection.getConnection();
    private static final String INSERT_LIGNE_COMMANDE = "INSERT INTO commande_produits(id_commande, id_produit) VALUES (?, ?)";
    private static final String UPDATE_QUANTITY_COMMANDER = "UPDATE commande_produits SET quantite_commander = ?, prix_total=? WHERE id = ?";
    private static final String GET_BY_LIGNE_COMMANDE_ID = "SELECT * FROM commande_produits WHERE id = ?";
    private static final String GET_ALL_LIGNE_COMMANDE = "SELECT * FROM commande_produits";

    ProduitDAO produitDAO = new ProduitDAO();
    CommandeDAO commandeDAO = new CommandeDAO();


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
    public CommandeProduit getById(int id)
    {
        try{
            PreparedStatement ps = connection.prepareStatement(GET_BY_LIGNE_COMMANDE_ID);
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
    public List<CommandeProduit> getAll()
    {
        List<CommandeProduit> commandeProduits = new ArrayList<>();
        try{
            PreparedStatement ps = connection.prepareStatement(GET_ALL_LIGNE_COMMANDE);
            ResultSet rs = ps.executeQuery();

            while (rs.next())
            {
                commandeProduits.add(extractFromResultSet(rs));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return commandeProduits;
    }

    @Override
    public CommandeProduit extractFromResultSet(ResultSet resultSet) throws SQLException
    {
        CommandeProduit commandeProduit = new CommandeProduit();
        Produit produit = produitDAO.getById(resultSet.getInt("id_produit"));
        Commande commande = commandeDAO.getById(resultSet.getInt("id_commande"));
        commandeProduit.setId(resultSet.getInt("id"));
        commandeProduit.setCommande(commande);
        commandeProduit.setProduit(produit);
        commandeProduit.setQuantite(resultSet.getInt("quantite_commander"));
        commandeProduit.setPrix_total(resultSet.getInt("prix_total"));
        return commandeProduit;
    }

    public void updateQuantityWithPrixTotal(CommandeProduit commandeProduit,Produit produit)
    {
        try{
            PreparedStatement ps = connection.prepareStatement(UPDATE_QUANTITY_COMMANDER);
            ps.setInt(1, commandeProduit.getQuantite());
            ps.setDouble(2, commandeProduit.getQuantite()*produit.getPrix());
            ps.setInt(3, commandeProduit.getId());

            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
