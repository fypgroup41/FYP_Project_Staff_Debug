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
public class MemberBean {

    String memberID;
    String districtID;
    String nickName;
    String Birthday;
    String address;
    String parent;
    String relationship;
    String emergency_phone;
    String school;
    String reg_date;
    int isHelper;

    public MemberBean(String memberID, String districtID, String nickName, String Birthday, String address, String parent, String relationship, String emergency_phone, String school, String reg_date, int isHelper) {
        this.memberID = memberID;
        this.districtID = districtID;
        this.nickName = nickName;
        this.Birthday = Birthday;
        this.address = address;
        this.parent = parent;
        this.relationship = relationship;
        this.emergency_phone = emergency_phone;
        this.school = school;
        this.reg_date = reg_date;
        this.isHelper = isHelper;
    }

    public String getMemberID() {
        return memberID;
    }

    public void setMemberID(String memberID) {
        this.memberID = memberID;
    }

    public String getDistrictID() {
        return districtID;
    }

    public void setDistrictID(String districtID) {
        this.districtID = districtID;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getBirthday() {
        return Birthday;
    }

    public void setBirthday(String Birthday) {
        this.Birthday = Birthday;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getParent() {
        return parent;
    }

    public void setParent(String parent) {
        this.parent = parent;
    }

    public String getRelationship() {
        return relationship;
    }

    public void setRelationship(String relationship) {
        this.relationship = relationship;
    }

    public String getEmergency_phone() {
        return emergency_phone;
    }

    public void setEmergency_phone(String emergency_phone) {
        this.emergency_phone = emergency_phone;
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public String getReg_date() {
        return reg_date;
    }

    public void setReg_date(String reg_date) {
        this.reg_date = reg_date;
    }

    public int getIsHelper() {
        return isHelper;
    }

    public void setIsHelper(int isHelper) {
        this.isHelper = isHelper;
    }

}
