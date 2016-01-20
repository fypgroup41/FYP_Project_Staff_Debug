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
public class ActivityTypeBean {

    String activityTypeID;
    String typeName;
    String description;

    public ActivityTypeBean(String activityTypeID, String typeName, String description) {
        this.activityTypeID = activityTypeID;
        this.typeName = typeName;
        this.description = description;
    }

    public String getActivityTypeID() {
        return activityTypeID;
    }

    public void setActivityTypeID(String activityTypeID) {
        this.activityTypeID = activityTypeID;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
