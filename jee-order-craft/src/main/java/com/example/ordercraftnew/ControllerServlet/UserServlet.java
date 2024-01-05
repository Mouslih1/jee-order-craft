package com.example.ordercraftnew.ControllerServlet;

import com.example.ordercraftnew.DAO.UserDAO;
import com.example.ordercraftnew.Model.Produit;
import com.example.ordercraftnew.Model.User;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "user", urlPatterns = {"/user", "/user/*"})
public class UserServlet extends HttpServlet {

    UserDAO userDAO;

    public UserServlet()
    {
        this.userDAO = new UserDAO();
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
            case "/user/new":
                showFormUser(req,resp);
                break;
            case "/user/add":
                add(req, resp);
                break;
            case "/user/edit":
                showFormEdit(req, resp);
                break;
            case "/user/update":
                update(req, resp);
                break;
            case "/user/delete":
                delete(req, resp);
                break;
            default:
                listUser(req, resp);
                break;
        }
    }

    private void showFormUser(HttpServletRequest req, HttpServletResponse resp) throws ServletException,IOException
    {
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/user-form.jsp");
        requestDispatcher.forward(req,resp);
    }

    private void add(HttpServletRequest req, HttpServletResponse resp) throws  ServletException,IOException
    {
        String name = req.getParameter("name");
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        User user = new User();
        user.setName(name);
        user.setEmail(email);
        user.setPassword(password);
        userDAO.add(user);
        resp.sendRedirect("list");
    }

    private void delete(HttpServletRequest req, HttpServletResponse resp) throws ServletException,IOException
    {
        int id = Integer.parseInt(req.getParameter("id"));
        userDAO.delete(id);
        resp.sendRedirect("list");
    }

    private void showFormEdit(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        int id = Integer.parseInt(req.getParameter("id"));
        User user = userDAO.getById(id);
        req.setAttribute("user", user);
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/user-form.jsp");
        requestDispatcher.forward(req,resp);
    }

    private void update(HttpServletRequest req, HttpServletResponse resp) throws IOException
    {
        int id = Integer.parseInt(req.getParameter("id"));
        String name = req.getParameter("name");
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        User user = new User();
        user.setId(id);
        user.setName(name);
        user.setEmail(email);
        user.setPassword(password);
        userDAO.update(user);
        resp.sendRedirect("list");
    }

    private void listUser(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        List<User> users = userDAO.getAll();
        req.setAttribute("users", users);
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/users.jsp");
        requestDispatcher.forward(req,resp);
    }

}
