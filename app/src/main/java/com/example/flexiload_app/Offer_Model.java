package com.example.flexiload_app;

public class Offer_Model {
    String offer_price,offer_name,offer_time,offer_des;

    public Offer_Model() {
    }

    public Offer_Model(String offer_price, String offer_name, String offer_time, String offer_des) {
        this.offer_price = offer_price;
        this.offer_name = offer_name;
        this.offer_time = offer_time;
        this.offer_des = offer_des;
    }

    public String getOffer_price() {
        return offer_price;
    }

    public void setOffer_price(String offer_price) {
        this.offer_price = offer_price;
    }

    public String getOffer_name() {
        return offer_name;
    }

    public void setOffer_name(String offer_name) {
        this.offer_name = offer_name;
    }

    public String getOffer_time() {
        return offer_time;
    }

    public void setOffer_time(String offer_time) {
        this.offer_time = offer_time;
    }

    public String getOffer_des() {
        return offer_des;
    }

    public void setOffer_des(String offer_des) {
        this.offer_des = offer_des;
    }
}
