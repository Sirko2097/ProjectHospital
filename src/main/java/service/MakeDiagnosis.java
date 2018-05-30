package service;

import dao.implementations.DAOFactoryImpl;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.sql.*;


@WebServlet("/diagnosis")
public class MakeDiagnosis extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("jsp/service/addDiagnosis.jsp");
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
            doGet(req, resp);
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
            e.printStackTrace();
        }
    }
}
