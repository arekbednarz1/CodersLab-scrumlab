package pl.coderslab.web;

import org.mindrot.jbcrypt.BCrypt;
import pl.coderslab.dao.AdminsDao;
import pl.coderslab.model.Admins;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
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
            Admins admins = adminsDao.readAdminEmail(email);
            if (admins != null) {
                if (BCrypt.checkpw(password, admins.getPassword())) {
                    HttpSession httpSession = request.getSession();
                    httpSession.setAttribute("admin", admins);
//                        response.sendRedirect("myRecipes");
                    response.sendRedirect("/");
                }
            }


        }else {
            String wrw = "Wpisz poprawne dane";
            request.setAttribute("wrong", wrw);
            getServletContext().getRequestDispatcher("/login.jsp").forward(request, response);
        }
    }




    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        getServletContext().getRequestDispatcher("/login.jsp").forward(request, response);
    }
}
