package db.bean;

import java.io.Serializable;



/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author test
 */
public class ActivityTypeBean implements Serializable{

    String activityTypeID;
    String typeName;
    String description;

    @Override
    public String toString() {
        return "ActivityTypeBean{" + "activityTypeID=" + activityTypeID + ", typeName=" + typeName + ", description=" + description + '}';
    }

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
