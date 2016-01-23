/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ict.servlet;

import db.handle.DB_Select;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class DeleteServlet extends HttpServlet {

    private DB_Select db_select;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String dbUser = this.getServletContext().getInitParameter("dbUsername");
        String dbPassword = this.getServletContext().getInitParameter("dbPassword");
        String dbUrl = this.getServletContext().getInitParameter("dbUrl");
        db_select = new DB_Select(dbUrl, dbUser, dbPassword);
        PrintWriter out = response.getWriter();
        String table_type = null;
        String item_id = null;
        String activities_id = null;
        String timetable_id = null;
        try {
            table_type = request.getParameter("table_type");

            if (table_type.equals("activities")) {
                activities_id = request.getParameter("activities_id");

                Boolean a = db_select.delRecordBySql("DELETE FROM atype_a WHERE activitiesID='" + activities_id + "'");
                Boolean b = db_select.delRecordBySql("DELETE FROM ACTIVITIES WHERE activitiesID='" + activities_id + "'");

                request.setAttribute("status", "Delete Success");
                request.setAttribute("link", "activities.jsp");

                RequestDispatcher rd
                        = request.getServletContext().getRequestDispatcher("/updateSucess.jsp");
                rd.forward(request, response);
            }

            if (table_type.equals("activityBudget")) {
                item_id = request.getParameter("item_id");
                activities_id = request.getParameter("activities_id");

                Boolean a = db_select.delRecordBySql("DELETE FROM activitybudget WHERE itemID='" + item_id + "'");
                request.setAttribute("status", "Delete Success");
                request.setAttribute("link", "activitiesBudget.jsp?table_type=activities&activities_id=" + activities_id);
                RequestDispatcher rd
                        = request.getServletContext().getRequestDispatcher("/updateSucess.jsp");
                rd.forward(request, response);
            }

            if (table_type.equals("activityTimetable")) {
                timetable_id = request.getParameter("timetable_id");
                activities_id = request.getParameter("activities_id");

                Boolean a = db_select.delRecordBySql("DELETE FROM activityTimetable WHERE activityTimeTableID='" + timetable_id + "'");
                request.setAttribute("status", "Delete Success");
                request.setAttribute("link", "activitiyTimetable.jsp?activities_id=" + activities_id);
                RequestDispatcher rd
                        = request.getServletContext().getRequestDispatcher("/updateSucess.jsp");
                rd.forward(request, response);
            }
        } catch (Exception ex) {
            out.println(ex.getStackTrace());
            out.println(ex.getMessage());
        }
    }

}
