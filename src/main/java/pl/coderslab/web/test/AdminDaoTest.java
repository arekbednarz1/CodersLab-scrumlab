package pl.coderslab.web.test;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "AdminDaoTest", urlPatterns = {"/adminTest"})
public class AdminDaoTest extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        AdminsDao adminsDao = new AdminsDao();
//        Admins admin = adminsDao.readAdmin(2);
//        admin.setFirstName("Miłosz");
//        admin.setLastName("Małysz");
//        admin.setEmail("miłek@onet.pl");
//        admin.setPassword("4321");
////        adminsDao.createAdmin(admin);
////        adminsDao.deleteAdmin(3);
//        adminsDao.updateAdminData(admin);
//        List<Admins> adminsList = adminsDao.readAllAdmins();
//        System.out.println(adminsList);
    }
}
