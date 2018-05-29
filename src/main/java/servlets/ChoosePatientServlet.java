package servlets;

import dao.implementations.DAOFactoryImpl;
import dao.implementations.DAOPatientImpl;
import model.Patient;

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
import java.util.List;


@WebServlet("/choosePatient")
public class ChoosePatientServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            choosePatient(req, resp);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            choosePatient(req, resp);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void choosePatient(HttpServletRequest req, HttpServletResponse resp) throws SQLException, ServletException, IOException {
        HttpSession session = req.getSession();

        DAOFactoryImpl daoFactory = DAOFactoryImpl.getInstance();
        Connection connection = daoFactory.getConnection();
        DAOPatientImpl daoPatient = daoFactory.getDAOPatientImpl(connection);

        List<Patient> patients = (List<Patient>) session.getAttribute("patientsInTheHospital");

        List<Patient> freePatients;

        freePatients = daoPatient.existing(patients);
        session.setAttribute("freePatients", freePatients);

        RequestDispatcher requestDispatcher = req.getRequestDispatcher("jsp/choosePatient.jsp");
        requestDispatcher.forward(req, resp);
    }
}
