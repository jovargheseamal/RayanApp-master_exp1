package com.example.user123.myapplication;

public class CompanyModelClass {

 private String ownerName, employeeCount,CompID,CompanyName,ContactPerson,ContactNo,TradeLicence,TradeLicenceExpiry,TRN,LabourCardNumber,ImmigrationCardNumber,ImmigrationCardNumberExpiry,Tenancy;



    public CompanyModelClass(String compID,String ownerName,  String companyName, String contactPerson, String contactNo, String tradeLicence, String tradeLicenceExpiry, String TRN, String labourCardNumber, String immigrationCardNumber, String immigrationCardNumberExpiry, String tenancy,String employeeCount)
    {
        this.CompID = compID;
        this.ownerName = ownerName;
        this.CompanyName = companyName;
        this. ContactPerson = contactPerson;
        this. ContactNo = contactNo;
        this. TradeLicence = tradeLicence;
        this.TradeLicenceExpiry = tradeLicenceExpiry;
        this.TRN = TRN;
        this.LabourCardNumber = labourCardNumber;
        this.ImmigrationCardNumber = immigrationCardNumber;
        this.ImmigrationCardNumberExpiry = immigrationCardNumberExpiry;
        this.Tenancy = tenancy;
        this.employeeCount = employeeCount;
    }

    public String getCompID() {
        return CompID;
    }

    public void setCompID(String compID) {
        CompID = compID;
    }

//    public String getOwnerID() {
//        return OwnerID;
//    }
//
//    public void setOwnerID(String ownerID) {
//        OwnerID = ownerID;
//    }

    public String getCompanyName() {
        return CompanyName;
    }

    public void setCompanyName(String companyName) {
        CompanyName = companyName;
    }

    public String getContactPerson() {
        return ContactPerson;
    }

    public void setContactPerson(String contactPerson) {
        ContactPerson = contactPerson;
    }

    public String getContactNo() {
        return ContactNo;
    }

    public void setContactNo(String contactNo) {
        ContactNo = contactNo;
    }

    public String getTradeLicence() {
        return TradeLicence;
    }

    public void setTradeLicence(String tradeLicence) {
        TradeLicence = tradeLicence;
    }

    public String getTradeLicenceExpiry() {
        return TradeLicenceExpiry;
    }

    public void setTradeLicenceExpiry(String tradeLicenceExpiry) {
        TradeLicenceExpiry = tradeLicenceExpiry;
    }

    public String getTRN() {
        return TRN;
    }

    public void setTRN(String TRN) {
        this.TRN = TRN;
    }

    public String getLabourCardNumber() {
        return LabourCardNumber;
    }

    public void setLabourCardNumber(String labourCardNumber) {
        LabourCardNumber = labourCardNumber;
    }

    public String getImmigrationCardNumber() {
        return ImmigrationCardNumber;
    }

    public void setImmigrationCardNumber(String immigrationCardNumber) {
        ImmigrationCardNumber = immigrationCardNumber;
    }

    public String getImmigrationCardNumberExpiry() {
        return ImmigrationCardNumberExpiry;
    }

    public void setImmigrationCardNumberExpiry(String immigrationCardNumberExpiry) {
        ImmigrationCardNumberExpiry = immigrationCardNumberExpiry;
    }

    public String getEmployeeCount() {
        return employeeCount;
    }

    public void setEmployeeCount(String employeeCount) {
        this.employeeCount = employeeCount;
    }

    public String getTenancy() {
        return Tenancy;
    }



    public void setTenancy(String tenancy) {
        Tenancy = tenancy;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }
}
