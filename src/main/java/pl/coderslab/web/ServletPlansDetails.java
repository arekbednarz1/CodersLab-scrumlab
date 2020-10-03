package pl.coderslab.web;

import pl.coderslab.dao.PlanDao;
import pl.coderslab.dao.RecipePlanDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "ServletPlansDetails",urlPatterns = {"/app/plan/details"})
public class ServletPlansDetails extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String planIdS = request.getParameter("planId");
        int planId = Integer.parseInt(planIdS);
        PlanDao planDao = new PlanDao();

        request.setAttribute("plan", planDao.readPlan(planId));

        RecipePlanDao recipePlanDao = new RecipePlanDao();
        List<String[]> detailedPlan = recipePlanDao.getRecipePlanByPLanId(planId);
        request.setAttribute("detailedPlan", detailedPlan);
        request.setAttribute("planId", planId);

        getServletContext().getRequestDispatcher("/WEB-INF/planDetails.jsp").forward(request, response);
    }
}

