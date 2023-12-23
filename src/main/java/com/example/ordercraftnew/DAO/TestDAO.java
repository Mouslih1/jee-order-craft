package com.example.ordercraftnew.DAO;

import com.example.ordercraftnew.Model.*;

import java.time.LocalDate;

public class TestDAO {
    public static void main(String[] args) {
        ClientDAO clientDAO = new ClientDAO();
        //System.out.println(clientDAO.delete(3));
        //Client client = clientDAO.getById(2);
        //commandeDAO.delete(1);
        //commandeDAO.add(new Commande(client,"residence amal", LocalDate.now(),"en cours"));
        //commandeDAO.update(new Commande(1,client,"residence amal", LocalDate.now(),"en cours"));
        //System.out.println(commandeDAO.getById(2));
        ProduitDAO produitDAO = new ProduitDAO();
        //produitDAO.add(new Produit("iphone X ", "aaaaaaaaaaaaaaa", 3500));
        //produitDAO.delete(1);
        //produitDAO.update(new Produit(2, "iphone 12", "eeeeeeeeeeeee", 3000));
        //System.out.println(produitDAO.getById(2));
        ///System.out.println(produitDAO.getAll());
        LigneCommandeDAO ligneCommandeDAO = new LigneCommandeDAO();
        System.out.print(ligneCommandeDAO.getAll());
        ClientDAO clientDAO1 = new ClientDAO();
        System.out.println(clientDAO1.getAll());
    }

}
