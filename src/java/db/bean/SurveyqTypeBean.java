package db.bean;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author test
 */
public class SurveyqTypeBean {

    String sQTypeID;
    String name;

    public SurveyqTypeBean(String sQTypeID, String name) {
        this.sQTypeID = sQTypeID;
        this.name = name;
    }

    public String getsQTypeID() {
        return sQTypeID;
    }

    public void setsQTypeID(String sQTypeID) {
        this.sQTypeID = sQTypeID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
