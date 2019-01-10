package com.example.user123.myapplication;

public class EmployeeModelClass {

  private   String EmpID, CompId, EmpName, Address, PhoneNo, DOB, EmailID, EmiratesID, PassportNO, PassportExpiry, WorkPermit, WorkPermitExpiry, VisaExpiry, UIDNumber, FileNumber, CompanyName;

    public EmployeeModelClass(String empID, String compId, String empName, String address, String phoneNo, String DOB, String emailID, String emiratesID, String passportNO, String passportExpiry, String workPermit, String workPermitExpiry, String visaExpiry, String UIDNumber, String fileNumber, String companyName) {
        this.EmpID = empID;
        this.CompId = compId;
        this.EmpName = empName;
        this.Address = address;
        this.PhoneNo = phoneNo;
        this.DOB = DOB;
        this.EmailID = emailID;
        this.EmiratesID = emiratesID;
        this.PassportNO = passportNO;
        this.PassportExpiry = passportExpiry;
        this.WorkPermit = workPermit;
        this.WorkPermitExpiry = workPermitExpiry;
        this.VisaExpiry = visaExpiry;
        this.UIDNumber = UIDNumber;
        this.FileNumber = fileNumber;
        this.CompanyName = companyName;
    }

    public String getEmpID() {
        return EmpID;
    }

    public void setEmpID(String empID) {
        EmpID = empID;
    }

    public String getCompId() {
        return CompId;
    }

    public void setCompId(String compId) {
        CompId = compId;
    }

    public String getEmpName() {
        return EmpName;
    }

    public void setEmpName(String empName) {
        EmpName = empName;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public String getPhoneNo() {
        return PhoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        PhoneNo = phoneNo;
    }

    public String getDOB() {
        return DOB;
    }

    public void setDOB(String DOB) {
        this.DOB = DOB;
    }

    public String getEmailID() {
        return EmailID;
    }

    public void setEmailID(String emailID) {
        EmailID = emailID;
    }

    public String getEmiratesID() {
        return EmiratesID;
    }

    public void setEmiratesID(String emiratesID) {
        EmiratesID = emiratesID;
    }

    public String getPassportNO() {
        return PassportNO;
    }

    public void setPassportNO(String passportNO) {
        PassportNO = passportNO;
    }

    public String getPassportExpiry() {
        return PassportExpiry;
    }

    public void setPassportExpiry(String passportExpiry) {
        PassportExpiry = passportExpiry;
    }

    public String getWorkPermit() {
        return WorkPermit;
    }

    public void setWorkPermit(String workPermit) {
        WorkPermit = workPermit;
    }

    public String getWorkPermitExpiry() {
        return WorkPermitExpiry;
    }

    public void setWorkPermitExpiry(String workPermitExpiry) {
        WorkPermitExpiry = workPermitExpiry;
    }

    public String getVisaExpiry() {
        return VisaExpiry;
    }

    public void setVisaExpiry(String visaExpiry) {
        VisaExpiry = visaExpiry;
    }

    public String getUIDNumber() {
        return UIDNumber;
    }

    public void setUIDNumber(String UIDNumber) {
        this.UIDNumber = UIDNumber;
    }

    public String getFileNumber() {
        return FileNumber;
    }

    public void setFileNumber(String fileNumber) {
        FileNumber = fileNumber;
    }

    public String getCompanyName() {
        return CompanyName;
    }

    public void setCompanyName(String companyName) {
        CompanyName = companyName;
    }
}