package pl.coderslab.web;

import pl.coderslab.dao.AdminsDao;
import pl.coderslab.model.Admins;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "EditAdminPasswordServlet", urlPatterns = {"/app/editPassword"})
public class EditAdminPasswordServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");
        request.setCharacterEncoding("UTF-8");
        String password1 = request.getParameter("password");
        String password2 = request.getParameter("repassword");
        Boolean samePass = password1.equals(password2);
        if (!samePass) {
            String wrw = "Hasła nie są takie same";
            request.setAttribute("wrong", wrw);
        }
        AdminsDao adminsDao = new AdminsDao();
        String idValue = request.getParameter("id");
        int id = Integer.parseInt(idValue);
        Admins admins = adminsDao.readAdmin(id);
        if (password1.length() != 0 && password2.length() != 0) {
            admins.setPassword(password1);
        }
            adminsDao.updateAdminPassword(admins);
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
        getServletContext().getRequestDispatcher("/WEB-INF/editAdminPassword.jsp").forward(request,response);
    }
}
