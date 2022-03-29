package com.azs.azsproj;

public class HistoryAZS {
    public String getOil() {
        return Oil;
    }

    public void setOil(String oil) {
        Oil = oil;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }

    public double getLiters() {
        return Liters;
    }

    public void setLiters(double liters) {
        Liters = liters;
    }

    public double getPrice() {
        return Price;
    }

    public void setPrice(double price) {
        Price = price;
    }

    public double getSumm() {
        return Summ;
    }

    public void setSumm(double summ) {
        Summ = summ;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getIdCo() {
        return idCo;
    }

    public void setIdCo(String idCo) {
        this.idCo = idCo;
    }

    public HistoryAZS(int ID, String oil, double liters, Double price, Double summ, String status, String idCo ) {
        this.Oil = oil;
        this.Status = status;
        this.Liters = liters;
        this.Price = price;
        this.Summ = summ;
        this.ID = ID;
        this.idCo = idCo;
    }

    String Oil, Status, idCo;
    double Liters;
    Double Price;
    Double Summ;
    int ID;
}
