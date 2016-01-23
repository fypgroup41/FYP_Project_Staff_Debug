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

public class InsertServlet extends HttpServlet {

    private DB_Select db_select;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String dbUser = this.getServletContext().getInitParameter("dbUsername");
        String dbPassword = this.getServletContext().getInitParameter("dbPassword");
        String dbUrl = this.getServletContext().getInitParameter("dbUrl");
        db_select = new DB_Select(dbUrl, dbUser, dbPassword);
        PrintWriter out = response.getWriter();
        try {

            String table_type = null;
            table_type = request.getParameter("table_type");
            if (table_type.equals("activities")) {
                String item_id = null;
                String name = null;
                String districtNo = null;
                String quota = null;
                String targetAgeMax = null;
                String targetAgeMin = null;
                String deadline = null;
                String venue = null;
                String date = null;
                String tag = null;
                String activityType = null;
                String description = null;
                activityType = request.getParameter("activityType");
                item_id = request.getParameter("item_id");
                name = request.getParameter("name");
                districtNo = request.getParameter("districtNo");
                quota = request.getParameter("quota");
                targetAgeMax = request.getParameter("targetAgeMax");
                targetAgeMin = request.getParameter("targetAgeMin");
                deadline = request.getParameter("deadline");
                venue = request.getParameter("venue");
                date = request.getParameter("date");
                tag = request.getParameter("tag");
                description = request.getParameter("description");
                String validID = db_select.getAvailActivitiesID();
                String sql = " INSERT INTO `activities` (`activitiesID`, `name`, `districtNo`, `quota`, `remain`, `targetAgeMax`, `targetAgeMin`, `deadline`, `venue`, `date`, `tag`, `staffID`, `description`) VALUES ('" + validID + "', '" + name + "', '" + districtNo + "', '" + quota + "', '" + quota + "', '" + targetAgeMax + "', '" + targetAgeMin + "', '" + deadline + "', '" + venue + "', '" + date + "', '" + tag + "', '" + "10"
                        + "', '" + description + "')";
                boolean isSuccess = db_select.editRecordBySql(sql);
                sql = "INSERT INTO `atype_a` (`activityTypeID`, `activitiesID`) VALUES ('" + activityType + "', '" + validID + "')";
                isSuccess = db_select.editRecordBySql(sql);
                request.setAttribute("status", "Insert Success");
                request.setAttribute("link", "activities.jsp");
                RequestDispatcher rd
                        = request.getServletContext().getRequestDispatcher("/updateSucess.jsp");
                rd.forward(request, response);
            }
            if (table_type.equals("activityBudget")) {

                String itemName = null;
                String itemType = null;
                String cost = null;
                String number = null;
                String remark = null;
                String activities_id = null;
                activities_id = request.getParameter("activities_id");
                itemName = request.getParameter("itemName");
                itemType = request.getParameter("itemType");
                cost = request.getParameter("cost");
                number = request.getParameter("number");
                remark = request.getParameter("remark");
                String validID = db_select.getAvailBudgetItemID();
                String sql = "INSERT INTO `activitybudget` (`itemID`, `activitiesID`, `itemName`, `itemType`, `cost`, `number`, `remark`) VALUES('" + validID + "', '" + activities_id + "', '" + itemName + "', '" + itemType + "', '" + cost + "', '" + number + "', '" + remark + "')";
                boolean isSuccess = db_select.editRecordBySql(sql);
                request.setAttribute("status", "Insert Success");
                request.setAttribute("link", "activitiesBudget.jsp?table_type=activities&item_id="+activities_id);
                RequestDispatcher rd
                        = request.getServletContext().getRequestDispatcher("/updateSucess.jsp");
                rd.forward(request, response);
            }

        } catch (Exception ex) {

            out.println(ex.getMessage());
        }
    }

}
