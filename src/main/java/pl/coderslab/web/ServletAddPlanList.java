package pl.coderslab.web;

import pl.coderslab.dao.PlanDao;
import pl.coderslab.model.Admins;
import pl.coderslab.model.Plan;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;


@WebServlet(name = "ServletAddPlanList",urlPatterns = {"/app/plan/add"})
public class ServletAddPlanList extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("nazwaPlanu");
        String decription = request.getParameter("opisPlanu");
        Boolean notNull = name != null && decription != null;
        Boolean notZero = name.length() != 0 && decription.length() != 0;
        if (notNull && notZero) {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date date = new Date();
            String dateString = formatter.format(date);

            PlanDao planDao = new PlanDao();
            HttpSession session = request.getSession();
            Admins admins = (Admins) session.getAttribute("admin");
            int id = admins.getId();
            Plan plan = new Plan();
            plan.setAdminId(id);
            plan.setName(name);
            plan.setDescription(decription);
            plan.setCreated(dateString);
            planDao.createPlan(plan);
            response.sendRedirect("/app/plan/list");
        }else{
            String wrw = "Wpisz poprawne dane";
            request.setAttribute("wrongP", wrw);
            getServletContext().getRequestDispatcher("/WEB-INF/addPlan.jsp").forward(request, response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        getServletContext().getRequestDispatcher("/WEB-INF/addPlan.jsp").forward(request, response);


    }
}
