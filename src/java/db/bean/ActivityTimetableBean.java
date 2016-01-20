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
public class ActivityTimetableBean {

    String activityTimeTableID;
    String Activities_activitiesID;
    String detail;
    String startTime;
    String finish;

    public ActivityTimetableBean(String activityTimeTableID, String Activities_activitiesID, String detail, String startTime, String finish) {
        this.activityTimeTableID = activityTimeTableID;
        this.Activities_activitiesID = Activities_activitiesID;
        this.detail = detail;
        this.startTime = startTime;
        this.finish = finish;
    }

    public String getActivityTimeTableID() {
        return activityTimeTableID;
    }

    public void setActivityTimeTableID(String activityTimeTableID) {
        this.activityTimeTableID = activityTimeTableID;
    }

    public String getActivities_activitiesID() {
        return Activities_activitiesID;
    }

    public void setActivities_activitiesID(String Activities_activitiesID) {
        this.Activities_activitiesID = Activities_activitiesID;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getFinish() {
        return finish;
    }

    public void setFinish(String finish) {
        this.finish = finish;
    }

}
