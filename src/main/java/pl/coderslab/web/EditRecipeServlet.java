package pl.coderslab.web;

import pl.coderslab.dao.RecipeDao;
import pl.coderslab.model.Admins;
import pl.coderslab.model.Recipe;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.time.LocalDateTime;

@WebServlet(name = "EditRecipeServlet", urlPatterns = {"/app/recipe/edit"})
public class EditRecipeServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");
        request.setCharacterEncoding("UTF-8");
        String name = request.getParameter("name");
        String description = request.getParameter("description");
        String  ingredients = request.getParameter("ingredients");
        String prepTime = request.getParameter("preparation_time");
        int prep = Integer.parseInt(prepTime);
        String preparation = request.getParameter("preparation");


        RecipeDao recipeDao = new RecipeDao();
        String idValue = request.getParameter("id");
        int id = Integer.parseInt(idValue);
        Recipe recipe = recipeDao.readRecipe(id);
        if (name.length() == 0) {
                recipe.getName();
        }else {
            recipe.setName(name);
        }
        if (description.length() == 0) {
            recipe.getDescription();
        }else {
            recipe.setDescription(description);
        }
        if (ingredients.length() == 0) {
            recipe.getIngredients();
        }else {
            recipe.setIngredients(ingredients);
        }
        if (prep == 0) {
            recipe.getPreparation_time();
        }else {
            recipe.setPreparation_time(prep);
        }
        if (preparation.length() == 0) {
            recipe.getPreparation();
        }else {
            recipe.setPreparation(preparation);
        }
        recipe.setUpdated(String.valueOf(LocalDateTime.now()));

        recipeDao.updateRecipe(recipe);
        response.sendRedirect(request.getContextPath() + "/app/recipe/list/");

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");
        request.setCharacterEncoding("UTF-8");

        HttpSession session = request.getSession();
        Admins admin = (Admins) session.getAttribute("admin");
        request.setAttribute("username", admin.getFirstName());

        RecipeDao recipeDao = new RecipeDao();
        int id = Integer.parseInt(request.getParameter("id"));
        request.setAttribute("showDetails", recipeDao.readRecipe(id));
        getServletContext().getRequestDispatcher("/WEB-INF/editRecipe.jsp").forward(request,response);
    }
}
