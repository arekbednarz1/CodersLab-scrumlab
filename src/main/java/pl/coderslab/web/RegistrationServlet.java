package pl.coderslab.web;

import pl.coderslab.dao.AdminsDao;
import pl.coderslab.model.Admins;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Writer;
import java.util.Arrays;

@WebServlet(name = "RegistrationServlet",urlPatterns = {"/register"})
public class RegistrationServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        String surname = request.getParameter("surname");
        String email = request.getParameter("email");
        String[] passwords = request.getParameterValues("password");
        Boolean isNull = name != null && surname != null && email != null && passwords != null;
        Boolean isZero = name.length() != 0 && surname.length() != 0 && email.length() != 0 && passwords.length != 0;
        Boolean samePass = passwords[0].equals(passwords[1]);
        if (isNull && isZero && samePass) {
            Admins admins = new Admins();
            admins.setFirstName(name);
            admins.setLastName(surname);
            admins.setEmail(email);
            admins.setPassword(passwords[0]);
            AdminsDao adminsDao = new AdminsDao();
            adminsDao.createAdmin(admins);
            response.sendRedirect("/WEB-INF/login");

        } else {
            if (!samePass) {
                String wrw = "Hasła nie są takie same";
                request.setAttribute("wrong", wrw);
            }
            if (!isZero || !isNull) {
                String wr = "Uzupełnij wszystkie pola";
                request.setAttribute("all", wr);
            }
            getServletContext().getRequestDispatcher("/WEB-INF/register.jsp").forward(request, response);


        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        getServletContext().getRequestDispatcher("/WEB-INF/register.jsp").forward(request,response);
    }
}
