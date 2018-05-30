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
import java.util.List;

@WebServlet("/certificate")
public class CertificateGenerator extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("jsp/certificate.jsp");
        requestDispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        DAOFactoryImpl daoFactory = DAOFactoryImpl.getInstance();
        Connection connection = null;
        try {
            connection = daoFactory.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        DAOPatientImpl daoPatient = daoFactory.getDAOPatientImpl(connection);

        HttpSession session = req.getSession();
        int cardNumber = Integer.parseInt(req.getParameter("cardNumber"));
        String doctorLastName = session.getAttribute("lastName").toString();
        List<String> listOfPatientParameters = null;
        try {
            listOfPatientParameters = daoPatient.getListOfPatientParameters(cardNumber);
        } catch (SQLException ex) {
            req.getRequestDispatcher("jsp/error/errorAddTreatment.jsp").forward(req, resp);
        }


        if (listOfPatientParameters != null) {
            listOfPatientParameters.add(doctorLastName);
        }

        session.setAttribute("patientsParameters", listOfPatientParameters);
        doGet(req, resp);
    }
}
