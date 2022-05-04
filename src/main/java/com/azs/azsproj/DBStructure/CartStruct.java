package com.azs.azsproj.DBStructure;

public class CartStruct {
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCartid() {
        return Cartid;
    }

    public void setCartid(String cartid) {
        Cartid = cartid;
    }

    public String getCartOil() {
        return CartOil;
    }

    public void setCartOil(String cartOil) {
        CartOil = cartOil;
    }

    public double getCartLiters() {
        return CartLiters;
    }

    public void setCartLiters(double cartLiters) {
        CartLiters = cartLiters;
    }

    public double getCartPrice() {
        return CartPrice;
    }

    public void setCartPrice(double cartPrice) {
        CartPrice = cartPrice;
    }

    public double getCartSumm() {
        return CartSumm;
    }

    public void setCartSumm(double cartSumm) {
        CartSumm = cartSumm;
    }

    public CartStruct(int id, String cartid, String cartOil, double cartLiters, double cartPrice, double cartSumm) {
        this.id = id;
        Cartid = cartid;
        CartOil = cartOil;
        CartLiters = cartLiters;
        CartPrice = cartPrice;
        CartSumm = cartSumm;
    }

    int id;
    String Cartid, CartOil;
    double CartLiters, CartPrice, CartSumm;
}
