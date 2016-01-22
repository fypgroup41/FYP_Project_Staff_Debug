/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ict.servlet;

import db.bean.ActivitiesBean;
import db.handle.DB_Select;
import java.io.*;
import java.util.ArrayList;
import java.util.Date;
import javax.servlet.*;

import javax.servlet.http.*;
import net.sf.json.*;

public class UpdateServlet extends HttpServlet {

    private DB_Select db_select;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String dbUser = this.getServletContext().getInitParameter("dbUsername");
        String dbPassword = this.getServletContext().getInitParameter("dbPassword");
        String dbUrl = this.getServletContext().getInitParameter("dbUrl");
        db_select = new DB_Select(dbUrl, dbUser, dbPassword);
        PrintWriter out = response.getWriter();
        try {

            boolean a = db_select.editActivitiesRecord(null);
            out.println("AAA");

        } catch (Exception ex) {

            out.println(ex.getMessage());
        }
    }

}
