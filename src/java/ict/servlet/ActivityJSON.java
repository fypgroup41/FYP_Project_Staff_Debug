/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ict.servlet;

import db.bean.ActivitiesBean;
import db.bean.DistrictBean;
import db.handle.DB_Select;
import java.io.*;
import java.util.ArrayList;
import java.util.Date;
import javax.servlet.*;

import javax.servlet.http.*;
import net.sf.json.*;

public class ActivityJSON extends HttpServlet {

    private DB_Select db_select;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String dbUser = this.getServletContext().getInitParameter("dbUsername");
        String dbPassword = this.getServletContext().getInitParameter("dbPassword");
        String dbUrl = this.getServletContext().getInitParameter("dbUrl");
        db_select = new DB_Select(dbUrl, dbUser, dbPassword);
        JSONArray jsonArray2 = new JSONArray();
        PrintWriter out = response.getWriter();

        try {
            ArrayList activity_aryData = db_select.queryActivitiesBySql("select * from activities where staffID=\"" + request.getParameter("id") + "\"");
            for (int i = 0; i < activity_aryData.size(); i++) {
                ActivitiesBean act = (ActivitiesBean) activity_aryData.get(i);
                ArrayList district_aryData = db_select.queryListAll("district", " where districtID='" + act.getDistrictNo() + "'");
                DistrictBean district = (DistrictBean) district_aryData.get(0);
                response.setContentType("text/html;charset=UTF-8");
                JSONArray jsonArray = new JSONArray();
                jsonArray.add(0, act.getName());
                jsonArray.add(1, act.getDate());
                jsonArray.add(2, act.getVenue());
                jsonArray.add(3, district.getName());
                jsonArray.add(4, act.getRemain());
                jsonArray.add(5, act.getTargetAgeMax());
                jsonArray.add(6, act.getTargetAgeMin());
                jsonArray.add(7, act.getDeadline());
                jsonArray.add(8, act.getVenue());
                jsonArray.add(9, act.getDate());
                jsonArray.add(10, act.getTag());
                jsonArray.add(11, act.getStaffID());
                jsonArray.add(12, act.getDescription());
                jsonArray2.add(jsonArray);
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
