package pl.coderslab.dao;

import pl.coderslab.exception.NotFoundException;
import pl.coderslab.model.Plan;
import pl.coderslab.model.RecipePlanObj;
import pl.coderslab.utils.DbUtil;

import java.sql.*;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class PlanDao {
    //zapytania
    private static final String CREATE_PLAN_QUERY = "INSERT INTO plan(name , description, created, admin_id) VALUES (?,?,?,?)";
    private static final String READ_PLAN_QUERY = "SELECT * FROM plan WHERE id=?";
    private static final String READ_ALL_PLANS_QUERY = "SELECT * FROM plan";
    private static final String READ_ALL_ADMIN_PLANS_QUERY = "SELECT * FROM plan WHERE admin_id=?";
    private static final String UPDATE_PLAN_QUERY = "UPDATE plan SET name=?, description=? WHERE id=?";
    private static final String DELETE_PLAN_QUERY = "DELETE FROM plan WHERE id=?";
    private static final String COUNT_PLANS_QUERY = "SELECT COUNT(*) FROM plan WHERE admin_id=?";
    private static final String GET_LAST_PLAN_QUERY = "SELECT * FROM plan WHERE admin_id=? ORDER BY created DESC LIMIT 1";
    private static final String READ_ALL_PLAN_ADMIN_QUERY = "SELECT * FROM plan WHERE admin_id=?";
    private static final String UPDATE_PLAN_QUERY_ADMIN = "UPDATE plan SET name = ?, description = ?, admin_id = ? WHERE id = ?;";

    /*
     * Create plan
     */

    public Plan createPlan(Plan plan) {
        try (Connection conn = DbUtil.getConnection();
             PreparedStatement statement = conn.prepareStatement(CREATE_PLAN_QUERY, PreparedStatement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, plan.getName());
            statement.setString(2, plan.getDescription());
            statement.setString(3, plan.getCreated());
            statement.setInt(4, plan.getAdminId());

            int result = statement.executeUpdate();

            if (result != 1) {
                throw new RuntimeException("Execute update returned " + result);
            }
            try (ResultSet resultSet = statement.getGeneratedKeys()) {
                if (resultSet.first()) {
                    plan.setId(resultSet.getInt(1));
                    return plan;
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
     *  Read Plan
     */

    public Plan readPlan(int planId) {
        Plan plan = new Plan();
        try (Connection conn = DbUtil.getConnection();
             PreparedStatement statement = conn.prepareStatement(READ_PLAN_QUERY)) {
            statement.setInt(1, planId);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    plan.setId(resultSet.getInt("id"));
                    plan.setName(resultSet.getString("name"));
                    plan.setDescription(resultSet.getString("description"));
                    plan.setCreated(resultSet.getString("created"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return plan;
    }

    public List<Plan> readPlanAdmin(int adminId) {
        List<Plan> list = new ArrayList<>();
        try (Connection conn = DbUtil.getConnection();
             PreparedStatement statement = conn.prepareStatement(READ_ALL_PLAN_ADMIN_QUERY)) {
            statement.setInt(1, adminId);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Plan plan = new Plan();
                plan.setId(resultSet.getInt("id"));
                plan.setName(resultSet.getString("name"));
                plan.setDescription(resultSet.getString("description"));
                plan.setCreated(resultSet.getString("created"));
                list.add(plan);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    /*
     * Read all plans
     */

    public List<Plan> readAllPlans() {
        List<Plan> planList = new ArrayList<>();
        try (Connection conn = DbUtil.getConnection();
             PreparedStatement statement = conn.prepareStatement(READ_ALL_PLANS_QUERY);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                Plan planToAdd = new Plan();
                planToAdd.setId(resultSet.getInt("id"));
                planToAdd.setName(resultSet.getString("name"));
                planToAdd.setDescription(resultSet.getString("description"));
                planToAdd.setCreated(resultSet.getString("created"));
                planList.add(planToAdd);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return planList;
    }


    /*
     * Read all plans
     */

    public List<Plan> readAllAdminPlans(int adminId) {
        List<Plan> planList = new ArrayList<>();
        try (Connection conn = DbUtil.getConnection();
             PreparedStatement statement = conn.prepareStatement(READ_ALL_ADMIN_PLANS_QUERY)) {
            statement.setInt(1, adminId);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    Plan planToAdd = new Plan();
                    planToAdd.setId(resultSet.getInt("id"));
                    planToAdd.setName(resultSet.getString("name"));
                    planToAdd.setDescription(resultSet.getString("description"));
                    planToAdd.setCreated(resultSet.getString("created"));
                    planList.add(planToAdd);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return planList;
    }

    /*
     * Update plan
     */
    public void updatePlan(Plan plan) {
        try (Connection conn = DbUtil.getConnection();
             PreparedStatement statement = conn.prepareStatement(UPDATE_PLAN_QUERY)) {
            statement.setInt(4, plan.getId());
            statement.setString(1, plan.getName());
            statement.setString(2, plan.getDescription());

            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void updatePlanAdmin(Plan plan) {
        try (Connection conn = DbUtil.getConnection();
             PreparedStatement statement = conn.prepareStatement(UPDATE_PLAN_QUERY_ADMIN)) {
            statement.setInt(4, plan.getId());
            statement.setString(1, plan.getName());
            statement.setString(2, plan.getDescription());
            statement.setInt(3, plan.getAdmin().getId());

            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /*
     * Delete plan
     */
    public void deletePlan(int planId) {
        try (Connection conn = DbUtil.getConnection();
             PreparedStatement statement = conn.prepareStatement(DELETE_PLAN_QUERY)) {
            statement.setInt(1, planId);
            statement.executeUpdate();

            boolean deleted = statement.execute();
            if (!deleted) {
                throw new NotFoundException("Plan not found");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /*
     * Count plans made by specific user
     */

    public int countPlans(int adminID) {
        try (Connection conn = DbUtil.getConnection();
             PreparedStatement statement = conn.prepareStatement(COUNT_PLANS_QUERY)) {
            statement.setInt(1, adminID);
            try (ResultSet rs = statement.executeQuery()) {
                if (rs.next()) {
                    int numOfPlans = rs.getInt(1);
                    return numOfPlans;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1;
    }

    /*
     * Get last plan made by specific user
     */

    public Plan getLastPlan(int adminID) {
        Plan plan = new Plan();
        try (Connection conn = DbUtil.getConnection();
             PreparedStatement statement = conn.prepareStatement(GET_LAST_PLAN_QUERY)) {
            statement.setInt(1, adminID);
            try(ResultSet rs = statement.executeQuery()) {
                if (rs.next()) {
                    plan.setId(rs.getInt("id"));
                    plan.setName(rs.getString("name"));
                    plan.setDescription(rs.getString("description"));
                    plan.setCreated(rs.getString("created"));
                    plan.setAdminId(rs.getInt("admin_id"));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return plan;
    }

}
