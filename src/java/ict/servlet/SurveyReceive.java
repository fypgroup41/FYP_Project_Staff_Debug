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

public class SurveyReceive extends HttpServlet {

    private DB_Select db_select;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String dbUser = this.getServletContext().getInitParameter("dbUsername");
        String dbPassword = this.getServletContext().getInitParameter("dbPassword");
        String dbUrl = this.getServletContext().getInitParameter("dbUrl");
        db_select = new DB_Select(dbUrl, dbUser, dbPassword);
        try {
            PrintWriter out = response.getWriter();
            Enumeration<String> parameterNames = request.getParameterNames();

            String memberID = request.getParameter("memberID");

            while (parameterNames.hasMoreElements()) {

                String paramName = parameterNames.nextElement();

                if (!paramName.equals("memberID")) {

                    String[] paramValues = request.getParameterValues(paramName);
                    String val = "";
                    for (int i = 0; i < paramValues.length; i++) {
                        String paramValue = paramValues[i];
                        val = val + paramValue + ";";
                    }
                    val = val.substring(0, val.length() - 1);
                    String sql = "INSERT INTO `surveyanswer` (`saID`, `answer`, `sqID`, `memberID`) VALUES('" + db_select.getAvailSurveyAnswerID() + "', '" + val + "', '" + paramName + "', '" + memberID + "')";
                    boolean isSuccess = db_select.editRecordBySql(sql);
                    request.setAttribute("status", "Receive Success");
                    request.setAttribute("link", "survey.jsp");
                    RequestDispatcher rd = request.getServletContext().getRequestDispatcher("/updateSucess.jsp");
                    rd.forward(request, response);
                }

            }
        } catch (Exception ex) {
            PrintWriter out = response.getWriter();
            out.println(ex.getMessage());
        }
    }

}
