package pl.coderslab.dao;

import pl.coderslab.exception.NotFoundException;
import pl.coderslab.model.RecipePlan;
import pl.coderslab.utils.DbUtil;
import java.sql.*;
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
     * Read all plans
     */
//
//    public List<Plan> readAllPlans() {
//        List<Plan> planList = new ArrayList<>();
//        try (Connection conn = DbUtil.getConnection();
//             PreparedStatement statement = conn.prepareStatement(READ_ALL_RECIPE_PLANS_QUERY);
//             ResultSet resultSet = statement.executeQuery()) {
//            while (resultSet.next()) {
//                Plan planToAdd = new Plan();
//                planToAdd.setId(resultSet.getInt("id"));
//                planToAdd.setName(resultSet.getString("name"));
//                planToAdd.setDescription(resultSet.getString("description"));
//                planToAdd.setCreated(resultSet.getDate("created"));
//                planList.add(planToAdd);
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return planList;
//    }
//
//
//    /*
//     * Read all plans
//     */
//
//    public List<Plan> readAllAdminPlans(int adminId) {
//        List<Plan> planList = new ArrayList<>();
//        try (Connection conn = DbUtil.getConnection();
//             PreparedStatement statement = conn.prepareStatement(READ_ALL_ADMIN_RECIPE_PLANS_QUERY)) {
//            statement.setInt(1, adminId);
//            try (ResultSet resultSet = statement.executeQuery()) {
//                while (resultSet.next()) {
//                    Plan planToAdd = new Plan();
//                    planToAdd.setId(resultSet.getInt("id"));
//                    planToAdd.setName(resultSet.getString("name"));
//                    planToAdd.setDescription(resultSet.getString("description"));
//                    planToAdd.setCreated(resultSet.getDate("created"));
//                    planList.add(planToAdd);
//                }
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return planList;
//    }

    /*
     * Update plan
     */
//    public void updateRecipePlan(RecipePlan recipePlan) {
//        try (Connection conn = DbUtil.getConnection();
//             PreparedStatement statement = conn.prepareStatement(UPDATE_RECIPE_PLAN_QUERY)) {
//            statement.setInt(4, plan.getId());
//            statement.setString(1, plan.getName());
//            statement.setString(2, plan.getDescription());
//
//            statement.executeUpdate();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }
//
//    /*
//     * Delete plan
//     */
//    public void deleteRecipePlan(int planId) {
//        try (Connection conn = DbUtil.getConnection();
//             PreparedStatement statement = conn.prepareStatement(DELETE_RECIPE_PLAN_QUERY)) {
//            statement.setInt(1, planId);
//            statement.executeUpdate();
//
//            boolean deleted = statement.execute();
//            if (!deleted) {
//                throw new NotFoundException("RecipePlan not found");
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
}