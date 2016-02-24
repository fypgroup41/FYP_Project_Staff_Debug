/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ict.servlet;

import db.bean.ActivitiesBean;
import db.bean.UserBean;
import db.handle.DB_Select;
import java.io.*;
import java.util.ArrayList;
import java.util.Date;
import javax.servlet.*;

import javax.servlet.http.*;
import net.sf.json.*;

public class LoginJSON extends HttpServlet {

    private DB_Select db_select;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String dbUser = this.getServletContext().getInitParameter("dbUsername");
        String dbPassword = this.getServletContext().getInitParameter("dbPassword");
        String dbUrl = this.getServletContext().getInitParameter("dbUrl");
        db_select = new DB_Select(dbUrl, dbUser, dbPassword);
        JSONArray jsonArray2 = new JSONArray();
        PrintWriter out = response.getWriter();

        try {

            ArrayList aryData = db_select.queryListAll("user", " where userName=\"" + request.getParameter("userName") + "\" and password=\"" + request.getParameter("password") + "\"");

            if (!aryData.isEmpty()) {
                for (int i = 0; i < aryData.size(); i++) {
                    UserBean user = (UserBean) aryData.get(i);
                    response.setContentType("text/html;charset=UTF-8");
                    JSONArray jsonArray = new JSONArray();
                    jsonArray.add(0, user.getStaffID());
                    jsonArray.add(1, user.getEmail());
                    jsonArray.add(2, user.getTel());
                    jsonArray2.add(jsonArray);
                }
            } else {
                /*  response.setContentType("text/html;charset=UTF-8");
                JSONArray jsonArray = new JSONArray();
                jsonArray.add(0, "null1234");
                jsonArray.add(1, "null1234");
                jsonArray.add(2, "null1234");
                jsonArray2.add(jsonArray);*/
            }

            out.println(jsonArray2);
            
            /*  response.setContentType("application/json");
             request.setCharacterEncoding("UTF-8");
             JSONArray jArray = new JSONArray();

             JSONObject arrayObj = new JSONObject();
             arrayObj.put("username", "A");
             arrayObj.put("password", "B");

             jArray.add(0, arrayObj);
             PrintWriter out = response.getWriter();
             out.print(jArray);*/
 /*  response.setContentType("text/html;charset=UTF-8");
             //      
      

        

          
             request.setAttribute("data", data);
             */
 /*RequestDispatcher rd;
             rd = getServletContext().getRequestDispatcher("/main.jsp");
             rd.forward(request, response);*/
        } catch (Exception ex) {

            out.println(ex.getMessage());
        }
    }

}
