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
@WebServlet("/main")
public class MainServlet extends HttpServlet {
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

    private void getAllDoctorPatients(HttpServletRequest request, HttpServletResponse response) throws SQLException {
        Connection connection = DAOFactoryImpl.getInstance().getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("");
        ResultSet resultSet = preparedStatement.executeQuery();

        List<Patient> patients = new ArrayList<>();

        while (resultSet.next()) {

        }
    }
}
