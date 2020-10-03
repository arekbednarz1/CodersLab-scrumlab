package pl.coderslab.web;

import pl.coderslab.dao.RecipeDao;
import pl.coderslab.model.Admins;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "AllRecipesServlet", urlPatterns = {"/allRecipes"})
public class AllRecipesServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Admins admin = (Admins) session.getAttribute("admin");
        if(admin == null) {
            request.setAttribute("logged", false);
        }
        else {
            request.setAttribute("logged", true);
        }

        RecipeDao recipeDao = new RecipeDao();
        request.setAttribute("recipe",recipeDao.readAllRecipes());
        getServletContext().getRequestDispatcher("/WEB-INF/allRecipes.jsp").forward(request,response);
    }
}
