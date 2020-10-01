package pl.coderslab.web;

import pl.coderslab.dao.PlanDao;
import pl.coderslab.dao.RecipeDao;
import pl.coderslab.model.Recipe;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "RecipeDaoTest", urlPatterns = {"/recipeDaoTest"})
public class RecipeDaoTest extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        Recipe test1 = new Recipe("zupa","pomidory","2019-12-20","zupa z naturalnych pomidorow","2020-02-02","32","opis","1");
//        RecipeDao recipeDao = new RecipeDao();
//        Recipe testcreated = recipeDao.createRecipe(test1);
//
//        System.out.println(testcreated);
//        RecipeDao recipeDao = new RecipeDao();
//        recipeDao.deleteRecipe(10);

//        RecipeDao recipeDao = new RecipeDao();
//        Recipe t2 = recipeDao.readRecipe(11);
//        t2.setName("barszcz");
//        recipeDao.updateRecipe(t2);


        /* test liczenia przepisów dodanych przez podanego użytkonika */
        RecipeDao countRecipeDao = new RecipeDao();
        int numOfRecipes = countRecipeDao.countRecipes(1);
        response.getWriter().append("Number of recipes for admin_id=1: ")
                .append(String.valueOf(numOfRecipes));

        numOfRecipes = countRecipeDao.countRecipes(2443);
        response.getWriter().append("\nNumber of recipes for admin_id=2443: ")
                .append(String.valueOf(numOfRecipes));



    }
}