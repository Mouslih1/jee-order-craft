package com.example.ordercraftnew.ControllerServlet;

import com.example.ordercraftnew.DAO.ClientDAO;
import com.example.ordercraftnew.Model.Client;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "client", urlPatterns = {"/client", "/client/*"})
public class ClientServlet extends HttpServlet {

    ClientDAO clientDAO;
    public ClientServlet()
    {
        clientDAO = new ClientDAO();
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
            case "/client/new":
                showFormAdd(req, resp);
                break;
            case "/client/add":
                add(req, resp);
                break;
            case "/client/delete":
                delete(req,resp);
                System.out.println(action);
                break;
            case "/client/edit":
                showFormEdit(req, resp);
                break;
            case "/client/update":
                update(req, resp);
                break;
            default:
                listClient(req,resp);
                break;
        }
    }

    private void showFormAdd(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/client-form.jsp");
        requestDispatcher.forward(req,resp);
    }

    private void add(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        String name = req.getParameter("name");
        String email = req.getParameter("email");
        String ville = req.getParameter("ville");
        Client client = new Client(name, email, ville);
        clientDAO.add(client);
        resp.sendRedirect("list");
    }

    private void delete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        int id = Integer.parseInt(req.getParameter("id"));
        clientDAO.delete(id);
        resp.sendRedirect("list");
    }

    private void showFormEdit(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        int id = Integer.parseInt(req.getParameter("id"));
        Client client = clientDAO.getById(id);
        req.setAttribute("client", client);
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/client-form.jsp");
        requestDispatcher.forward(req,resp);
    }

    private void update(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        int id = Integer.parseInt(req.getParameter("id"));
        String name = req.getParameter("name");
        String email = req.getParameter("email");
        String ville = req.getParameter("ville");
        Client client = new Client(id, name, email, ville);
        clientDAO.update(client);
        resp.sendRedirect("list");
    }

    private void listClient(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        List<Client> clients = clientDAO.getAll();
        req.setAttribute("clients", clients);
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/clients.jsp");
        requestDispatcher.forward(req,resp);
    }
}
