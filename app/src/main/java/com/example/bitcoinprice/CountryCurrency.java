package com.example.bitcoinprice;

public class CountryCurrency {

    private String mCurrencyCode;

    public CountryCurrency(String Code){
        this.mCurrencyCode=Code;
    }

    public String getCurrencyCode(){
        return mCurrencyCode;
    }
}
