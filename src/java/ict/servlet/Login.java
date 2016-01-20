/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ict.servlet;

import db.bean.UserBean;
import db.handle.DB_Select;
import java.io.*;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

/**
 *
 * @author user
 */
public class Login extends HttpServlet {

    private DB_Select db;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String dbUser = this.getServletContext().getInitParameter("dbUsername");
        String dbPassword = this.getServletContext().getInitParameter("dbPassword");
        String dbUrl = this.getServletContext().getInitParameter("dbUrl");
        db = new DB_Select(dbUrl, dbUser, dbPassword);
        try {
            PrintWriter out = response.getWriter();
            String action = request.getParameter("action");
            if (!isAuthenticated(request)
                    && !("authenticate".equals(action))) {
                doLogin(request, response);
                return;
            }
            switch (action) {
                case "authenticate":
                    doAuthenticate(request, response);
                    break;
                case "logout":
                    doLogout(request, response);
                    return;
                default:
                    response.sendError(HttpServletResponse.SC_NOT_IMPLEMENTED);
                    break;
            }
            RequestDispatcher rd;
            rd = getServletContext().getRequestDispatcher("/errorLogin.jsp");
            rd.forward(request, response);
        } catch (Exception ex) {
            PrintWriter out = response.getWriter();
            out.println(ex.getMessage());
        }
    }

    private boolean isAuthenticated(HttpServletRequest request) {
        boolean result = false;
        HttpSession session = request.getSession();
        if (session.getAttribute("userInfo") != null) {
            result = true;
        }
        return result;
    }

    private void doLogin(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        response.sendRedirect(request.getContextPath() + ("/main.jsp"));
    }

    private void doLogout(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.removeAttribute("userInfo");
            session.invalidate();
        }
        doLogin(request, response);
    }

    private void doAuthenticate(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        try {

            String username = request.getParameter("username");
            String password = request.getParameter("password");
            String targetURL;
            boolean isValid = db.isValidUser(username, password);
            if (isValid) {
                HttpSession session;
                session = request.getSession(true);
                session.setAttribute("userInfo", db.queryUserByUsername(username));

                if (session.getAttribute("userInfo") != null) {
                    UserBean user = (UserBean) session.getAttribute("userInfo");
                    if (user.getMemberID() != null) {
                        session.setAttribute("userTypeLevel", "member");
                        session.setAttribute("userName", username);
                    }
                    if (user.getStaffID() != null) {
                        session.setAttribute("userTypeLevel", "staff");
                        session.setAttribute("userTypeID", user.getStaffID());
                        session.setAttribute("userName", username);
                    }
                }

                RequestDispatcher rd;
                rd = getServletContext().getRequestDispatcher("/main.jsp");
                rd.forward(request, response);
            } else {
                 RequestDispatcher rd;
                rd = getServletContext().getRequestDispatcher("/errorLogin.jsp");
                rd.forward(request, response);

            }
        } catch (Exception ex) {
            PrintWriter out = response.getWriter();
            out.println("Login Fail! Prease try again" + ex.getMessage());
        }
    }

}
