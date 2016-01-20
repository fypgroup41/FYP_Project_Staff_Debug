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
public class CCtype_CCBean {

    String ccTypeID;
    String ccID;

    public CCtype_CCBean(String ccTypeID, String ccID) {
        this.ccTypeID = ccTypeID;
        this.ccID = ccID;
    }

    public String getCcTypeID() {
        return ccTypeID;
    }

    public void setCcTypeID(String ccTypeID) {
        this.ccTypeID = ccTypeID;
    }

    public String getCcID() {
        return ccID;
    }

    public void setCcID(String ccID) {
        this.ccID = ccID;
    }

}
