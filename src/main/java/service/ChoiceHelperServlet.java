package service;

import dao.implementations.DAOFactoryImpl;
import dao.implementations.DAOPatientImpl;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

@WebServlet("/helper")
public class ChoiceHelperServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            helper(req, resp);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            helper(req, resp);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void helper(HttpServletRequest req, HttpServletResponse resp) throws SQLException, ServletException, IOException {
        HttpSession session = req.getSession();
        String license = session.getAttribute("license").toString();

        String passNumber = req.getParameter("passNumber");

        DAOFactoryImpl daoFactory = DAOFactoryImpl.getInstance();
        Connection connection = daoFactory.getConnection();
        DAOPatientImpl daoPatient = daoFactory.getDAOPatientImpl(connection);

        try {
            daoPatient.addDoctor(license, passNumber);
        } catch (SQLException e) {
            req.getRequestDispatcher("jsp/error/incorrectPassNumber.jsp").forward(req, resp);
            e.printStackTrace();
        }

        RequestDispatcher requestDispatcher = req.getRequestDispatcher("jsp/service/addPatientToList.jsp");
        requestDispatcher.forward(req, resp);

    }
}
