package com.example.business_deal;

public class Business_class {

    private String name,fhname,adress,mobno,description,totalweight,money,tokenno,dateofreservation,dateofwithdrawn,email;

    public Business_class()
    {

    }
    public Business_class(String name, String fhname, String adress, String mobno, String description, String totalweight, String money, String tokenno, String dateofreservation, String dateofwithdrawn,String email) {
        this.name = name;
        this.fhname = fhname;
        this.adress = adress;
        this.mobno = mobno;
        this.description = description;
        this.totalweight = totalweight;
        this.money = money;
        this.tokenno = tokenno;
        this.dateofreservation = dateofreservation;
        this.dateofwithdrawn = dateofwithdrawn;
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFhname() {
        return fhname;
    }

    public void setFhname(String fhname) {
        this.fhname = fhname;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public String getMobno() {
        return mobno;
    }

    public void setMobno(String mobno) {
        this.mobno = mobno;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTotalweight() {
        return totalweight;
    }

    public void setTotalweight(String totalweight) {
        this.totalweight = totalweight;
    }

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }

    public String getTokenno() {
        return tokenno;
    }

    public void setTokenno(String tokenno) {
        this.tokenno = tokenno;
    }

    public String getDateofreservation() {
        return dateofreservation;
    }

    public void setDateofreservation(String dateofreservation) {
        this.dateofreservation = dateofreservation;
    }

    public String getDateofwithdrawn() {
        return dateofwithdrawn;
    }

    public void setDateofwithdrawn(String dateofwithdrawn) {
        this.dateofwithdrawn = dateofwithdrawn;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}
