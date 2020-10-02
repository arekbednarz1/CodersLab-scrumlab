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
import java.time.LocalDate;
import java.time.LocalDateTime;

@WebServlet(name = "AddRecipeServlet", urlPatterns = {"/app/recipe/add"})
public class AddRecipeServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");
        request.setCharacterEncoding("UTF-8");

        HttpSession session = request.getSession();

        Admins admin = (Admins) session.getAttribute("admin");
        String name = request.getParameter("name").trim();
        String description = request.getParameter("description").trim();
        String prepTime = request.getParameter("prepTime").trim();
        String preparation = request.getParameter("prep").trim();
        String ingredients = request.getParameter("ingredients").trim();
        Recipe recipe = new Recipe();
        recipe.setName(name);
        recipe.setDescription(description);
        recipe.setPreparation_time(Integer.parseInt(prepTime));
        recipe.setPreparation(preparation);
        recipe.setIngredients(ingredients);
        recipe.setAdmin_id(String.valueOf(admin.getId()));
        recipe.setCreated(String.valueOf(LocalDateTime.now()));
        recipe.setUpdated(String.valueOf(LocalDateTime.now()));
        RecipeDao recipeDao = new RecipeDao();
        recipeDao.createRecipe(recipe);
        response.sendRedirect(request.getContextPath() + "/app/recipe/list/");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");
        request.setCharacterEncoding("UTF-8");

        getServletContext().getRequestDispatcher("/WEB-INF/addRecipe.jsp").forward(request, response);
    }
}
