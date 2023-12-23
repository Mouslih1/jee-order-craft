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
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@WebServlet(name = "commande", urlPatterns = {"/commande", "/commande/*"})
public class CommandeServlet extends HttpServlet {
    ClientDAO clientDAO;
    ProduitDAO produitDAO;
    CommandeDAO commandeDAO;
    LigneCommandeDAO ligneCommandeDAO;

    public CommandeServlet()
    {
        this.clientDAO = new ClientDAO();
        this.produitDAO = new ProduitDAO();
        this.commandeDAO = new CommandeDAO();
        this.ligneCommandeDAO = new LigneCommandeDAO();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getServletPath() + (req.getPathInfo() != null ? req.getPathInfo() : "");

        switch (action)
        {
            case  "/commande/add":
                add(req, resp);
                break;
            case "/commande/add_info":
                add_info(req, resp);
                break;
            default:
                list(req, resp);
                break;
        }
    }

    private void add(HttpServletRequest req, HttpServletResponse resp) throws ServletException,IOException
    {
        int id_client = Integer.parseInt(req.getParameter("id_client"));
        String address_livraison = req.getParameter("address_livraison");
        String etat_commande = req.getParameter("etat_commande");
        Commande c = new Commande();
        Client client = clientDAO.getById(id_client);
        c.setClient(client);
        c.setAddress_livrison(address_livraison);
        c.setEtat_commande(Etat.valueOf(etat_commande));
        Commande commande = commandeDAO.add(c);
        //Commande commande = commandeDAO.getById(c1.getId());
        System.out.println(commande.toString());
        req.setAttribute("commande", commande);
        list(req, resp);
    }

    private void add_info(HttpServletRequest req, HttpServletResponse resp) throws ServletException,IOException
    {
        int id_commande = Integer.parseInt(req.getParameter("id_commande"));
        int id_produit = Integer.parseInt(req.getParameter("id_product"));
        CommandeProduit cp = new CommandeProduit();
        Commande commande = commandeDAO.getById(id_commande);
        Produit produit = produitDAO.getById(id_produit);
        cp.setCommande(commande);
        cp.setProduit(produit);
        CommandeProduit commandeProduit = ligneCommandeDAO.add(cp);
        List<CommandeProduit> cpList = new ArrayList<>();
        cpList.add(commandeProduit);
        req.setAttribute("commande", commande);
        req.setAttribute("commande_produits", cpList);
        list(req, resp);
    }

    private void list(HttpServletRequest req, HttpServletResponse resp) throws ServletException,IOException
    {
        List<Produit> produits = produitDAO.getAll();
        List<Commande> commandes = commandeDAO.getAll();
        List<Client> clients = clientDAO.getAll();
        List<Etat> etats = Arrays.asList(Etat.values());
        req.setAttribute("etats", etats);
        req.setAttribute("produits", produits);
        req.setAttribute("commandes", commandes);
        req.setAttribute("clients", clients);
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/commande-form.jsp");
        requestDispatcher.forward(req,resp);
    }
}
