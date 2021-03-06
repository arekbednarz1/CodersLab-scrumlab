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

@WebServlet(name = "RecipeServlet", urlPatterns = "/app/recipe/list/")
public class RecipeServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");
        RecipeDao recipeDao = new RecipeDao();
        HttpSession session = request.getSession();
        Admins admin = (Admins) session.getAttribute("admin");
        request.setAttribute("recipes", recipeDao.readAllAdminRecipes(admin.getId()));
        getServletContext().getRequestDispatcher("/WEB-INF/AdminRecipe.jsp").forward(request, response);
    }
}
