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

@WebServlet(name = "RecipeDetailServlet", urlPatterns = {"/app/recipe/details"})
public class RecipeDetailServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");
        HttpSession session = request.getSession();
        Admins admin = (Admins) session.getAttribute("admin");
        request.setAttribute("username", admin.getFirstName());
        RecipeDao recipeDao = new RecipeDao();
        String idValue = request.getParameter("id");
        int id = Integer.parseInt(idValue);
        request.setAttribute("showDetails", recipeDao.readRecipe(id));
        getServletContext().getRequestDispatcher("/WEB-INF/recipeDetails.jsp").forward(request, response);
    }
}
