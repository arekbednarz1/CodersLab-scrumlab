package pl.coderslab.web;

import pl.coderslab.dao.DayNameDao;
import pl.coderslab.dao.PlanDao;
import pl.coderslab.dao.RecipeDao;
import pl.coderslab.dao.RecipePlanDao;
import pl.coderslab.model.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "AddRecipeToPlanServlet", urlPatterns = {"/app/recipe/plan/add"})
public class AddRecipeToPlanServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setCharacterEncoding("UTF-8");
        request.setCharacterEncoding("UTF-8");

        HttpSession session = request.getSession();
        Admins admin = (Admins) session.getAttribute("admin");

        int planId = Integer.parseInt(request.getParameter("choosePlan"));
        String mealName = request.getParameter("mealName").trim();
        int displayOrder = Integer.parseInt(request.getParameter("displayOrder"));
        int recipeId = Integer.parseInt(request.getParameter("recipe"));
        int dayId = Integer.parseInt(request.getParameter("day"));

        RecipePlan recipePlan = new RecipePlan();
        recipePlan.setRecipe_id(recipeId);
        recipePlan.setMeal_name(mealName);
        recipePlan.setDisplay_order(displayOrder);
        recipePlan.setDay_name_id(dayId);
        recipePlan.setPlan_id(planId);

        RecipePlanDao recipePlanDao = new RecipePlanDao();
        recipePlanDao.createRecipePlan(recipePlan);

        response.sendRedirect(request.getContextPath() + "/app?recipePlanCreated=true");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Admins admin = (Admins) session.getAttribute("admin");
        request.setAttribute("username", admin.getFirstName());
        int adminID = admin.getId();

        PlanDao planDao = new PlanDao();
        List<Plan> plansList = planDao.readAllAdminPlans(adminID);
//        List<String> planNamesList = new ArrayList<>();
//        for(Plan plan : plansList) {
//            planNamesList.add(plan.getName());
//        }
        request.setAttribute("plansList", plansList);

        RecipeDao recipeDao = new RecipeDao();
        List<Recipe> recipesList = recipeDao.readAllAdminRecipes(adminID);
//        List<String> recipeNamesList = new ArrayList<>();
//        for(Recipe recipe : recipesList) {
//            recipeNamesList.add(recipe.getName());
//        }
        request.setAttribute("recipesList", recipesList);

        DayNameDao dayNameDao = new DayNameDao();
        List<DayName> dayList = dayNameDao.readAllDays();
//        List<String> dayNameList = new ArrayList<>();
//        for(DayName day : dayList) {
//            dayNameList.add(day.getName());
//        }
        request.setAttribute("dayList", dayList);

        getServletContext().getRequestDispatcher("/WEB-INF/addRecipeToPlan.jsp").forward(request, response);

    }
}
