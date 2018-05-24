package servlet;

import dao.implementations.DAOFactoryImpl;
import dao.implementations.DAOPatientImpl;
import model.Patient;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;


/**
 * Servlet, which output table with all patients in the hospital.
 * */
@WebServlet("/allPatients")
public class ListOfAllPatientsServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            getAllPatients(req, resp);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            getAllPatients(req, resp);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void getAllPatients(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
        DAOFactoryImpl daoFactory = DAOFactoryImpl.getInstance();
        Connection connection = daoFactory.getConnection();
        DAOPatientImpl daoPatient = daoFactory.getDAOPatientImpl(connection);

        List<Patient> patients = daoPatient.getAllPatients();
        request.setAttribute("patientsInTheHospital", patients);
        request.getRequestDispatcher("jsp/allPatients.jsp").forward(request, response);
    }
}
