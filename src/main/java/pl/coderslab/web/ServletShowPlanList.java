package pl.coderslab.web;

import pl.coderslab.dao.PlanDao;
import pl.coderslab.model.Admins;
import pl.coderslab.model.Plan;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "ServletShowPlanList",urlPatterns = {"/app/plan/list"})
public class ServletShowPlanList extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PlanDao planDao = new PlanDao();
        planDao.readAllPlans();
        HttpSession session = request.getSession();
        Admins admins = (Admins) session.getAttribute("admin");
        int id=admins.getId();
        List<Plan> list = planDao.readPlanAdmin(id);
        request.setAttribute("list",list);
        getServletContext().getRequestDispatcher("/WEB-INF/PlanLIst.jsp").forward(request, response);
    }
}
