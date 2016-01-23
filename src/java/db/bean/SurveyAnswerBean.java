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
public class SurveyAnswerBean {

    String saID;
    String answer;
    String sqID;
    String memberID;
    String userID;

    public SurveyAnswerBean() {
    }

    public SurveyAnswerBean(String saID, String answer, String sqID, String memberID) {
        this.saID = saID;
        this.answer = answer;
        this.sqID = sqID;
        this.memberID = memberID;

    }

    public String getSaID() {
        return saID;
    }

    public void setSaID(String saID) {
        this.saID = saID;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String getSqID() {
        return sqID;
    }

    public void setSqID(String sqID) {
        this.sqID = sqID;
    }

    public String getMemberID() {
        return memberID;
    }

    public void setMemberID(String memberID) {
        this.memberID = memberID;
    }

}
