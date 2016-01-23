/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db.bean;

import java.io.Serializable;

/**
 *
 * @author test
 */
public class SurveyBean implements Serializable{

    String surveyID;
    String activitiesID;

    public SurveyBean() {
    }

    public SurveyBean(String surveyID, String activitiesID) {
        this.surveyID = surveyID;
        this.activitiesID = activitiesID;
    }

    public String getSurveyID() {
        return surveyID;
    }

    public void setSurveyID(String surveyID) {
        this.surveyID = surveyID;
    }

    public String getActivitiesID() {
        return activitiesID;
    }

    public void setActivitiesID(String activitiesID) {
        this.activitiesID = activitiesID;
    }

}
