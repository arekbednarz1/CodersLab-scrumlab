package pl.coderslab.web;

import org.mindrot.jbcrypt.BCrypt;
import pl.coderslab.dao.AdminsDao;
import pl.coderslab.model.Admins;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet(name = "LoginServlet", urlPatterns = {"/login"})
public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String email = request.getParameter("email");
        String password = request.getParameter("password");
        Boolean isNotNull = email != null && password != null;
        Boolean isNotZero = email.length() != 0 && password.length() != 0;

        if (isNotNull && isNotZero) {
            AdminsDao adminsDao = new AdminsDao();
            Admins admin = adminsDao.readAdminEmail(email);
            if (admin != null) {
                if (BCrypt.checkpw(password, admin.getPassword())) {
                    HttpSession httpSession = request.getSession();
                    httpSession.setAttribute("admin", admin);
                    Cookie username = new Cookie("username", admin.getFirstName());
                    username.setMaxAge(3600*24*31*12);
                    response.addCookie(username);
                    response.sendRedirect(request.getContextPath() + "/app");
                    return;
                }
                else request.setAttribute("wrong", "Błędne hasło");
            }
            else request.setAttribute("wrong", "Brak podanego użytkownika");
        }
        else request.setAttribute("wrong", "Wpisz poprawne dane");

        getServletContext().getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        getServletContext().getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
    }
}
