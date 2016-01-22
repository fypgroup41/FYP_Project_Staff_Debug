/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db.handle;

import db.bean.ActivitiesBean;
import db.bean.ActivitiesRecordBean;
import db.bean.ActivityBudgetBean;
import db.bean.ActivityTimetableBean;
import db.bean.ActivityTypeBean;
import db.bean.AdminBean;
import db.bean.Atype_ABean;
import db.bean.CCtype_CCBean;
import db.bean.CommunityCenterBean;
import db.bean.CommunityCenterTypeBean;
import db.bean.DistrictBean;
import db.bean.JobRecordBean;
import db.bean.MemberBean;
import db.bean.StaffBean;
import db.bean.SurveyAnswerBean;
import db.bean.SurveyQuestionBean;
import db.bean.SurveyqTypeBean;
import db.bean.UserBean;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author a1
 */
public class DB_Select {

    public DB_Select() {
    }

    private String url = "";
    private String username = "";
    private String password = "";

    public DB_Select(String url, String username, String password) {
        this.url = url;
        this.username = username;
        this.password = password;

    }

    public Connection getConnection() throws SQLException, IOException {
        System.setProperty("jdbc.drivers", "com.mysql.jdbc.Driver");

        try {
            Class.forName("com.mysql.jdbc.Driver");

        } catch (ClassNotFoundException ex) {
            //  Logger.getLogger(DB.class.getName()).log(Level.SEVERE, null, ex);
        }

        return DriverManager.getConnection(url, username, password);
    }

    public ArrayList getList(ResultSet rs, String bean) throws SQLException {
        ArrayList list = new ArrayList();

        if (bean.equals("activities")) {
            ActivitiesBean activities;
            while (rs.next()) {
                activities = new ActivitiesBean(rs.getString("activitiesID"), rs.getString("name"), rs.getString("districtNo"), rs.getInt("quota"), rs.getInt("remain"), rs.getInt("targetAgeMax"), rs.getInt("targetAgeMin"), rs.getString("deadline"), rs.getString("venue"), rs.getString("date"), rs.getString("tag"), rs.getString("staffID"), rs.getString("description"));
                list.add(activities);
            }
        }
        if (bean.equals("admin")) {
            AdminBean admin;
            while (rs.next()) {
                admin = new AdminBean(rs.getString("adminID"), rs.getString("login_time"), rs.getString("modify_time"), rs.getString("position"));
                list.add(admin);
            }
        }

        if (bean.equals("activitiesrecord")) {
            ActivitiesRecordBean activitiesRecord;
            while (rs.next()) {
                activitiesRecord = new ActivitiesRecordBean(rs.getString("activitiesRecordID"), rs.getString("activitiesID"), rs.getString("memberID"), rs.getString("state"));
                list.add(activitiesRecord);
            }
        }

        if (bean.equals("activitybudget")) {
            ActivityBudgetBean activityBudget;
            while (rs.next()) {
                activityBudget = new ActivityBudgetBean(rs.getString("itemID"), rs.getString("activitiesID"), rs.getString("itemName"), rs.getString("itemType"), rs.getDouble("cost"), rs.getInt("number"), rs.getString("remark"));
                list.add(activityBudget);
            }
        }
        return list;
    }

