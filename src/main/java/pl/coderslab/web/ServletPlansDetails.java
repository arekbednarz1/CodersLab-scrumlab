package pl.coderslab.web;

import pl.coderslab.dao.PlanDao;
import pl.coderslab.model.Admins;
import pl.coderslab.model.Plan;
import pl.coderslab.model.RecipePlanObj;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

@WebServlet(name = "ServletPlansDetails",urlPatterns = {"/app/plan/details"})
public class ServletPlansDetails extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String planIdS = request.getParameter("planId");
        int planId = Integer.parseInt(planIdS);
        PlanDao planDao = new PlanDao();

        request.setAttribute("plan", planDao.readPlan(planId));

        List <RecipePlanObj> list = PlanDao.getRecipePlanByPLanId(planId);

        List<RecipePlanObj> pon = new ArrayList<>();
        List<RecipePlanObj> wt = new ArrayList<>();
        List<RecipePlanObj> sr = new ArrayList<>();
        List<RecipePlanObj> czw = new ArrayList<>();
        List<RecipePlanObj> pt = new ArrayList<>();
        List<RecipePlanObj> sob = new ArrayList<>();
        List<RecipePlanObj> ndz = new ArrayList<>();

        if (list != null) {
            ListIterator<RecipePlanObj> listIterator = list.listIterator();

            while (listIterator.hasNext()) {
                RecipePlanObj rec = listIterator.next();
                if ("poniedziałek".equals(rec.getDayName())) {
                    pon.add(rec);
                } else if ("wtorek".equals(rec.getDayName())) {
                    wt.add(rec);
                } else if ("środa".equals(rec.getDayName())) {
                    sr.add(rec);
                } else if ("czwartek".equals(rec.getDayName())) {
                    czw.add(rec);
                } else if ("piątek".equals(rec.getDayName())) {
                    pt.add(rec);
                } else if ("sobota".equals(rec.getDayName())) {
                    sob.add(rec);
                } else if ("niedziela".equals(rec.getDayName())) {
                    ndz.add(rec);
                }
            }
            request.setAttribute("pon", pon);
            request.setAttribute("wt", wt);
            request.setAttribute("sr", sr);
            request.setAttribute("czw", czw);
            request.setAttribute("pt", pt);
            request.setAttribute("sob", sob);
            request.setAttribute("ndz", ndz);
        }
        getServletContext().getRequestDispatcher("/WEB-INF/planDetails.jsp").forward(request, response);
    }

    }

