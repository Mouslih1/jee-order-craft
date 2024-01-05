package com.example.ordercraftnew.ControllerServlet;

import com.example.ordercraftnew.DAO.LoginDAO;
import com.example.ordercraftnew.Model.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet(name = "login" , urlPatterns = {"/login", "/login/*"})
public class LoginServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        HttpSession httpSession = req.getSession();
        String email = req.getParameter("email");
        String password = req.getParameter("password");

        LoginDAO loginDAO = new LoginDAO();
        User user = loginDAO.getUserByEmail(email);
        if(login(email, password))
        {
            httpSession.setAttribute("name", user.getName());
            resp.sendRedirect("welcome.jsp");
        }else{
            resp.sendRedirect("index.jsp?error=true");
        }
    }

    private boolean login(String email, String password)
    {
        LoginDAO loginDAO = new LoginDAO();
        return loginDAO.login(email, password);
    }
}
