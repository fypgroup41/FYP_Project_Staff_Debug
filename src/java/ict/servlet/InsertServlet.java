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

            String table_type = "activities";
            table_type = request.getParameter("table_type");

            switch (table_type) {
                case "activities": {
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

                    if (name != null && districtNo != null && quota != null && targetAgeMax != null && targetAgeMin != null && deadline != null && venue != null && date != null && tag != null && activityType != null) {

                        String validID = db_select.getAvailActivitiesID();
                        String sql = " INSERT INTO `activities` (`activitiesID`, `name`, `districtNo`, `quota`, `remain`, `targetAgeMax`, `targetAgeMin`, `deadline`, `venue`, `date`, `tag`, `staffID`, `description`) VALUES ('" + validID + "', '" + name + "', '" + districtNo + "', '" + quota + "', '" + quota + "', '" + targetAgeMax + "', '" + targetAgeMin + "', '" + deadline + "', '" + venue + "', '" + date + "', '" + tag + "', '" + "1"
                                + "', '" + description + "')";
                        boolean check1 = db_select.editRecordBySql(sql);

                        String sql2 = "INSERT INTO `atype_a` (`activityTypeID`, `activitiesID`) VALUES ('" + activityType + "', '" + validID + "')";
                        boolean check2 = db_select.editRecordBySql(sql2);

                        if (check1 == true && check2 == true) {
                            request.setAttribute("status", "Insert Success");
                            request.setAttribute("link", "activities.jsp");
                            RequestDispatcher rd
                                    = request.getServletContext().getRequestDispatcher("/requestSuccess.jsp");
                            rd.forward(request, response);
                        } else {
                            request.setAttribute("status", "Insert Error");
                            request.setAttribute("link", "activities.jsp");
                            RequestDispatcher rd
                                    = request.getServletContext().getRequestDispatcher("/requestError.jsp");
                            rd.forward(request, response);
                        }
                    } else {
                        request.setAttribute("status", "Insert Error - Please Check Again");
                        request.setAttribute("link", "activities.jsp");
                        RequestDispatcher rd
                                = request.getServletContext().getRequestDispatcher("/requestError.jsp");
                        rd.forward(request, response);
                    }
                    break;
                }
                case "activityBudget": {
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

                    if (itemName != null && itemType != null && cost != null && number != null && remark != null && activities_id != null) {
                        String validID = db_select.getAvailBudgetItemID();
                        String sql = "INSERT INTO `activitybudget` (`itemID`, `activitiesID`, `itemName`, `itemType`, `cost`, `number`, `remark`) VALUES('" + validID + "', '" + activities_id + "', '" + itemName + "', '" + itemType + "', '" + cost + "', '" + number + "', '" + remark + "')";
                        boolean check = db_select.editRecordBySql(sql);
                        if (check) {
                            request.setAttribute("status", "Insert Success");
                            request.setAttribute("link", "activitiesBudget.jsp?table_type=activities&activities_id=" + activities_id);
                            RequestDispatcher rd
                                    = request.getServletContext().getRequestDispatcher("/requestSuccess.jsp");
                            rd.forward(request, response);
                        } else {
                            request.setAttribute("status", "Insert Error");
                            request.setAttribute("link", "activitiesBudget.jsp?table_type=activities&activities_id=" + activities_id);
                            RequestDispatcher rd
                                    = request.getServletContext().getRequestDispatcher("/requestError.jsp");
                            rd.forward(request, response);
                        }
                    } else {
                        request.setAttribute("status", "Insert Error - Please Check Again");
                        request.setAttribute("link", "activities,jsp");
                        RequestDispatcher rd
                                = request.getServletContext().getRequestDispatcher("/requestError.jsp");
                        rd.forward(request, response);
                    }

                    break;
                }

                case "activityTimetable": {
                    String detail = null;
                    String startTime = null;
                    String finishTime = null;
                    String activities_id = null;
                    detail = request.getParameter("detail");
                    startTime = request.getParameter("startTime");
                    finishTime = request.getParameter("finishTime");
                    activities_id = request.getParameter("activities_id");

                    if (detail != null && startTime != null && finishTime != null && activities_id != null) {

                        String validID = db_select.getAvailTimeTableID();
                        String sql = "INSERT INTO `activitytimetable` (`activityTimeTableID`, `activitiesID`, `detail`, `startTime`, `finishTime`) VALUES ('" + validID + "', '" + activities_id + "', '" + detail + "', '" + startTime + "', '" + finishTime + "');";
                        boolean check = db_select.editRecordBySql(sql);
                        if (check) {
                            request.setAttribute("status", "Insert Success");
                            request.setAttribute("link", "activitiyTimetable.jsp?activities_id=" + activities_id);
                            RequestDispatcher rd
                                    = request.getServletContext().getRequestDispatcher("/requestSuccess.jsp");
                            rd.forward(request, response);
                        } else {
                            request.setAttribute("status", "Insert Error");
                            request.setAttribute("link", "activitiyTimetable.jsp?activities_id=" + activities_id);
                            RequestDispatcher rd
                                    = request.getServletContext().getRequestDispatcher("/requestError.jsp");
                            rd.forward(request, response);
                        }
                    } else {
                        request.setAttribute("status", "Insert Error - Please Check Again");
                        request.setAttribute("link", "activities.jsp");
                        RequestDispatcher rd
                                = request.getServletContext().getRequestDispatcher("/requestError.jsp");
                        rd.forward(request, response);
                    }
                    break;
                }
                case "survey": {
                    String survey_id = null;
                    String qTypeID = null;
                    String question = null;

                    survey_id = request.getParameter("survey_id");
                    qTypeID = request.getParameter("qTypeID");
                    question = request.getParameter("question");
                    if (survey_id != null && qTypeID != null && question != null) {
                        String validID = db_select.getAvailSurveyQuestionID();
                        String sql = "INSERT INTO `surveyquestion` (`sqID`, `question`, `qTypeID`, `maxMark`, `minMark`,`surveyID`) VALUES ('" + validID + "', '" + question + "', '" + qTypeID + "', " + "10" + ", " + "1" + ",'" + survey_id + "');";
                        boolean check = db_select.editRecordBySql(sql);
                        if (check) {
                            request.setAttribute("status", "Insert Success");
                            request.setAttribute("link", "survey.jsp");
                            RequestDispatcher rd = request.getServletContext().getRequestDispatcher("/requestSuccess.jsp");
                            rd.forward(request, response);
                        } else {
                            request.setAttribute("status", "Insert Error");
                            request.setAttribute("link", "survey.jsp");
                            RequestDispatcher rd = request.getServletContext().getRequestDispatcher("/requestError.jsp");
                            rd.forward(request, response);
                        }
                    } else {
                        request.setAttribute("status", "Insert Error - Please Check Again");
                        request.setAttribute("link", "survey.jsp");
                        RequestDispatcher rd = request.getServletContext().getRequestDispatcher("/requestError.jsp");
                        rd.forward(request, response);
                    }
                    break;
                }
                default:
                    break;
            }
        } catch (Exception ex) {

            request.setAttribute("status", ex.getMessage());
            request.setAttribute("link", "activities.jsp");
            RequestDispatcher rd = request.getServletContext().getRequestDispatcher("/requestError.jsp");
            rd.forward(request, response);

        }
    }

}
