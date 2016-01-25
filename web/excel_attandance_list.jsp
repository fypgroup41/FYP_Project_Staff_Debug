<%@page import="java.util.Date"%><%@page import="db.bean.MemberBean"%><%@page import="db.bean.ActivitiesRecordBean"%><%@page import="ict.caculate.DateCalculate"%><%@page import="java.text.SimpleDateFormat"%><%@page import="db.handle.DB_Select"%><%@page import="java.util.ArrayList"%><%
    response.reset();
    response.setContentType("text/csv");
    response.setHeader("Content-disposition", "inline; filename=test.csv");
    DateCalculate dc = new DateCalculate();
    SimpleDateFormat sdfbd = new SimpleDateFormat("yyyy-MM-dd");
    String dbUser = this.getServletContext().getInitParameter("dbUsername");
    String dbPassword = this.getServletContext().getInitParameter("dbPassword");
    String dbUrl = this.getServletContext().getInitParameter("dbUrl");
    DB_Select db_select = new DB_Select(dbUrl, dbUser, dbPassword);
    String a = null;
    String b = null;
    String c = null;
    String d = null;
    String e = null;
    ArrayList activitiesRecord = db_select.queryListAll("ActivitiesRecord", " where activitiesID=\"" + request.getParameter("activities_id") + "\"");
    out.print("Member ID, District ID,Age ,Nick Name, Status");
      out.println();
    if (activitiesRecord.size() != 0) {

        for (int j = 0; j < activitiesRecord.size(); j++) {
            ActivitiesRecordBean activitiesRecord_val = (ActivitiesRecordBean) activitiesRecord.get(j);
            ArrayList member_aryData = db_select.queryListAll("member", " where memberID='" + activitiesRecord_val.getMemberID() + "'");
            MemberBean member_val2 = (MemberBean) member_aryData.get(0);

            a = activitiesRecord_val.getMemberID();

            b = member_val2.getDistrictID();

            String bate = member_val2.getBirthday();
            Date bd = sdfbd.parse(bate);
            c = dc.calculateAge(bd) + "";

            d = member_val2.getNickName();
            e = activitiesRecord_val.getState();

            out.print(a + "," + b + "," + c + "," + d + "," + e);
            out.println();
        }

    }
%>    