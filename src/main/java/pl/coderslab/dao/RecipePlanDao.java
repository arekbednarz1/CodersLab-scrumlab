package pl.coderslab.dao;

import pl.coderslab.exception.NotFoundException;
import pl.coderslab.model.RecipePlan;
import pl.coderslab.utils.DbUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RecipePlanDao {
    //zapytania
    private static final String CREATE_RECIPE_PLAN_QUERY = "INSERT INTO recipe_plan(recipe_id , meal_name, display_order, day_name_id, plan_id) VALUES (?,?,?,?,?)";
    private static final String READ_RECIPE_PLAN_QUERY = "SELECT * FROM recipe_plan WHERE id=?";
    private static final String READ_ALL_RECIPE_PLANS_QUERY = "SELECT * FROM recipe_plan";
    private static final String READ_ALL_ADMIN_RECIPE_PLANS_QUERY = "SELECT * FROM recipe_plan WHERE admin_id=?";
    private static final String UPDATE_RECIPE_PLAN_QUERY = "UPDATE recipe_plan SET name=?, description=? WHERE id=?";
    private static final String DELETE_RECIPE_PLAN_QUERY = "DELETE FROM recipe_plan WHERE id=?";
    private static final String GET_DETAILED_LAST_PLAN_QUERY =
            "SELECT day_name.name as day_name, meal_name, recipe.name as recipe_name, recipe_id "+
                    "FROM `recipe_plan` "+
                    "JOIN day_name on day_name.id=day_name_id "+
                    "JOIN recipe on recipe.id=recipe_id WHERE "+
                    "recipe_plan.plan_id =  (SELECT MAX(id) from plan WHERE admin_id = ?) "+
                    "ORDER by day_name.display_order, recipe_plan.display_order";
    private static final String GET_RECIPE_PLAN_BY_PLAN_ID =
            "SELECT day_name.name as day_name, meal_name, recipe.name as recipe_name, recipe_id, recipe_plan.id " +
                    "FROM recipe_plan " +
                    "JOIN day_name on day_name.id=day_name_id " +
                    "JOIN recipe on recipe.id=recipe_id " +
                    "WHERE plan_id = ? " +
                    "ORDER by day_name.display_order, recipe_plan.display_order";

    /*
     * Create RecipePlan
     */

    public RecipePlan createRecipePlan(RecipePlan recipePlan) {
        try (Connection conn = DbUtil.getConnection();
             PreparedStatement statement = conn.prepareStatement(CREATE_RECIPE_PLAN_QUERY, PreparedStatement.RETURN_GENERATED_KEYS)) {
            statement.setInt(1, recipePlan.getRecipe_id());
            statement.setString(2, recipePlan.getMeal_name());
            statement.setInt(3, recipePlan.getDisplay_order());
            statement.setInt(4, recipePlan.getDay_name_id());
            statement.setInt(5, recipePlan.getPlan_id());

            int result = statement.executeUpdate();

            if (result != 1) {
                throw new RuntimeException("Execute update returned " + result);
            }
            try (ResultSet resultSet = statement.getGeneratedKeys()) {
                if (resultSet.first()) {
                    recipePlan.setId(resultSet.getInt(1));
                    return recipePlan;
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
     *  Read RecipePlan
     */

    public RecipePlan readRecipePlan(int recipePlanId) {
        RecipePlan recipePlan = new RecipePlan();
        try (Connection conn = DbUtil.getConnection();
             PreparedStatement statement = conn.prepareStatement(READ_RECIPE_PLAN_QUERY)) {
            statement.setInt(1, recipePlanId);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    recipePlan.setId(resultSet.getInt("id"));
                    recipePlan.setRecipe_id(resultSet.getInt("recipe_id"));
                    recipePlan.setMeal_name(resultSet.getString("meal_name"));
                    recipePlan.setDisplay_order(resultSet.getInt("display_order"));
                    recipePlan.setDay_name_id(resultSet.getInt("day_name_id"));
                    recipePlan.setPlan_id(resultSet.getInt("plan_id"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return recipePlan;
    }

    /*
     * Get last detailed plan made by specific user
     * example:
     * poniedziałek,Śniadanie,Przepis 2,Opis przepisu 2
     * poniedziałek,Kolacja,Przepis 1,Opis przepisu 1
     */

    public List<String[]> getLastPlanDetailed(int adminID) {
        List<String[]> lastDetailedPlan = new ArrayList<>();
        try (Connection conn = DbUtil.getConnection();
             PreparedStatement statement = conn.prepareStatement(GET_DETAILED_LAST_PLAN_QUERY)) {
            statement.setInt(1, adminID);
            try(ResultSet rs = statement.executeQuery()) {
                while (rs.next()) {
                    String[] planRow = new String[4];
                    planRow[0] = rs.getString("day_name");
                    planRow[1] = rs.getString("meal_name");
                    planRow[2] = rs.getString("recipe_name");
                    planRow[3] = rs.getString("recipe_id");
                    lastDetailedPlan.add(planRow);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lastDetailedPlan;
    }


    public static List<String[]> getRecipePlanByPLanId(int planId) {
        List<String[]> detailedPlan = new ArrayList<>();
        try (Connection conn = DbUtil.getConnection();
             PreparedStatement statement = conn.prepareStatement(GET_RECIPE_PLAN_BY_PLAN_ID)) {
            statement.setInt(1, planId);
            try (ResultSet rs = statement.executeQuery()) {
                while (rs.next()) {
                    String[] planRow = new String[5];
                    planRow[0] = rs.getString("day_name");
                    planRow[1] = rs.getString("meal_name");
                    planRow[2] = rs.getString("recipe_name");
                    planRow[3] = rs.getString("recipe_id");
                    planRow[4] = rs.getString("id");
                    detailedPlan.add(planRow);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return detailedPlan;
    }

    /*
     * Delete RecipePlan
     */
    public void deleteRecipePlan(int recipePlanId) {
        try (Connection conn = DbUtil.getConnection();
             PreparedStatement statement = conn.prepareStatement(DELETE_RECIPE_PLAN_QUERY)) {
            statement.setInt(1, recipePlanId);
            statement.executeUpdate();

            boolean deleted = statement.execute();
            if (!deleted) {
                throw new NotFoundException("RecipePlan not found");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}