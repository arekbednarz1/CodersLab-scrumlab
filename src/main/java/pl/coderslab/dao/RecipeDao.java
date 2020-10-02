package pl.coderslab.dao;

import pl.coderslab.exception.NotFoundException;
import pl.coderslab.model.Plan;
import pl.coderslab.model.Recipe;
import pl.coderslab.utils.DbUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

    public class RecipeDao {
    private static final String CREATE_RECIPE_QUERY = "INSERT INTO recipe(name, ingredients, description, created, updated, preparation_time, preparation, admin_id) VALUES (?,?,?,?,?,?,?,?)";
    private static final String READ_RECIPE_QUERY = "SELECT * FROM recipe WHERE id=?";
    private static final String READ_ALL_RECIPES_QUERY = "SELECT * FROM recipe";
    private static final String UPDATE_RECIPE_QUERY = "UPDATE recipe SET name=?, ingredients=?, description=?, created=?, updated=?, preparation_time=?, preparation=? WHERE id=?";
    private static final String DELETE_RECIPE_QUERY = "DELETE FROM recipe WHERE id=?";
    private static final String COUNT_RECIPES_QUERY = "SELECT COUNT(*) FROM recipe WHERE admin_id=?";
    private static final String READ_ALL_RECIPES_SORTED_QUERY = "SELECT * FROM recipe WHERE admin_id=? ORDER BY created desc, updated desc";
    /*
     * Create recipe
     */

    public Recipe createRecipe(Recipe recipe) {
        try (Connection conn = DbUtil.getConnection();
             PreparedStatement statement = conn.prepareStatement(CREATE_RECIPE_QUERY, Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, recipe.getName());
            statement.setString(2, recipe.getIngredients());
            statement.setString(3, recipe.getDescription());
            statement.setString(4, recipe.getCreated());
            statement.setString(5, recipe.getUpdated());
            statement.setInt(6, recipe.getPreparation_time());
            statement.setString(7, recipe.getPreparation());
            statement.setString(8, recipe.getAdmin_id());
            int result = statement.executeUpdate();

            if (result != 1) {
                throw new RuntimeException("Execute update returned " + result);
            }
            try (ResultSet resultSet = statement.getGeneratedKeys()) {
                if (resultSet.first()) {
                    recipe.setId(resultSet.getInt(1));
                    return recipe;
                } else {
                    throw new RuntimeException("Generated key was not found");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /*
     *  Read recipe
     */

    public Recipe readRecipe(int recipeId) {
        Recipe recipe = new Recipe();
        try (Connection conn = DbUtil.getConnection();
             PreparedStatement statement = conn.prepareStatement(READ_RECIPE_QUERY)) {
            statement.setInt(1, recipeId);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    recipe.setId(resultSet.getInt("id"));
                    recipe.setName(resultSet.getString("name"));
                    recipe.setIngredients(resultSet.getString("ingredients"));
                    recipe.setDescription(resultSet.getString("description"));
                    recipe.setCreated(resultSet.getString("created"));
                    recipe.setUpdated(resultSet.getString("updated"));
                    recipe.setPreparation_time(resultSet.getInt("preparation_time"));
                    recipe.setPreparation(resultSet.getString("preparation"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return recipe;
    }

    /*
     * Read all recipes
     */

    public List<Recipe> readAllRecipes() {
        List<Recipe> recipesList = new ArrayList<>();
        try (Connection conn = DbUtil.getConnection();
             PreparedStatement statement = conn.prepareStatement(READ_ALL_RECIPES_QUERY);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                Recipe recipeToAdd = new Recipe();
                recipeToAdd.setId(resultSet.getInt("id"));
                recipeToAdd.setName(resultSet.getString("name"));
                recipeToAdd.setIngredients(resultSet.getString("ingredients"));
                recipeToAdd.setDescription(resultSet.getString("description"));
                recipeToAdd.setCreated(resultSet.getString("created"));
                recipeToAdd.setUpdated(resultSet.getString("updated"));
                recipeToAdd.setPreparation_time(resultSet.getInt("preparation_time"));
                recipeToAdd.setPreparation(resultSet.getString("preparation"));
               recipesList.add(recipeToAdd);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return recipesList;
    }

    /*
     * Update recipe
     */
    public void updateRecipe(Recipe recipe) {
        try (Connection conn = DbUtil.getConnection();
             PreparedStatement statement = conn.prepareStatement(UPDATE_RECIPE_QUERY)) {
            statement.setInt(8, recipe.getId());
            statement.setString(1, recipe.getName());
            statement.setString(2, recipe.getIngredients());
            statement.setString(3, recipe.getDescription());
            statement.setString(4, recipe.getCreated());
            statement.setString(5, recipe.getUpdated());
            statement.setInt(6, recipe.getPreparation_time());
            statement.setString(7, recipe.getPreparation());

            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /*
     * Delete recipe
     */
    public void deleteRecipe(int recipeId) {
        try (Connection conn = DbUtil.getConnection();
             PreparedStatement statement = conn.prepareStatement(DELETE_RECIPE_QUERY)) {
            statement.setInt(1, recipeId);
            statement.executeUpdate();

            boolean deleted = statement.execute();
            if (!deleted) {
                throw new NotFoundException("Recipe not found");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /*
     * Count recipes made by specific user
     */

    public int countRecipes(int adminID) {
        try (Connection conn = DbUtil.getConnection();
             PreparedStatement statement = conn.prepareStatement(COUNT_RECIPES_QUERY)) {
            statement.setInt(1, adminID);
            try(ResultSet rs = statement.executeQuery()) {
                if(rs.next()) {
                    int numOfRecipes = rs.getInt(1);
                    return numOfRecipes;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1;
    }
        public List<Recipe> readAllAdminRecipes(int adminId) {
            List<Recipe> recipesList = new ArrayList<>();
            try (Connection conn = DbUtil.getConnection();
                 PreparedStatement statement = conn.prepareStatement(READ_ALL_RECIPES_SORTED_QUERY);){
                 statement.setInt(1, adminId);
                 try(ResultSet resultSet = statement.executeQuery()) {
                     while (resultSet.next()) {
                         Recipe recipeToAdd = new Recipe();
                         recipeToAdd.setId(resultSet.getInt("id"));
                         recipeToAdd.setName(resultSet.getString("name"));
                         recipeToAdd.setIngredients(resultSet.getString("ingredients"));
                         recipeToAdd.setDescription(resultSet.getString("description"));
                         recipeToAdd.setCreated(resultSet.getString("created"));
                         recipeToAdd.setUpdated(resultSet.getString("updated"));
                         recipeToAdd.setPreparation_time(resultSet.getInt("preparation_time"));
                         recipeToAdd.setPreparation(resultSet.getString("preparation"));
                         recipesList.add(recipeToAdd);
                     }
                 }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return recipesList;
        }
}



