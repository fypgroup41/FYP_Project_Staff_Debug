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
public class StaffBean {

    String staffID;
    String districtID;
    String ccID;
    String position;

    public StaffBean(String staffID, String districtID, String ccID, String position) {
        this.staffID = staffID;
        this.districtID = districtID;
        this.ccID = ccID;
        this.position = position;
    }

    public String getStaffID() {
        return staffID;
    }

    public void setStaffID(String staffID) {
        this.staffID = staffID;
    }

    public String getDistrictID() {
        return districtID;
    }

    public void setDistrictID(String districtID) {
        this.districtID = districtID;
    }

    public String getCcID() {
        return ccID;
    }

    public void setCcID(String ccID) {
        this.ccID = ccID;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

}
