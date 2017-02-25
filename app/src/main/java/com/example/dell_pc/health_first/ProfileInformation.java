package com.example.dell_pc.health_first;

/**
 * Created by Hamed on 2/15/2017.
 */

public class ProfileInformation {
    public String userName;
    public String age;
    public String weight;
    public String height;
    public String blood;
    public String doctor;
    public String emerName;
    public String emerContact;

    public ProfileInformation() {

    }

    public ProfileInformation(String userName, String weight,String height,String age,String blood,String doctor,
                              String emerName ,String emerContact) {

        this.userName=userName;
        this.weight= weight;
        this.height=height;
        this.age=age;
        this.blood=blood;
        this.doctor=doctor;
        this.emerName=emerName;
        this.emerContact=emerContact;

    }

    //Getters and setters
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }
    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }
    public String getBlood() {
        return blood;
    }

    public void setBlood(String blood) {
        this.blood = blood;
    }

    public String getDoctor() {
        return doctor;
    }

    public void setDoctor(String weight) {
        this.doctor = doctor;
    }
    public String getEmerName() {
        return emerName;
    }

    public void setEmerName(String emerName) {
        this.emerName = emerName;
    }

    public String getEmerContact() {
        return emerContact;
    }

    public void setEmerContact(String emerContact) {
        this.age = emerContact;
    }
}
