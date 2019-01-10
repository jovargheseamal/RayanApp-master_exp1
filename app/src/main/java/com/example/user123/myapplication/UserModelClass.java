package com.example.user123.myapplication;

public class UserModelClass {

    String CompanyName;
    String UserID;
    String Name;
    String UserName;
    String ContactNo,CompId;

    public UserModelClass(String companyName, String userID, String name, String userName, String contactNo, String compId) {
        CompanyName = companyName;
        UserID = userID;
        Name = name;
        UserName = userName;
        ContactNo = contactNo;
        CompId = compId;
    }

    public String getCompanyName() {
        return CompanyName;
    }

    public void setCompanyName(String companyName) {
        CompanyName = companyName;
    }

    public String getUserID() {
        return UserID;
    }

    public void setUserID(String userID) {
        UserID = userID;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }

    public String getContactNo() {
        return ContactNo;
    }

    public void setContactNo(String contactNo) {
        ContactNo = contactNo;
    }

    public String getCompId() {
        return CompId;
    }

    public void setCompId(String compId) {
        CompId = compId;
    }
}
