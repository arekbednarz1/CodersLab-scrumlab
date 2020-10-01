package pl.coderslab.web;

import pl.coderslab.dao.AdminsDao;
import pl.coderslab.dao.PlanDao;
import pl.coderslab.dao.RecipeDao;
import pl.coderslab.model.Admins;
import pl.coderslab.model.Plan;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "DashboardServlet", urlPatterns = {"/app"})
public class DashboardServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //TEST
//        AdminsDao adminsDao = new AdminsDao();
//        Admins admin = adminsDao.readAdmin(1);

        HttpSession session = request.getSession();

        Admins admin = (Admins) session.getAttribute("admin");
        request.setAttribute("username", admin.getFirstName());

        // get number of plans user created and display it on dashbord
        PlanDao planDao = new PlanDao();
        int numOfPlans = planDao.countPlans(admin.getId());
        request.setAttribute("numOfPlans", numOfPlans);

        // get number of recipes user created and display it on dashbord
        RecipeDao recipeDao = new RecipeDao();
        int numOfRecipes = recipeDao.countRecipes(admin.getId());
        request.setAttribute("numOfRecipes", numOfRecipes);

        // get name of last plan user created and display it on dashbord
        Plan lastPlan = planDao.getLastPlan(admin.getId());
        request.setAttribute("lastPlan", lastPlan);

        // get detailed information about last plan user created and display it on dashbord
        List<String[]> lastDetailedPlan = planDao.getLastPlanDetailed(admin.getId());
        request.setAttribute("lastDetailedPlan", lastDetailedPlan);

        String recipePlanCreated = request.getParameter("recipePlanCreated");
        if(recipePlanCreated != null) request.setAttribute("recipePlanCreated", "Plan przepisów został zaktualizowany!");

        getServletContext().getRequestDispatcher("/WEB-INF/dashboard.jsp").forward(request, response);
    }
}
