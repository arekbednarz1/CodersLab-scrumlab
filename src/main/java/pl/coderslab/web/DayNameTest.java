package pl.coderslab.web;

import pl.coderslab.dao.DayNameDao;
import pl.coderslab.model.DayName;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "DayNameTest", urlPatterns = {"/daynameTest"})
public class DayNameTest extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        DayNameDao dayNameDao = new DayNameDao();
        List<DayName> days = dayNameDao.readAllDays();
        System.out.println(days);
    }
}
