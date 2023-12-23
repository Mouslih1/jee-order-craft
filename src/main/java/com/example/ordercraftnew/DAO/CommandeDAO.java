package com.example.ordercraftnew.DAO;

import com.example.ordercraftnew.Model.Client;
import com.example.ordercraftnew.Model.Commande;
import com.example.ordercraftnew.Model.Etat;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class CommandeDAO implements InterfaceDAOCommande {

    static Connection connection = DataBaseConnection.getConnection();
    private static String INSERT_COMMANDE = "INSERT INTO commandes (id_client, address_livraison, date_creation, etat_commande)  VALUES(?,?,?,?)";
    private static final String DELETE_COMMANDE = "DELETE FROM commandes WHERE id = ?";
    private static final String UPDATE_COMMANDE = "UPDATE commandes SET id_client=?, address_livraison=?, etat_commande=? WHERE id = ?";
    private static final String GET_COMMANDE_BY_ID = "SELECT * FROM commandes WHERE id = ?";
    private static final String GET_ALL_COMMANDES = "SELECT * FROM commandes";

    @Override
    public Commande add(Commande commande)
    {
        try{
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT_COMMANDE);
            preparedStatement.setInt(1, commande.getClient().getId());
            preparedStatement.setString(2, commande.getAddress_livraison());
            preparedStatement.setDate(3, Date.valueOf(LocalDate.now()));
            preparedStatement.setString(4, commande.getEtat_commande().name());

            preparedStatement.executeUpdate();

            PreparedStatement ps = connection.prepareStatement("SELECT MAX(id) AS MAX_ID FROM commandes");
            ResultSet rs = ps.executeQuery();

            if(rs.next())
            {
                commande.setId(rs.getInt("MAX_ID"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return commande;
    }

    @Override
    public boolean delete(int id)
    {
        boolean rowDeleted;
        try {
            PreparedStatement ps = connection.prepareStatement(DELETE_COMMANDE);
            ps.setInt(1, id);
            rowDeleted = ps.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return rowDeleted;
    }

    @Override
    public boolean update(Commande commande)
    {
        boolean rowUpdated;
        try{
            PreparedStatement ps = connection.prepareStatement(UPDATE_COMMANDE);
            ps.setInt(1, commande.getClient().getId());
            ps.setString(2, commande.getAddress_livraison());
            ps.setString(3, commande.getEtat_commande().name());
            ps.setInt(4, commande.getId());

            rowUpdated = ps.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return rowUpdated;
    }

    @Override
    public Commande getById(int id)
    {
        try{
            PreparedStatement ps = connection.prepareStatement(GET_COMMANDE_BY_ID);
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
    public List<Commande> getAll()
    {
        List<Commande> commandes = new ArrayList<>();
        try{
            PreparedStatement ps = connection.prepareStatement(GET_ALL_COMMANDES);
            ResultSet rs = ps.executeQuery();

            while (rs.next())
            {
                commandes.add(extractFromResultSet(rs));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return commandes;
    }

    @Override
    public Commande extractFromResultSet(ResultSet resultSet) throws SQLException
    {
        Commande commande = new Commande();
        ClientDAO clientDAO = new ClientDAO();
        Client client = clientDAO.getById(resultSet.getInt("id_client"));
        commande.setId(resultSet.getInt("id"));
        commande.setClient(client);
        commande.setAddress_livrison(resultSet.getString("address_livraison"));
        commande.setDate(resultSet.getDate("date_creation").toLocalDate());
        commande.setEtat_commande(Etat.valueOf(resultSet.getString("etat_commande")));

        return commande;
    }
}
