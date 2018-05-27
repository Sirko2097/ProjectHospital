package servlet;

import dao.implementations.DAODoctorImpl;
import dao.implementations.DAOFactoryImpl;
import dao.implementations.DAONurseImpl;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * This servlet is using only by doctors or nurses,
 * because only of their job and access level.
  */
@WebServlet("/login")
public class LogInServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("jsp/login.jsp");
        requestDispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();

        try {
            Connection connection = DAOFactoryImpl.getInstance().getConnection();
            DAODoctorImpl daoDoctor = DAOFactoryImpl.getInstance().getDAODoctorImpl(connection);
            DAONurseImpl daoNurse = DAOFactoryImpl.getInstance().getDAONurseImpl(connection);

            String license = req.getParameter("license");
            String password = req.getParameter("password");

            boolean nurseAvailability = daoNurse.nurseLogin(license, password);
            boolean doctorAvailability = daoDoctor.docLogin(license, password);

            String lastName;

            if (doctorAvailability) {
                lastName = daoDoctor.read(license).getLastName();
                req.setAttribute("lastName", lastName);
                session.setAttribute("license", license);

                RequestDispatcher requestDispatcher = req.getRequestDispatcher("jsp/mainMenu.jsp");
                requestDispatcher.forward(req, resp);
            } else if (nurseAvailability){
                lastName = daoNurse.read(license).getLastName();
                req.setAttribute("lastName", lastName);
                session.setAttribute("license", license);

                Cookie nurseCookie = new Cookie("Nurse", lastName);
                resp.addCookie(nurseCookie);

                RequestDispatcher requestDispatcher = req.getRequestDispatcher("jsp/mainMenu.jsp");
                requestDispatcher.forward(req, resp);
            } else {
                resp.sendRedirect("jsp/errorPage.jsp");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

}
