package com.example.ordercraftnew.ControllerServlet;

import com.example.ordercraftnew.DAO.ProduitDAO;
import com.example.ordercraftnew.Model.Produit;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "produit", urlPatterns = {"/produit", "/produit/*"})
public class ProduitServlet extends HttpServlet {


    ProduitDAO produitDao;
    public ProduitServlet()
    {
        produitDao = new ProduitDAO();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        this.doGet(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        String action = req.getServletPath() + (req.getPathInfo() != null ? req.getPathInfo() : "");
        switch (action)
        {
            case "/produit/new":
                showFormAdd(req, resp);
                break;
            case "/produit/add":
                add(req, resp);
                break;
            case "/produit/delete":
                delete(req,resp);
                System.out.println(action);
                break;
            case "/produit/edit":
                showFormEdit(req, resp);
                break;
            case "/produit/update":
                update(req, resp);
                break;
            default:
                listProduit(req,resp);
                break;
        }
    }

    private void showFormAdd(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/produit-form.jsp");
        requestDispatcher.forward(req,resp);
    }

    private void add(HttpServletRequest req, HttpServletResponse resp) throws IOException
    {
        String name = req.getParameter("name");
        String description = req.getParameter("description");
        double prix = Double.parseDouble(req.getParameter("prix"));
        int quantite_produit = Integer.parseInt(req.getParameter("quantite_produit"));
        Produit produit = new Produit(name,description,prix,quantite_produit);
        produitDao.add(produit);
        resp.sendRedirect("list");
    }

    private void delete(HttpServletRequest req, HttpServletResponse resp) throws IOException
    {

        int id = Integer.parseInt(req.getParameter("id"));
        produitDao.delete(id);
        resp.sendRedirect("list");
    }

    private void showFormEdit(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        int id = Integer.parseInt(req.getParameter("id"));
        Produit produit = produitDao.getById(id);
        req.setAttribute("produit", produit);
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/produit-form.jsp");
        requestDispatcher.forward(req,resp);
    }

    private void update(HttpServletRequest req, HttpServletResponse resp) throws IOException
    {
        int id = Integer.parseInt(req.getParameter("id"));
        String name = req.getParameter("name");
        String description = req.getParameter("description");
        double prix = Double.parseDouble(req.getParameter("prix"));
        int quantite_produit = Integer.parseInt(req.getParameter("quantite_produit"));
        Produit produit = new Produit(id, name, description, prix,quantite_produit);
       /* produit.setId(id);
        produit.setName(name);
        produit.setDescription(description);
        produit.setPrix(prix);
        produit.setQuantite_produit(quantite_produit);*/
        produitDao.update(produit);
        resp.sendRedirect("list");
    }

    private void listProduit(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        List<Produit> produits = produitDao.getAll();
        req.setAttribute("produits", produits);
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/produits.jsp");
        requestDispatcher.forward(req,resp);
    }
}
