/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ict.servlet;

import db.handle.DB_Select;
import java.io.*;
import java.util.Enumeration;
import javax.servlet.*;
import javax.servlet.http.*;

public class UpdateServlet extends HttpServlet {

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
                String activities_id = null;
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
                activities_id = request.getParameter("activities_id");
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
                String sql = " UPDATE ACTIVITIES SET name ='" + name + "'  ,districtNo='" + districtNo + "'  , quota='" + quota + "'  ,targetAgeMax=" + targetAgeMax + "  ,targetAgeMin=" + targetAgeMin + "  ,deadline='" + deadline + "'  ,venue='" + venue + "'  ,date='" + date + "'  ,tag='" + tag + "'  ,description='" + description + "'  WHERE activitiesID='" + activities_id + "'";
                boolean isSuccess = db_select.editRecordBySql(sql);
                sql = " UPDATE ATYPE_A SET activityTypeID='" + activityType + "' WHERE activitiesID='" + activities_id + "'";
                isSuccess = db_select.editRecordBySql(sql);
                request.setAttribute("status", "Update Success");
                request.setAttribute("link", "activitiesDetail.jsp?actID=" + activities_id);
                RequestDispatcher rd
                        = request.getServletContext().getRequestDispatcher("/updateSucess.jsp");
                rd.forward(request, response);

            }

            if (table_type.equals("activityBudget")) {
                String activities_id = null;
                String item_id = null;
                String itemName = null;
                String itemType = null;
                String cost = null;
                String number = null;
                String remark = null;
                item_id = request.getParameter("item_id");
                itemName = request.getParameter("itemName");
                itemType = request.getParameter("itemType");
                cost = request.getParameter("cost");
                number = request.getParameter("number");
                remark = request.getParameter("remark");
                activities_id = request.getParameter("activities_id");
                String sql = " UPDATE ACTIVITYBUDGET SET  itemName='" + itemName + "', itemType='" + itemType + "', cost=" + cost + ",number=" + number + " ,remark='" + remark + "' WHERE itemID='" + item_id + "'";
                boolean isSuccess = db_select.editRecordBySql(sql);
                request.setAttribute("status", "Update Success");
                request.setAttribute("link", "activitiesBudget.jsp?table_type=activities&activities_id=" + activities_id);
                RequestDispatcher rd = request.getServletContext().getRequestDispatcher("/updateSucess.jsp");
                rd.forward(request, response);
            }

            if (table_type.equals("activityTimetable")) {
                String activityTimeTableID = null;
                String activities_id = null;
                String detail = null;
                String startTime = null;
                String finish = null;

                activityTimeTableID = request.getParameter("activityTimeTableID");
                activities_id = request.getParameter("activities_id");
                detail = request.getParameter("detail");
                startTime = request.getParameter("startTime");
                finish = request.getParameter("finish");
                String sql = " UPDATE ACTIVITYTIMETABLE SET  detail='" + detail + "', startTime='" + startTime + "', finish='" + finish + "' where activityTimeTableID='" + activityTimeTableID + "'";
                boolean isSuccess = db_select.editRecordBySql(sql);
                request.setAttribute("status", sql);
                request.setAttribute("link", "activitiyTimetable.jsp?activities_id=" + activities_id);
                RequestDispatcher rd = request.getServletContext().getRequestDispatcher("/updateSucess.jsp");
                rd.forward(request, response);
            }
            if (table_type.equals("survey")) {

                Enumeration<String> parameterNames = request.getParameterNames();

                String question = null;
                String questionType = null;
                while (parameterNames.hasMoreElements()) {
                    String paramName = parameterNames.nextElement();
                    String val = "";
                    if (paramName.contains("question_")) {

                        String[] paramValues = request.getParameterValues(paramName);

                        for (int i = 0; i < paramValues.length; i++) {
                            String paramValue = paramValues[i];
                            question = paramValue;

                            String sql = "UPDATE surveyquestion set question='" + question + "' where sqID='" + paramName.substring(9, paramName.length()) + "'";
                            boolean isSuccess = db_select.editRecordBySql(sql);

                        }

                    }

                    if (paramName.contains("qTypeID_")) {
                        String[] paramValues = request.getParameterValues(paramName);

                        for (int i = 0; i < paramValues.length; i++) {
                            String paramValue = paramValues[i];
                            questionType = paramValue;
                            String sql = " UPDATE surveyquestion set qTypeID='" + paramValue + "' where sqID='" + paramName.substring(8, paramName.length()) + "'";
                            boolean isSuccess = db_select.editRecordBySql(sql);

                        }

                    }

                }
                request.setAttribute("status", "Update Success");
                request.setAttribute("link", "surveyDetail.jsp?activities_id=" + request.getParameter("activities_id"));
                RequestDispatcher rd = request.getServletContext().getRequestDispatcher("/updateSucess.jsp");
                rd.forward(request, response);
            }

        } catch (Exception ex) {

            out.println(ex.getMessage());
        }
    }

}
