package com.example.barun.baccha;

import android.app.Activity;

/**
 * Created by barun on 11-05-2017.
 */

public class Datacollect {
    private String Parent_Name,Child_Name,image,user;

    public Datacollect(){

    }

    public Datacollect(String parent_Name, String child_Name, String image /*,String user*/) {
        Parent_Name = parent_Name;
        Child_Name = child_Name;
        this.image = image;
        // this.user = user;
    }

    public String getParent_Name() {
        return Parent_Name;
    }

    public void setParent_Name(String Parent_Name) {
        Parent_Name = Parent_Name;
    }

    public String getChild_Name() {
        return Child_Name;
    }

    public void setChild_Name(String child_Name) {
        Child_Name = child_Name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

   /* public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }*/
}
