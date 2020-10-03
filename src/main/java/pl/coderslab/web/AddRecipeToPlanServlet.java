package pl.coderslab.web;

import pl.coderslab.dao.*;
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

        String inputPlanId = request.getParameter("choosePlan");
        String mealName = request.getParameter("mealName").trim();
        String inputDisplayOrder = request.getParameter("displayOrder");
        String inputRecipeId = request.getParameter("recipe");
        String inputDayId = request.getParameter("day");

        Boolean notNull = inputPlanId != null && mealName != null && inputDisplayOrder != null && inputRecipeId != null && inputDayId != null;
        Boolean notZero = mealName.length() != 0 && inputDisplayOrder.length() != 0;

        if (!notNull || !notZero) {
            request.setAttribute("infoField", "Uzupełnij wszystkie pola");
            commonLogic(request);
            getServletContext().getRequestDispatcher(request.getContextPath()+"/WEB-INF/addRecipeToPlan.jsp").forward(request, response);
            return;
        }

        int planId = Integer.parseInt(inputPlanId);
        int displayOrder = Integer.parseInt(inputDisplayOrder);
        int recipeId = Integer.parseInt(inputRecipeId);
        int dayId = Integer.parseInt(inputDayId);

        RecipePlan recipePlan = new RecipePlan();
        recipePlan.setRecipe_id(recipeId);
        recipePlan.setMeal_name(mealName);
        recipePlan.setDisplay_order(displayOrder);
        recipePlan.setDay_name_id(dayId);
        recipePlan.setPlan_id(planId);

        RecipePlanDao recipePlanDao = new RecipePlanDao();
        recipePlanDao.createRecipePlan(recipePlan);

        request.setAttribute("infoField", "Przepis został dodany do planu!");
        commonLogic(request);
        getServletContext().getRequestDispatcher(request.getContextPath()+"/WEB-INF/addRecipeToPlan.jsp").forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        commonLogic(request);

        getServletContext().getRequestDispatcher(request.getContextPath()+"/WEB-INF/addRecipeToPlan.jsp").forward(request, response);

    }
    protected void commonLogic(HttpServletRequest request) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Admins admin = (Admins) session.getAttribute("admin");
        int adminID = admin.getId();

        PlanDao planDao = new PlanDao();
        List<Plan> plansList = planDao.readAllAdminPlans(adminID);
        request.setAttribute("plansList", plansList);

        RecipeDao recipeDao = new RecipeDao();
        List<Recipe> recipesList = recipeDao.readAllAdminRecipes(adminID);
        request.setAttribute("recipesList", recipesList);

        DayNameDao dayNameDao = new DayNameDao();
        List<DayName> dayList = dayNameDao.readAllDays();
        request.setAttribute("dayList", dayList);
    }
}
