package servlet;

import dao.implementations.DAOFactoryImpl;
import model.Patient;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


/**
 * This servlet was developed for producing main function of application.
 * From this servlet doctor can write a patient's medical certificate.
 * */
@WebServlet("/newPatients")
public class ListOfNewPatientsServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            getAllDoctorPatients(req, resp);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            getAllDoctorPatients(req, resp);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Output table with all doctor's patients
     * */
    private void getAllDoctorPatients(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
        Connection connection = DAOFactoryImpl.getInstance().getConnection();

        String license = request.getSession().getAttribute("license").toString();

        PreparedStatement preparedStatement = connection.prepareStatement("SELECT\n" +
                "  first_name,\n" +
                "  second_name,\n" +
                "  last_name,\n" +
                "  birthday,\n" +
                "  card_number\n" +
                "FROM HUMAN\n" +
                "  JOIN PATIENT P ON HUMAN.passport_number = P.passport_number\n" +
                "  JOIN DOCTOR_PATIENT D ON P.card_number = D.patient_card\n" +
                "  JOIN DOCTOR D2 on D.license = D2.license_number\n" +
                "WHERE license_number = '" + license + "'");

        ResultSet resultSet = preparedStatement.executeQuery();

        List<Patient> patients = new ArrayList<>();

        while (resultSet.next()) {
            patients.add(new Patient(resultSet.getString(1),
                    resultSet.getString(2), resultSet.getString(3),
                    resultSet.getDate(4), resultSet.getInt(5)));
        }

        request.setAttribute("patients", patients);
        request.getRequestDispatcher("jsp/listOfNewPatients.jsp").forward(request, response);


    }
}