    public String[][] getSql(String aa) {
        Connection cnnct = null;
        PreparedStatement pStmnt = null;
        String[][] temp = null;
        try {

            ResultSet rs = null;
            cnnct = getConnection();
            String preQueryStatement = aa;

            pStmnt = cnnct.prepareStatement(preQueryStatement);
            rs = pStmnt.executeQuery();
            ResultSetMetaData rsmd = rs.getMetaData();
            int columnCount = rsmd.getColumnCount();
            rs.last();
            temp = new String[rs.getRow() + 1][columnCount];
            Logger _log = Logger.getLogger(DB_Select.class.getName());
            rs.beforeFirst();
            for (int i = 0; i < columnCount; i++) {
                temp[0][i] = rsmd.getColumnName(i + 1);
            }
            int count = 0;
            while (rs.next()) {
                count++;
                for (int j = 0; j < temp[0].length; j++) {
                    temp[count][j] = rs.getString(j + 1);
                }
            }
            pStmnt.close();
            cnnct.close();

        } catch (SQLException ex) {
            while (ex != null) {
                ex.printStackTrace();
                ex = ex.getNextException();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return temp;

    }

    public boolean isValidStaff(String username, String password) {
        Connection cnnct = null;
        PreparedStatement pStmnt = null;
        String[][] temp = null;
        boolean isValid = false;
        try {
            cnnct = getConnection();
            String preQueryStatement = "SELECT * FROM USER WHERE USERNAME =  ? and  PASSWORD =  ?  AND STAFFID IS NOT NULL";
            pStmnt = cnnct.prepareStatement(preQueryStatement);
            pStmnt.setString(1, username);
            pStmnt.setString(2, password);

            ResultSet rs = pStmnt.executeQuery();
            if (rs.next()) {
                isValid = true;
            }

            pStmnt.close();
            cnnct.close();
        } catch (SQLException ex) {
            while (ex != null) {
                ex.printStackTrace();
                ex = ex.getNextException();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return isValid;
    }

    public ArrayList queryDistrict() {
        Connection cnnct = null;
        PreparedStatement pStmnt = null;
        ArrayList list = new ArrayList();
        DistrictBean district = null;
        try {

            ResultSet rs = null;
            cnnct = getConnection();
            String preQueryStatement = "SELECT* FROM DISTRICT";
            pStmnt = cnnct.prepareStatement(preQueryStatement);
            rs = pStmnt.executeQuery();
            while (rs.next()) {
                district = new DistrictBean(rs.getString(1), rs.getString(2), rs.getString(3));
                list.add(district);
            }
            pStmnt.close();
            cnnct.close();

        } catch (SQLException ex) {
            while (ex != null) {
                ex.printStackTrace();
                ex = ex.getNextException();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return list;

    }

    public UserBean queryUserByUsername(String userName) {
        Connection cnnct = null;
        PreparedStatement pStmnt = null;
        ArrayList list = new ArrayList();
        UserBean user = null;
        try {

            ResultSet rs = null;
            cnnct = getConnection();
            String preQueryStatement = "SELECT* FROM USER WHERE USERNAME=? and adminID is null";
            pStmnt = cnnct.prepareStatement(preQueryStatement);

            pStmnt.setString(1, userName);
            rs = pStmnt.executeQuery();
            while (rs.next()) {
                user = new UserBean(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8), rs.getString(9), rs.getString(10), rs.getString(11), rs.getString(12), rs.getInt(13));
            }
            pStmnt.close();
            cnnct.close();

        } catch (SQLException ex) {
            while (ex != null) {
                ex.printStackTrace();
                ex = ex.getNextException();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return user;
    }

    public StaffBean queryStaffByStaffID(String StaffID) {
        Connection cnnct = null;
        PreparedStatement pStmnt = null;
        ArrayList list = new ArrayList();
        StaffBean user = null;
        try {

            ResultSet rs = null;
            cnnct = getConnection();
            String preQueryStatement = "SELECT* FROM STAFF WHERE STAFFID=?";
            pStmnt = cnnct.prepareStatement(preQueryStatement);

            pStmnt.setString(1, StaffID);
            rs = pStmnt.executeQuery();
            while (rs.next()) {
                user = new StaffBean(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4));
            }
            pStmnt.close();
            cnnct.close();

        } catch (SQLException ex) {
            while (ex != null) {
                ex.printStackTrace();
                ex = ex.getNextException();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return user;
    }

    public String getAvailMemberID() {
        Connection cnnct = null;
        boolean isSuccess = false;
        SimpleDateFormat formatter = null;
        java.util.Date utilDate = null;
        ResultSet rs = null;
        String maxID = null;
        int newAvaildID = -1;
        try {
            cnnct = getConnection();
            String preQueryStatement = "select memberID from member ORDER BY CAST(memberID AS SIGNED) DESC LIMIT 1";
            PreparedStatement pStmnt = cnnct.prepareStatement(preQueryStatement);
            rs = pStmnt.executeQuery();
            while (rs.next()) {
                maxID = rs.getString(1);
            }
            newAvaildID = Integer.parseInt(maxID) + 1;
        } catch (SQLException ex) {
            while (ex != null) {
                ex.printStackTrace();
                ex = ex.getNextException();

            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return newAvaildID + "";
    }

    public String getAvailUserID() {
        Connection cnnct = null;
        boolean isSuccess = false;
        SimpleDateFormat formatter = null;
        java.util.Date utilDate = null;
        ResultSet rs = null;
        String maxID = null;
        int newAvaildID = -1;
        try {
            cnnct = getConnection();
            String preQueryStatement = "select userID from user ORDER BY CAST(userID AS SIGNED) DESC LIMIT 1";
            PreparedStatement pStmnt = cnnct.prepareStatement(preQueryStatement);
            rs = pStmnt.executeQuery();
            while (rs.next()) {
                maxID = rs.getString(1);
            }
            newAvaildID = Integer.parseInt(maxID) + 1;
        } catch (SQLException ex) {
            while (ex != null) {
                ex.printStackTrace();
                ex = ex.getNextException();

            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return newAvaildID + "";
    }

    public boolean addGeneralUser_User(String availdID, String userName, String password, String availMemberID, String givenName, String surName, String gender, String tel, String chinese, String email, int isAuthenticated) throws ParseException, IOException {
        PreparedStatement pStmnt = null;
        Connection cnnct = null;
        boolean isSuccess = false;
        Logger _log = Logger.getLogger("addGeneralUser");
        try {
            String preQueryStatement = "INSERT INTO USER VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?)";
            cnnct = getConnection();
            pStmnt = cnnct.prepareStatement(preQueryStatement);
            pStmnt.setString(1, availdID);
            pStmnt.setString(2, userName);
            pStmnt.setString(3, password);
            pStmnt.setString(4, availMemberID);
            pStmnt.setString(5, null);
            pStmnt.setString(6, null);
            pStmnt.setString(7, givenName);
            pStmnt.setString(8, surName);
            pStmnt.setString(9, gender);
            pStmnt.setString(10, tel);
            pStmnt.setString(11, chinese);
            pStmnt.setString(12, email);
            pStmnt.setInt(13, 0);

            _log.log(Level.INFO, "addGeneralUser{0}", " " + pStmnt.toString());

            int rowCount = pStmnt.executeUpdate();
            if (rowCount >= 1) {
                isSuccess = true;
            }
            pStmnt.close();
            cnnct.close();

        } catch (SQLException ex) {
            while (ex != null) {
                ex.printStackTrace();
                ex = ex.getNextException();

            }
        }
        return isSuccess;
    }

    public boolean addGeneralUser_Member(String availdID, String districtID, String nickName, String birthday, String address, String parent, String relationship, String emergency_phone, String school) throws ParseException {
        PreparedStatement pStmnt = null;
        Connection cnnct = null;
        boolean isSuccess = false;
        SimpleDateFormat formatter = null;
        java.util.Date utilDate = null;

        Logger _log = Logger.getLogger("addGeneralUser ");
        try {

            formatter = new SimpleDateFormat("yyyy-MM-dd");
            utilDate = formatter.parse(birthday);
            cnnct = getConnection();

            String preQueryStatement = "INSERT INTO MEMBER VALUES (?,?,?,?,?,?,?,?,?,?,?)";
            pStmnt = cnnct.prepareStatement(preQueryStatement);
            pStmnt.setString(1, availdID + "");
            pStmnt.setString(2, "2");
            pStmnt.setString(3, nickName);
            pStmnt.setTimestamp(4, new java.sql.Timestamp(utilDate.getTime()));
            pStmnt.setString(5, address);
            pStmnt.setString(6, parent);
            pStmnt.setString(7, relationship);
            pStmnt.setString(8, emergency_phone);
            pStmnt.setString(9, school);
            pStmnt.setTimestamp(10, new java.sql.Timestamp(new java.util.Date().getTime()));
            pStmnt.setInt(11, 0);
            _log.log(Level.INFO, "addGeneralUser{0}", " " + pStmnt.toString());

            int rowCount = pStmnt.executeUpdate();
            if (rowCount >= 1) {
                isSuccess = true;
            }
            pStmnt.close();
            cnnct.close();

        } catch (SQLException ex) {
            while (ex != null) {
                ex.printStackTrace();
                ex = ex.getNextException();

            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return isSuccess;

    }

    public ArrayList queryActivitiesBySql(String sql) {
        Connection cnnct = null;
        PreparedStatement pStmnt = null;
        ArrayList list = new ArrayList();
        ActivitiesBean activities;
        try {

            ResultSet rs = null;
            cnnct = getConnection();
            String preQueryStatement = sql;
            pStmnt = cnnct.prepareStatement(preQueryStatement);

            rs = pStmnt.executeQuery();
            while (rs.next()) {
                activities = new ActivitiesBean(rs.getString("activitiesID"), rs.getString("name"), rs.getString("districtNo"), rs.getInt("quota"), rs.getInt("remain"), rs.getInt("targetAgeMax"), rs.getInt("targetAgeMin"), rs.getString("deadline"), rs.getString("venue"), rs.getString("date").substring(0, rs.getString("date").length() - 2), rs.getString("tag"), rs.getString("staffID"), rs.getString("description"));
                list.add(activities);
            }
            pStmnt.close();
            cnnct.close();

        } catch (SQLException ex) {
            while (ex != null) {
                ex.printStackTrace();
                ex = ex.getNextException();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return list;
    }

    public static Calendar DateToCalendar(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return cal;
    }

    public ArrayList queryListAll(String table, String condition) throws SQLException, IOException {
        Connection cnnct = null;
        PreparedStatement pStmnt = null;
        cnnct = getConnection();
        ArrayList list = new ArrayList();
        try {
            ResultSet rs = null;

            if (table.equals("activities")) {
                String preQueryStatement = "SELECT* FROM ACTIVITIES " + condition;
                pStmnt = cnnct.prepareStatement(preQueryStatement);
                rs = pStmnt.executeQuery();
                while (rs.next()) {
                    ActivitiesBean activities = new ActivitiesBean(rs.getString("activitiesID"), rs.getString("name"), rs.getString("districtNo"), rs.getInt("quota"), rs.getInt("remain"), rs.getInt("targetAgeMax"), rs.getInt("targetAgeMin"), rs.getString("deadline"), rs.getString("venue"), rs.getString("date").substring(0, rs.getString("date").length() - 2), rs.getString("tag"), rs.getString("staffID"), rs.getString("description"));
                    list.add(activities);
                }
                pStmnt.close();

            }
            if (table.equals("ActivitiesRecord")) {
                String preQueryStatement = "SELECT* FROM ActivitiesRecord" + condition;
                pStmnt = cnnct.prepareStatement(preQueryStatement);
                rs = pStmnt.executeQuery();
                while (rs.next()) {
                    ActivitiesRecordBean activitiesRecord = new ActivitiesRecordBean(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4));
                    list.add(activitiesRecord);
                }
                pStmnt.close();

            }
            if (table.equals("activityBudget")) {
                String preQueryStatement = "SELECT* FROM ActivityBudget" + condition;
                pStmnt = cnnct.prepareStatement(preQueryStatement);
                rs = pStmnt.executeQuery();
                while (rs.next()) {
                    ActivityBudgetBean activityBudget = new ActivityBudgetBean(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getDouble(5), rs.getInt(6), rs.getString(7));
                    list.add(activityBudget);
                }
                pStmnt.close();

            }
            if (table.equals("activityTimetable")) {
                String preQueryStatement = "SELECT* FROM ActivityTimetable" + condition;
                pStmnt = cnnct.prepareStatement(preQueryStatement);
                rs = pStmnt.executeQuery();
                while (rs.next()) {
                    ActivityTimetableBean activityTimetable = new ActivityTimetableBean(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5));

                    list.add(activityTimetable);
                }
                pStmnt.close();

            }
            if (table.equals("activityType")) {
                String preQueryStatement = "SELECT* FROM ActivityType" + condition;
                pStmnt = cnnct.prepareStatement(preQueryStatement);
                rs = pStmnt.executeQuery();
                while (rs.next()) {
                    ActivityTypeBean activityType = new ActivityTypeBean(rs.getString(1), rs.getString(2), rs.getString(3));
                    list.add(activityType);
                }
                pStmnt.close();

            }
            if (table.equals("atype_A")) {
                String preQueryStatement = "SELECT* FROM Atype_A" + condition;
                pStmnt = cnnct.prepareStatement(preQueryStatement);
                rs = pStmnt.executeQuery();
                while (rs.next()) {
                    Atype_ABean aType_A = new Atype_ABean(rs.getString(1), rs.getString(2));
                    list.add(aType_A);
                }
                pStmnt.close();

            }
            if (table.equals("cCtype_CC")) {
                String preQueryStatement = "SELECT* FROM CCtype_CC" + condition;
                pStmnt = cnnct.prepareStatement(preQueryStatement);
                rs = pStmnt.executeQuery();
                while (rs.next()) {
                    CCtype_CCBean cCtype_CC = new CCtype_CCBean(rs.getString(1), rs.getString(2));
                    list.add(cCtype_CC);
                }
                pStmnt.close();

            }
            if (table.equals("communityCenter")) {
                String preQueryStatement = "SELECT* FROM CommunityCenter" + condition;
                pStmnt = cnnct.prepareStatement(preQueryStatement);
                rs = pStmnt.executeQuery();
                while (rs.next()) {
                    CommunityCenterBean communityCenter = new CommunityCenterBean(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8));
                    list.add(communityCenter);
                }
                pStmnt.close();

            }
            if (table.equals("communityCenterType")) {
                String preQueryStatement = "SELECT* FROM CommunityCenterType" + condition;
                pStmnt = cnnct.prepareStatement(preQueryStatement);
                rs = pStmnt.executeQuery();
                while (rs.next()) {
                    CommunityCenterTypeBean communityCenterType = new CommunityCenterTypeBean(rs.getString(1), rs.getString(2), rs.getString(3));
                    list.add(communityCenterType);
                }
                pStmnt.close();

            }
            if (table.equals("district")) {
                String preQueryStatement = "SELECT* FROM District" + condition;
                pStmnt = cnnct.prepareStatement(preQueryStatement);
                rs = pStmnt.executeQuery();
                while (rs.next()) {
                    DistrictBean district = new DistrictBean(rs.getString(1), rs.getString(2), rs.getString(3));
                    list.add(district);
                }
                pStmnt.close();

            }
            if (table.equals("jobRecord")) {
                String preQueryStatement = "SELECT* FROM JobRecord" + condition;
                pStmnt = cnnct.prepareStatement(preQueryStatement);
                rs = pStmnt.executeQuery();
                while (rs.next()) {
                    JobRecordBean jobRecord = new JobRecordBean(rs.getString(1), rs.getString(2), rs.getString(3));
                    list.add(jobRecord);
                }
                pStmnt.close();

            }
            if (table.equals("member")) {
                String preQueryStatement = "SELECT* FROM Member" + condition;
                pStmnt = cnnct.prepareStatement(preQueryStatement);
                rs = pStmnt.executeQuery();
                while (rs.next()) {
                    MemberBean member = new MemberBean(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8), rs.getString(9), rs.getString(10), rs.getInt(11));
                    list.add(member);
                }
                pStmnt.close();

            }
            if (table.equals("staff")) {
                String preQueryStatement = "SELECT* FROM Staff" + condition;
                pStmnt = cnnct.prepareStatement(preQueryStatement);
                rs = pStmnt.executeQuery();
                while (rs.next()) {
                    StaffBean staff = new StaffBean(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4));
                    list.add(staff);
                }
                pStmnt.close();

            }
            if (table.equals("surveyAnswer")) {
                String preQueryStatement = "SELECT* FROM SurveyAnswer" + condition;
                pStmnt = cnnct.prepareStatement(preQueryStatement);
                rs = pStmnt.executeQuery();
                while (rs.next()) {
                    SurveyAnswerBean surveyAnswer = new SurveyAnswerBean(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5));
                    list.add(surveyAnswer);
                }
                pStmnt.close();
            }
            if (table.equals("surveyQuestion")) {
                String preQueryStatement = "SELECT* FROM ACTIVITIES" + condition;
                pStmnt = cnnct.prepareStatement(preQueryStatement);
                rs = pStmnt.executeQuery();
                while (rs.next()) {
                    SurveyQuestionBean surveyQuestion = new SurveyQuestionBean(rs.getString(1), rs.getString(2), rs.getInt(3), rs.getInt(4), rs.getInt(5));
                    list.add(surveyQuestion);
                }
                pStmnt.close();

            }
            if (table.equals("surveyqType")) {
                String preQueryStatement = "SELECT* FROM SurveyqType" + condition;
                pStmnt = cnnct.prepareStatement(preQueryStatement);
                rs = pStmnt.executeQuery();
                while (rs.next()) {
                    SurveyqTypeBean surveyqType = new SurveyqTypeBean(rs.getString(1), rs.getString(2));
                    list.add(surveyqType);
                }
                pStmnt.close();

            }
            if (table.equals("UserBean")) {
                String preQueryStatement = "SELECT* FROM ACTIVITIES" + condition;
                pStmnt = cnnct.prepareStatement(preQueryStatement);
                rs = pStmnt.executeQuery();
                while (rs.next()) {
                    UserBean user = new UserBean(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8), rs.getString(9), rs.getString(10), rs.getString(11), rs.getString(12), rs.getInt(13));
                    list.add(user);
                }
                pStmnt.close();

            }
            cnnct.close();
        } catch (SQLException ex) {
            while (ex != null) {
                ex.printStackTrace();
                ex = ex.getNextException();
            }
        }
        return list;
    }

    public boolean editRecordBySql(String sql) throws SQLException, IOException {
        Connection cnnct = null;
        PreparedStatement pStmnt = null;
        boolean isSuccess = false;
        cnnct = getConnection();
        try {
            Statement stmt = cnnct.createStatement();
            String update = sql;
            stmt.executeUpdate(update);
            int i = stmt.executeUpdate(update);
            if (i >= 1) {
                return true;
            }
            pStmnt.close();
            cnnct.close();

        } catch (SQLException ex) {
            while (ex != null) {
                ex.printStackTrace();
                ex = ex.getNextException();
            }
        }
        return false;
    }

    public boolean delRecordBySql(String sql) throws SQLException, IOException {
        Connection cnnct = null;

        boolean isSuccess = false;
        cnnct = getConnection();
        try {
            Statement stmt = cnnct.createStatement();
            String update = sql;
            stmt.executeUpdate(update);
            int i = stmt.executeUpdate(update);
            if (i >= 1) {
                return true;
            }

            cnnct.close();

        } catch (SQLException ex) {
            while (ex != null) {
                ex.printStackTrace();
                ex = ex.getNextException();
            }
        }
        return false;
    }

    /*public CategoryBean queryCategoryByID(String catId) {
     Connection cnnct = null;
     PreparedStatement pStmnt = null;
     try {
     ResultSet rs = null;
     cnnct = getConnection();
     String preQueryStatement = "SELECT* FROM CATEGORY WHERE CATID=?";
     pStmnt = cnnct.prepareStatement(preQueryStatement);

     pStmnt.setString(1, catId);
     rs = pStmnt.executeQuery();
     if (rs.next()) {
     category = new CategoryBean(rs.getString("catId"), rs.getString("catName"));
     }
     pStmnt.close();
     cnnct.close();

     } catch (SQLException ex) {
     while (ex != null) {
     ex.printStackTrace();
     ex = ex.getNextException();
     }
     } catch (IOException ex) {
     ex.printStackTrace();
     }
     return category;
     }
     public boolean editOrderRecord(OrderBean cb) {
     Connection cnnct = null;
     PreparedStatement pStmnt = null;
     boolean isSuccess = false;

     try {
     cnnct = getConnection();
     String preQueryStatement = "UPDATE UORDER SET UID=? , OMODE=?, OSTATUS=? WHERE OID=?";
     pStmnt = cnnct.prepareStatement(preQueryStatement);
     pStmnt.setString(1, cb.getuId());
     pStmnt.setString(2, cb.getoMode());
     pStmnt.setString(3, cb.getoStatus());
     pStmnt.setString(4, cb.getoId());

     int rowCount = pStmnt.executeUpdate();
     if (rowCount >= 1) {
     isSuccess = true;
     }

     pStmnt.close();
     cnnct.close();

     } catch (SQLException ex) {
     while (ex != null) {
     ex.printStackTrace();
     ex = ex.getNextException();
     }
     } catch (IOException ex) {
     ex.printStackTrace();
     }
     return isSuccess;
     }
     public boolean addProductRecord(String pId, String pName, String description, String mId, String cId, double price, int stockQty, String productPhoto) {
     Connection cnnct = null;
     PreparedStatement pStmnt = null;
     boolean isSuccess = false;
     try {
     cnnct = getConnection();
     String preQueryStatement = "INSERT INTO PRODUCT VALUES (?,?,?,?,?,?,?,?)";
     pStmnt = cnnct.prepareStatement(preQueryStatement);
     pStmnt.setString(1, pId);
     pStmnt.setString(2, pName);
     pStmnt.setString(3, description);
     pStmnt.setString(4, mId);
     pStmnt.setString(5, cId);
     pStmnt.setDouble(6, price);
     pStmnt.setInt(7, stockQty);
     pStmnt.setString(8, productPhoto);
     int rowCount = pStmnt.executeUpdate();
     if (rowCount >= 1) {
     isSuccess = true;
     }
     pStmnt.close();
     cnnct.close();

     } catch (SQLException ex) {
     while (ex != null) {
     ex.printStackTrace();
     ex = ex.getNextException();

     }
     } catch (IOException ex) {
     ex.printStackTrace();
     }
     return isSuccess;

     }

     public boolean delRecord(String table, String id) {
     Connection cnnct = null;
     PreparedStatement pStmnt = null;
     String preQueryStatement = null;
     try {
     ResultSet rs = null;
     cnnct = getConnection();
     if (table.equals("Category")) {
     preQueryStatement = "DELETE FROM CATEGORY WHERE CATID='" + id + "'";
     }
     if (table.equals("ProductOrder")) {
     preQueryStatement = "DELETE FROM PRODUCT_ORDER WHERE OID='" + id + "'";
     }
     if (table.equals("Product")) {
     preQueryStatement = "DELETE FROM PRODUCT WHERE PID='" + id + "'";
     }
     if (table.equals("Order")) {
     preQueryStatement = "DELETE FROM UORDER WHERE OID='" + id + "'";
     }
     if (table.equals("Manufacturer")) {
     preQueryStatement = "DELETE FROM MANUFACTURER WHERE MID='" + id + "'";
     }

     if (table.equals("Gift")) {
     preQueryStatement = "DELETE FROM gift WHERE gid='" + id + "'";
     }

     pStmnt = cnnct.prepareStatement(preQueryStatement);    
     int i = pStmnt.executeUpdate();
     if (i >= 1) {
     return true;
     }
     pStmnt.close();
     cnnct.close();

     } catch (SQLException ex) {
     while (ex != null) {
     ex.printStackTrace();
     ex = ex.getNextException();
     }
     } catch (IOException ex) {
     ex.printStackTrace();
     }
     return false;
     }
     */
}
