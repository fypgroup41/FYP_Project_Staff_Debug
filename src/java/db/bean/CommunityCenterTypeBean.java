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
public class CommunityCenterTypeBean {

    String communityCenterTypeID;
    String typeName;
    String description;

    public CommunityCenterTypeBean(String communityCenterTypeID, String typeName, String description) {
        this.communityCenterTypeID = communityCenterTypeID;
        this.typeName = typeName;
        this.description = description;
    }

    public String getCommunityCenterTypeID() {
        return communityCenterTypeID;
    }

    public void setCommunityCenterTypeID(String communityCenterTypeID) {
        this.communityCenterTypeID = communityCenterTypeID;
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
