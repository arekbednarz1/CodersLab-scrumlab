package pl.coderslab.web;

import pl.coderslab.dao.RecipeDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.ResultSet;

@WebServlet(name = "AllRecipesDetailsServlet", urlPatterns = {"/allRecipes/details"})
public class AllRecipesDetailsServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");
        String idValue = request.getParameter("id");
        RecipeDao recipeDao = new RecipeDao();
        int id = Integer.parseInt(idValue);
        request.setAttribute("showDetails", recipeDao.readRecipe(id));
        getServletContext().getRequestDispatcher("/WEB-INF/allRecipesDetails.jsp").forward(request, response);
    }
}
