package servlets;

import dao.implementations.DAOFactoryImpl;
import dao.implementations.DAOPatientImpl;

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
 * This servlets add's new patient to list of all patients in the hospital.
 * */
@WebServlet("/addPatient")
public class AddPatientServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("jsp/addPatient.jsp");
        requestDispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        DAOFactoryImpl daoFactory = DAOFactoryImpl.getInstance();
        Connection connection;

        String passNumber = req.getParameter("passNumber").toUpperCase();
        String firstName = req.getParameter("firstName");
        String secondName = req.getParameter("secondName");
        String lastName = req.getParameter("lastName");
        String birthday = req.getParameter("birthday");
        String cardNumber = req.getParameter("cardNumber");

        try {
            connection = daoFactory.getConnection();
            DAOPatientImpl daoPatient = daoFactory.getDAOPatientImpl(connection);
            daoPatient.AddNewPatient(passNumber, firstName, secondName, lastName, birthday, cardNumber);
            doGet(req, resp);
        } catch (SQLException e) {
            req.getRequestDispatcher("jsp/error/errorAddingPatient.jsp").forward(req, resp);
            e.printStackTrace();
        }
    }
}
