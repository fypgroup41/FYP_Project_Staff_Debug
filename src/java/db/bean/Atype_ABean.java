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
public class Atype_ABean {

    String activityTypeID;
    String activitiesID;

    public Atype_ABean(String activityTypeID, String activitiesID) {
        this.activityTypeID = activityTypeID;
        this.activitiesID = activitiesID;
    }

    public String getActivityTypeID() {
        return activityTypeID;
    }

    public void setActivityTypeID(String activityTypeID) {
        this.activityTypeID = activityTypeID;
    }

    public String getActivitiesID() {
        return activitiesID;
    }

    public void setActivitiesID(String activitiesID) {
        this.activitiesID = activitiesID;
    }

}
