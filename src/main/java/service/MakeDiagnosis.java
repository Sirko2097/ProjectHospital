package service;

import dao.implementations.DAOFactoryImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/diagnosis")
public class MakeDiagnosis extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            makeDiagnosis(req, resp);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            makeDiagnosis(req, resp);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void makeDiagnosis(HttpServletRequest req, HttpServletResponse resp) throws SQLException, UnsupportedEncodingException {
        DAOFactoryImpl daoFactory = DAOFactoryImpl.getInstance();
        Connection connection = daoFactory.getConnection();

        HttpSession session = req.getSession();
        req.setCharacterEncoding("utf8");
        String cardNumber = req.getParameter("cardNumber");
        String diagnosis = req.getParameter("diagnosis");
        String beginningOfCertificate = req.getParameter("beginning");

        try {
            int diagCounter = 1;
            connection.setAutoCommit(false);
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT COUNT(diagnosis_id) " +
                    "FROM DIAGNOSIS");
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                diagCounter = resultSet.getInt(1);
                diagCounter++;
            }

            preparedStatement = connection.prepareStatement("INSERT INTO DIAGNOSIS (diagnosis_id, disease_name, date) " +
                    "VALUES ("+ diagCounter+", '" + diagnosis + "', '" + beginningOfCertificate + "')");
            preparedStatement.execute();

            preparedStatement = connection.prepareStatement("INSERT INTO CARD_DIAGNOSIS (card_num, diag_id) " +
                    "VALUES ('" + cardNumber + "'," + diagCounter + ")");
            preparedStatement.execute();

            connection.commit();
        } catch (SQLException e) {
            connection.rollback();
            e.printStackTrace();
        }


    }
}
