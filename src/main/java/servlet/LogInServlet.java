package servlet;

import dao.implementations.DAODoctorImpl;
import dao.implementations.DAOFactoryImpl;
import dao.implementations.DAONurseImpl;

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

            boolean nurseAvailability = daoNurse.nurseLogin(req.getParameter("license"), req.getParameter("password"));
            boolean doctorAvailability = daoDoctor.docLogin(req.getParameter("license"), req.getParameter("password"));

            if (doctorAvailability) {
                req.setAttribute("lastName", daoDoctor.read(req.getParameter("license")).getLastName());
                session.setAttribute("license", req.getParameter("license"));

                RequestDispatcher requestDispatcher = req.getRequestDispatcher("jsp/success.jsp");
                requestDispatcher.include(req, resp);

            } else if (nurseAvailability){
                resp.sendRedirect("jsp/success.jsp");
                req.setAttribute("lastName", daoNurse.read(req.getParameter("license")).getLastName());
            } else {
                resp.sendRedirect("jsp/errorPage.jsp");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

}
