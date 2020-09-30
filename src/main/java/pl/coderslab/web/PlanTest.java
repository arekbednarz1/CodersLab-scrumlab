package pl.coderslab.web;

import pl.coderslab.dao.PlanDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "PlanTest", urlPatterns = {"/planTest"})
public class PlanTest extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PlanDao planDao = new PlanDao();
//        Plan plan = new Plan();
//        plan.setName("super plan");
//        plan.setDescription("super fajna dieta bogata w mięso");
//        planDao.createPlan(plan);
//        planDao.deletePlan(7);
//        planDao.deletePlan(8);
//        planDao.deletePlan(9);
//        planDao.deletePlan(10);
//        planDao.deletePlan(11);
//        List<Plan> planList = planDao.readAllPlans();
//        System.out.println(planList);

        /* test liczenia planów dodanych przez podanego użytkonika */
        PlanDao countPlanDao = new PlanDao();
        int numOfPlans = countPlanDao.countPlans(1);
        response.getWriter().append("Number of plans for admin_id=1: ")
                .append(String.valueOf(numOfPlans));

        numOfPlans = countPlanDao.countPlans(2443);
        response.getWriter().append("\nNumber of plans for admin_id=2443: ")
                .append(String.valueOf(numOfPlans));
    }
}
