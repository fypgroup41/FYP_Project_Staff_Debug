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
public class ActivitiesRecordBean {

    String activitiesRecordID;
    String activitiesID;
    String memberID;
    String state;

    public ActivitiesRecordBean(String activitiesRecordID, String activitiesID, String memberID, String state) {
        this.activitiesRecordID = activitiesRecordID;
        this.activitiesID = activitiesID;
        this.memberID = memberID;
        this.state = state;
    }

    public String getActivitiesRecordID() {
        return activitiesRecordID;
    }

    public void setActivitiesRecordID(String activitiesRecordID) {
        this.activitiesRecordID = activitiesRecordID;
    }

    public String getActivitiesID() {
        return activitiesID;
    }

    public void setActivitiesID(String activitiesID) {
        this.activitiesID = activitiesID;
    }

    public String getMemberID() {
        return memberID;
    }

    public void setMemberID(String memberID) {
        this.memberID = memberID;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;

    }
}
