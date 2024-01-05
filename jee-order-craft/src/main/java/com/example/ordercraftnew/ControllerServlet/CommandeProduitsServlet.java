package com.example.ordercraftnew.ControllerServlet;

import com.example.ordercraftnew.DAO.ClientDAO;
import com.example.ordercraftnew.DAO.CommandeDAO;
import com.example.ordercraftnew.DAO.LigneCommandeDAO;
import com.example.ordercraftnew.DAO.ProduitDAO;
import com.example.ordercraftnew.Model.*;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@WebServlet(name = "commande-situation" , urlPatterns = {"/commande-situation", "/commande-situation/*"})
public class CommandeProduitsServlet extends HttpServlet {

    ProduitDAO produitDAO = new ProduitDAO();
    CommandeDAO commandeDAO = new CommandeDAO();
    LigneCommandeDAO ligneCommandeDAO = new LigneCommandeDAO();
    ClientDAO clientDAO = new ClientDAO();
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        String action = req.getServletPath() + (req.getPathInfo() != null ? req.getPathInfo() : "");

        switch (action)
        {
            case "/commande-situation/new-quantity":
                showFormAddQuantity(req, resp);
                break;
            case "/commande-situation/edit-status":
                showFormEditEtat(req, resp);
                break;
            case "/commande-situation/add":
                addQuantity(req, resp);
                break;
            case "/commande-situation/update":
                updateEtat(req, resp);
                break;
            default:
                listCommandes(req, resp);
                break;
        }
    }

    private void showFormAddQuantity(HttpServletRequest req, HttpServletResponse resp) throws ServletException,IOException
    {
        int id_produit = Integer.parseInt(req.getParameter("id_produit"));
        int id_cp = Integer.parseInt(req.getParameter("id_cp"));
        CommandeProduit commandeProduit = ligneCommandeDAO.getById(id_cp);
        Produit produit = produitDAO.getById(id_produit);
        req.setAttribute("commandeProduit", commandeProduit);
        req.setAttribute("produit", produit);
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/commande-situation-form-add-quantity.jsp");
        requestDispatcher.forward(req,resp);
    }

    private void showFormEditEtat(HttpServletRequest req,HttpServletResponse resp) throws ServletException,IOException
    {
        int id_commande = Integer.parseInt(req.getParameter("id_commande"));
        Commande commande = commandeDAO.getById(id_commande);
        Client client = clientDAO.getById(commande.getClient().getId());
        List<Etat> etats = Arrays.asList(Etat.values());
        List<Commande> commandes = commandeDAO.getAll();
        List<Produit> produits = produitDAO.getAll();
        req.setAttribute("produits", produits);
        List<CommandeProduit> commandeProduits = ligneCommandeDAO.getAll();
        req.setAttribute("client" , client);
        req.setAttribute("commandeProduits", commandeProduits);
        req.setAttribute("etats", etats);
        req.setAttribute("commandes", commandes);
        req.setAttribute("commande", commande);
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/commande-situation-form-edit-etat.jsp");
        requestDispatcher.forward(req,resp);
    }

    private void listCommandes(HttpServletRequest req,HttpServletResponse resp) throws ServletException,IOException
    {
        List<Produit> produits = produitDAO.getAll();
        List<CommandeProduit> commandeProduits = ligneCommandeDAO.getAll();
        List<Commande> commandes = commandeDAO.getAll();
        req.setAttribute("commandeProduits", commandeProduits);
        req.setAttribute("produits", produits);
        req.setAttribute("commandes", commandes);
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/situation-commandes.jsp");
        requestDispatcher.forward(req,resp);
    }

    private void addQuantity(HttpServletRequest req,HttpServletResponse resp) throws ServletException,IOException
    {
        int id_produit = Integer.parseInt(req.getParameter("id_produit"));
        int id_cp = Integer.parseInt(req.getParameter("id_cp"));
        int quantite_commander = Integer.parseInt(req.getParameter("quantite_produit"));
        CommandeProduit commandeProduit = new CommandeProduit();
        commandeProduit.setId(id_cp);
        commandeProduit.setQuantite(quantite_commander);
        Produit produit = produitDAO.getById(id_produit);
        ligneCommandeDAO.updateQuantityWithPrixTotal(commandeProduit,produit);
        resp.sendRedirect("list");
    }

    private void updateEtat(HttpServletRequest req, HttpServletResponse resp) throws ServletException,IOException
    {
        int id_client = Integer.parseInt(req.getParameter("id_client"));
        int id_commande = Integer.parseInt(req.getParameter("id_commande"));
        String address_livraison = req.getParameter("address_livraison");
        String etat_commande = req.getParameter("etat_commande");
        Client client = clientDAO.getById(id_client);
        Commande c = new Commande();
        c.setId(id_commande);
        c.setClient(client);
        c.setAddress_livrison(address_livraison);
        c.setEtat_commande(Etat.valueOf(etat_commande));
        commandeDAO.updateEtat(c);
        resp.sendRedirect("list");
    }
}
