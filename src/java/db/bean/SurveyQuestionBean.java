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
public class SurveyQuestionBean {

    String sqID;
    String question;
    int qTypeID;
    int maxMark;
    int minMark;

    public SurveyQuestionBean(String sqID, String question, int qTypeID, int maxMark, int minMark) {
        this.sqID = sqID;
        this.question = question;
        this.qTypeID = qTypeID;
        this.maxMark = maxMark;
        this.minMark = minMark;
    }

    public String getSqID() {
        return sqID;
    }

    public void setSqID(String sqID) {
        this.sqID = sqID;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public int getqTypeID() {
        return qTypeID;
    }

    public void setqTypeID(int qTypeID) {
        this.qTypeID = qTypeID;
    }

    public int getMaxMark() {
        return maxMark;
    }

    public void setMaxMark(int maxMark) {
        this.maxMark = maxMark;
    }

    public int getMinMark() {
        return minMark;
    }

    public void setMinMark(int minMark) {
        this.minMark = minMark;
    }

}
