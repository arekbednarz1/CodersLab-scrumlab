package pl.coderslab.web;

import pl.coderslab.dao.AdminsDao;
import pl.coderslab.dao.RecipeDao;
import pl.coderslab.model.Admins;
import pl.coderslab.model.Recipe;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.time.LocalDateTime;

@WebServlet(name = "EditAdminServlet", urlPatterns = {"/app/editAdmin"})
public class EditAdminServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");
        request.setCharacterEncoding("UTF-8");
        String name = request.getParameter("name");
        String lastname = request.getParameter("lastname");
        String  email = request.getParameter("email");
        String password = request.getParameter("password");
        AdminsDao adminsDao = new AdminsDao();
        String idValue = request.getParameter("id");
        int id = Integer.parseInt(idValue);
        Admins admins = adminsDao.readAdmin(id);
        if (name.length() == 0) {
            admins.getFirstName();
        }else {
            admins.setFirstName(name);
        }
        if (lastname.length() == 0) {
            admins.getLastName();
        }else {
            admins.setLastName(lastname);
        }
        if (email.length() == 0) {
            admins.getEmail();
        }else {
            admins.setEmail(email);
        }
        if(password.length() != 0){
            admins.getPassword();
        }else {
            admins.setPassword(password);
        }

        adminsDao.updateAdminData(admins);
        response.sendRedirect(request.getContextPath() + "/app");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");
        request.setCharacterEncoding("UTF-8");
        AdminsDao adminsDao = new AdminsDao();
        HttpSession session = request.getSession();
        Admins admin = (Admins) session.getAttribute("admin");
        request.setAttribute("username", admin.getFirstName());
        request.setAttribute("adminData", adminsDao.readAdmin(admin.getId()));
        getServletContext().getRequestDispatcher("/WEB-INF/editAdminData.jsp").forward(request,response);
    }
}
