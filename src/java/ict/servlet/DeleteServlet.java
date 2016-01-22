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
        try {
            table_type = request.getParameter("table_type");

            if (table_type.equals("activities")) {
                item_id = request.getParameter("item_id");
                Boolean a = db_select.delRecordBySql("DELETE FROM atype_a WHERE activitiesID='" + item_id + "'");
                Boolean b = db_select.delRecordBySql("DELETE FROM ACTIVITIES WHERE activitiesID='" + item_id + "'");

                request.setAttribute("status", "Delete Success");
                RequestDispatcher rd
                        = request.getServletContext().getRequestDispatcher("/updateSucess.jsp");
                rd.forward(request, response);
            }

            if (table_type.equals("activityBudget")) {
                item_id = request.getParameter("item_id");
                Boolean a = db_select.delRecordBySql("DELETE FROM activitybudget WHERE itemID='" + item_id + "'");
                request.setAttribute("status", "Delete Success");
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
