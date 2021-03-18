package com.example.flexiload_app;

public class Coin_Model {
    String email,number,coin;

    public Coin_Model() {
    }

    public Coin_Model(String email, String number, String coin) {
        this.email = email;
        this.number = number;
        this.coin = coin;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getCoin() {
        return coin;
    }

    public void setCoin(String coin) {
        this.coin = coin;
    }
}
