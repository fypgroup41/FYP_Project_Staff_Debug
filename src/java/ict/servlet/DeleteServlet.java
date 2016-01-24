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

        try {
            table_type = request.getParameter("table_type");

            switch (table_type) {
                case "activities": {
                    String activities_id = null;
                    activities_id = request.getParameter("activities_id");
                    if (activities_id != null) {
                        Boolean check1 = db_select.delRecordBySql("DELETE FROM atype_a WHERE activitiesID='" + activities_id + "'");
                        Boolean check2 = db_select.delRecordBySql("DELETE FROM ACTIVITIES WHERE activitiesID='" + activities_id + "'");
                        if (check1 == true && check2 == true) {
                            request.setAttribute("status", "Delete Success");
                            request.setAttribute("link", "activities.jsp");
                            RequestDispatcher rd
                                    = request.getServletContext().getRequestDispatcher("/requestSuccess.jsp");
                            rd.forward(request, response);
                        } else {
                            request.setAttribute("status", "Delete Error");
                            request.setAttribute("link", "activities.jsp");
                            RequestDispatcher rd
                                    = request.getServletContext().getRequestDispatcher("/requestError.jsp");
                            rd.forward(request, response);
                        }
                    } else {
                        request.setAttribute("status", "Delete Error - Not Activities ID");
                        request.setAttribute("link", "activities.jsp");
                        RequestDispatcher rd
                                = request.getServletContext().getRequestDispatcher("/requestError.jsp");
                        rd.forward(request, response);
                    }
                    break;
                }
                case "activityBudget": {
                    String activities_id = null;
                    String item_id = null;
                    item_id = request.getParameter("item_id");
                    activities_id = request.getParameter("activities_id");
                    if (activities_id != null && item_id != null) {
                        Boolean check = db_select.delRecordBySql("DELETE FROM activitybudget WHERE itemID='" + item_id + "'");
                        if (check) {
                            request.setAttribute("status", "Delete Success");
                            request.setAttribute("link", "activitiesBudget.jsp?table_type=activities&activities_id=" + activities_id);
                            RequestDispatcher rd
                                    = request.getServletContext().getRequestDispatcher("/requestSuccess.jsp");
                            rd.forward(request, response);

                        } else {
                            request.setAttribute("status", "Delete Error");
                            request.setAttribute("link", "activitiesBudget.jsp?table_type=activities&activities_id=" + activities_id);
                            RequestDispatcher rd
                                    = request.getServletContext().getRequestDispatcher("/requestError.jsp");
                            rd.forward(request, response);
                        }
                    } else {
                        request.setAttribute("status", "Delete Error - No Activities ID or Item ID");
                        request.setAttribute("link", "activities.jsp");
                        RequestDispatcher rd
                                = request.getServletContext().getRequestDispatcher("/requestError.jsp");
                        rd.forward(request, response);
                    }

                    break;
                }
                case "activityTimetable": {
                    String activities_id = null;
                    String timetable_id = null;

                    timetable_id = request.getParameter("timetable_id");
                    activities_id = request.getParameter("activities_id");
                    if (activities_id != null && timetable_id != null) {
                        Boolean check = db_select.delRecordBySql("DELETE FROM activityTimetable WHERE activityTimeTableID='" + timetable_id + "'");
                        if (check) {
                            request.setAttribute("status", "Delete Success");
                            request.setAttribute("link", "activitiyTimetable.jsp?activities_id=" + activities_id);
                            RequestDispatcher rd
                                    = request.getServletContext().getRequestDispatcher("/requestSuccess.jsp");
                            rd.forward(request, response);
                        } else {
                            request.setAttribute("status", "Delete Error");
                            request.setAttribute("link", "activitiyTimetable.jsp?activities_id=" + activities_id);
                            RequestDispatcher rd
                                    = request.getServletContext().getRequestDispatcher("/requestError.jsp");
                            rd.forward(request, response);
                        }
                    } else {
                        request.setAttribute("status", "Delete Error - No Activities ID or Timetable ID");
                        request.setAttribute("link", "activities.jsp");
                        RequestDispatcher rd
                                = request.getServletContext().getRequestDispatcher("/requestError.jsp");
                        rd.forward(request, response);
                    }
                    break;
                }
                case "survey": {
                    String sqID = null;
                    sqID = request.getParameter("sqID");
                    if (sqID != null) {
                        Boolean check = db_select.delRecordBySql("DELETE FROM surveyquestion WHERE sqID='" + sqID + "'");
                        if (check) {
                            request.setAttribute("status", "Delete Success");
                            request.setAttribute("link", "surveyDetail.jsp");
                            RequestDispatcher rd
                                    = request.getServletContext().getRequestDispatcher("/requestSuccess.jsp");
                            rd.forward(request, response);
                        } else {
                            request.setAttribute("status", "Delete Error");
                            request.setAttribute("link", "surveyDetail.jsp");
                            RequestDispatcher rd
                                    = request.getServletContext().getRequestDispatcher("/requestError.jsp");
                            rd.forward(request, response);
                        }
                    } else {
                        request.setAttribute("status", "Delete Error - No SQ ID");
                        request.setAttribute("link", "surveyDetail.jsp");
                        RequestDispatcher rd
                                = request.getServletContext().getRequestDispatcher("/requestError.jsp");
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
