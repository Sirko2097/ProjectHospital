package servlet;

import dao.implementations.DAOFactoryImpl;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;


/**
 * In this servlet doctor can add treatment and operation.
 * */
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
        try {
            connection.setAutoCommit(false);

            PreparedStatement preparedStatement = connection.prepareStatement("");
            preparedStatement.execute();
            connection.commit();
        } catch (SQLException e) {
            connection.rollback();
            e.printStackTrace();
        }
    }
}
