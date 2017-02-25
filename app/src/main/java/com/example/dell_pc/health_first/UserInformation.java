package com.example.dell_pc.health_first;

/**
 * Created by nikpa on 11-02-2017.
 */

public class UserInformation {
    public String firstName;
    public String lastName;

    public String age;
    public Double phoneNumber;
    // public String emergencyContact;
    public String username;
    public String password;

    public UserInformation(){

    }

    public UserInformation(String firstName, String lastName,String age,Double phoneNumber,
                           String username ,String password) {

        this.firstName=firstName;
        this.lastName=lastName;

        this.age=age;
        this.phoneNumber=phoneNumber;
        // this.emergencyContact=emergencyContact;
        this.username=username;
        this.password=password;

    }


    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }



    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public Double getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(Double phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    /*public String getEmergencyContact() {
        return emergencyContact;
    }

    public void setEmergencyContact(String emergencyContact) {
        this.emergencyContact = emergencyContact;
    }*/

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
