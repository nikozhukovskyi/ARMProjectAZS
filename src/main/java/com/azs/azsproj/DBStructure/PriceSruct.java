package com.azs.azsproj.DBStructure;

public class PriceSruct {
    public String getPosCode() {
        return PosCode;
    }

    public void setPosCode(String PosCode) {
        this.PosCode = PosCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getPrice_name() {
        return price_name;
    }

    public void setPrice_name(double price_name) {
        this.price_name = price_name;
    }

    public PriceSruct(int id, String PosCode, String name, double price_name) {
        this.id = id;
        this.PosCode = PosCode;
        this.name = name;
        this.price_name = price_name;
    }

    String PosCode, name;
    double price_name;
    int id;

}
