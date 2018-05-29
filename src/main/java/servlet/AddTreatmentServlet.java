package servlet;

import dao.implementations.DAOFactoryImpl;
import model.Patient;

import javax.servlet.RequestDispatcher;
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



/**
 * In this servlet doctor can add treatment and operation.
 * */
@WebServlet("/addTreatment")
public class AddTreatmentServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("jsp/addTreatment.jsp");
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

    /**
     * This method insert treatment which doctor inputs.
     * */
    private void addTreatment(HttpServletRequest req, HttpServletResponse resp) throws SQLException, ServletException, IOException {
        DAOFactoryImpl daoFactory = DAOFactoryImpl.getInstance();
        Connection connection = daoFactory.getConnection();

        String medicines = req.getParameter("medicines");
        String procedures = req.getParameter("procedures");

        int operation = 0;
        if (req.getParameter("operation") != null){
            operation = Integer.parseInt(req.getParameter("operation"));
        }

//        System.out.println(session.getAttribute("cardNumberOfNewPatient").toString());
        int cardNumber = Integer.parseInt(req.getParameter("cardNumber"));
        System.out.println(cardNumber);
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
                    "WHERE card_number='" + cardNumber + "'");
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                Patient patient = new Patient(resultSet.getString(1), resultSet.getString(2),
                        resultSet.getString(3), resultSet.getString(4), resultSet.getDate(5),
                        resultSet.getInt(6));
                req.setAttribute("patient", patient);
            }
            preparedStatement = connection.prepareStatement("INSERT INTO TREATMENT (operation_necessity) " +
                    "VALUE (" + operation + ")");
            preparedStatement.execute();

            preparedStatement = connection.prepareStatement("SELECT COUNT(treatment_id) FROM TREATMENT");
            resultSet = preparedStatement.executeQuery();
            int treatmentCounter = 0;
            if (resultSet.next()) {
                treatmentCounter = resultSet.getInt(1);
            }
            preparedStatement = connection.prepareStatement("INSERT INTO CARD(card_number, treat_id) " +
                    "VALUES ('" + cardNumber +"', " + treatmentCounter + ")");
            preparedStatement.execute();

            preparedStatement = connection.prepareStatement("INSERT INTO TREATMENT_MEDICINE (treat_id, medicine_name) " +
                    "VALUES ("+ treatmentCounter+", '"+ medicines +"')");
            preparedStatement.execute();

            preparedStatement = connection.prepareStatement("INSERT INTO TREATMENT_PROCEDURE (treat_id, procedure_name) " +
                    "VALUES ("+ treatmentCounter+", '" + procedures+"')");
            preparedStatement.execute();

            connection.commit();
            doGet(req, resp);
        } catch (SQLException e) {
            connection.rollback();
            req.getRequestDispatcher("jsp/errorAddTreatment.jsp").forward(req, resp);
            e.printStackTrace();
        }
    }
}
