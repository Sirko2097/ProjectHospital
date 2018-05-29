package servlets;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/logout")
public class LogOutServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        session.removeAttribute("license");
        session.invalidate();
        resp.setHeader("Cache-Control","no-cache");
        resp.setHeader("Cache-Control","no-store");
        resp.setHeader("Pragma","no-cache");
        resp.setDateHeader ("Expires", 0);
        req.getRequestDispatcher("/login").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        HttpSession session = req.getSession();
//        session.removeAttribute("license");
//        resp.setHeader("Cache-Control","no-cache");
//        resp.setHeader("Cache-Control","no-store");
//        resp.setHeader("Pragma","no-cache");
//        resp.setDateHeader ("Expires", 0);
//
//        req.getRequestDispatcher("/login").forward(req, resp);
    }
}
