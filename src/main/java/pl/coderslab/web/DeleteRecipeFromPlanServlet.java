package pl.coderslab.web;

import pl.coderslab.dao.RecipeDao;
import pl.coderslab.dao.RecipePlanDao;
import pl.coderslab.model.Recipe;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "DeleteRecipeFromPlanServlet", urlPatterns = {"/app/recipe/plan/delete"})
public class DeleteRecipeFromPlanServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String recipePlanIdValue = request.getParameter("recipePlanId");
        String planIdValue = request.getParameter("planId");
        int recipePlanId = Integer.parseInt(recipePlanIdValue);
        int planId = Integer.parseInt(planIdValue);
        RecipePlanDao recipePlanDao = new RecipePlanDao();
        recipePlanDao.deleteRecipePlan(recipePlanId);

        response.sendRedirect(request.getContextPath() + "/app/plan/details?planId="+planId);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String recipePlanIdValue = request.getParameter("id");
        String planIdValue = request.getParameter("planId");

        request.setAttribute("recipePlanId",recipePlanIdValue);
        request.setAttribute("planId",planIdValue);
        getServletContext().getRequestDispatcher("/WEB-INF/deleteRecipeFromPlan.jsp").forward(request,response);
    }
}

