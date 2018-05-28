package servlet;

import dao.implementations.DAOFactoryImpl;
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
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


/**
 * In this servlet doctor can add treatment and operation.
 * */
@WebServlet("/addTreatment")
public class AddTreatmentServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("");
        requestDispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            addTreatment(req, resp);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void addTreatment(HttpServletRequest req, HttpServletResponse resp) throws SQLException {
        DAOFactoryImpl daoFactory = DAOFactoryImpl.getInstance();
        Connection connection = daoFactory.getConnection();
        HttpSession session = req.getSession();

        String license = session.getAttribute("license").toString();
        String medicines = req.getParameter("medicines");
        String procedures = req.getParameter("procedures");
        int operation = Integer.parseInt(req.getParameter("operation"));
        int cardNumber = Integer.parseInt(session.getAttribute("cardNumber").toString());

        try {
            connection.setAutoCommit(false);

            PreparedStatement preparedStatement = connection.prepareStatement("SELECT\n" +
                    "  HUMAN.passport_number," +
                    "  first_name,\n" +
                    "  second_name,\n" +
                    "  last_name,\n" +
                    "  birthday,\n" +
                    "  card_number\n" +
                    "FROM HUMAN\n" +
                    "  JOIN PATIENT P ON HUMAN.passport_number = P.passport_number\n" +
                    "  JOIN DOCTOR_PATIENT D ON P.card_number = D.patient_card\n" +
                    "  JOIN DOCTOR D2 on D.license = D2.license_number\n" +
                    "WHERE card_number=" + cardNumber);
            ResultSet resultSet = preparedStatement.executeQuery();

            Patient patient = new Patient(resultSet.getString(1), resultSet.getString(2),
                        resultSet.getString(3), resultSet.getString(4), resultSet.getDate(5),
                        resultSet.getInt(6));


            connection.commit();
        } catch (SQLException e) {
            connection.rollback();
            e.printStackTrace();
        }
    }
}
