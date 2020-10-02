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
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@WebServlet(name = "ServletEditPlan",urlPatterns = {"/app/plan/edit"})
public class ServletEditPlan extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        PlanDao planDao = new PlanDao();
        String name = request.getParameter("planName");
        String description = request.getParameter("planDescription");
        Admins admin = (Admins) session.getAttribute("admin");
        int id = Integer.parseInt(request.getParameter("idplan"));

        Plan plan = new Plan(name, description, admin);
        plan.setId(id);
        planDao.updatePlanAdmin(plan);

        response.sendRedirect("/app/plan/list");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("editId"));
        PlanDao planDao = new PlanDao();
        List<Plan> list = planDao.readAllPlans();
        Plan plan = new Plan();

        for (Plan p : list){
            if (p.getId()==id){
                plan = p;
                break;
            }
        }
        request.setAttribute("plan",plan);
        getServletContext().getRequestDispatcher("/WEB-INF/editPlan.jsp").forward(request,response);
    }
}
