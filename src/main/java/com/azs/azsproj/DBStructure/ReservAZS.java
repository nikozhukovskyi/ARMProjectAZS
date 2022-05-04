package com.azs.azsproj.DBStructure;

public class ReservAZS {

    String Oil;
    double Liters, Temp, Mass, Price;
    int ID;
    String Code;

    public String getOil() {
        return Oil;
    }

    public void setOil(String oil) {
        Oil = oil;
    }

    public double getLiters() {
        return Liters;
    }

    public void setLiters(double liters) {
        Liters = liters;
    }

    public double getTemp() {
        return Temp;
    }

    public void setTemp(double temp) {
        Temp = temp;
    }

    public double getMass() {
        return Mass;
    }

    public void setMass(double mass) {
        Mass = mass;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public double getPrice() {
        return Price;
    }

    public void setPrice(double price) {
        Price = price;
    }

    public String getCode() {
        return Code;
    }

    public void setCode(String code) {
        Code = code;
    }

    public ReservAZS(int ID, String Code, String Oil, double Liters, double Temp, double Mass, double Price) {
        this.Oil = Oil;
        this.Liters = Liters;
        this.Temp = Temp;
        this.Mass = Mass;
        this.ID = ID;
        this.Price = Price;
        this.Code = Code;
    }

}
