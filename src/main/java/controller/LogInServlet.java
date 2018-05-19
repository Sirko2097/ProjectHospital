package controller;

import dao.implementations.DAODoctorImpl;
import dao.implementations.DAOFactoryImpl;
import model.Doctor;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * This servlet is using only by doctors or nurses,
 * because only of their job and access level.
  */
@WebServlet("/login")
public class LogInServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("jsp/login.jsp");
        requestDispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            Connection connection = DAOFactoryImpl.getInstance().getConnection();
            DAODoctorImpl daoDoctor = DAOFactoryImpl.getInstance().getDAODoctorImpl(connection);

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}