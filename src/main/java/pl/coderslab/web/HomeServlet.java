package pl.coderslab.web;

import pl.coderslab.dao.AdminsDao;
import pl.coderslab.dao.BookDao;
import pl.coderslab.dao.DayNameDao;
import pl.coderslab.dao.PlanDao;
import pl.coderslab.model.Admins;
import pl.coderslab.model.Book;
import pl.coderslab.model.DayName;
import pl.coderslab.model.Plan;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Do not change servlet address !!!
 */
@WebServlet("")
public class HomeServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Admins admin = (Admins) session.getAttribute("admin");
        if(admin == null) {
            request.setAttribute("logged", false);
        }
        else {
            request.setAttribute("logged", true);
        }
        getServletContext().getRequestDispatcher("/WEB-INF/home.jsp").forward(request, response);

    }
}
