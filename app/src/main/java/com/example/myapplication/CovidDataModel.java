package com.example.myapplication;

public class CovidDataModel {
    private final String state;
    private final String count;
    private final String recover;
    private final String deaths;
    private final String symbol;
    private final String flag;

    public CovidDataModel(String state, String count, String recover, String deaths,String symbol,String flag) {
        this.state = state;
        this.count = count;
        this.recover = recover;
        this.deaths = deaths;
        this.symbol = symbol;
        this.flag = flag;
    }

    public String getSymbol() {
        return symbol;
    }

    public String getFlag() {
        return flag;
    }

    public String getState() {
        return state;
    }

    public String getCount() { return count; }

    public String getRecover() {
        return recover;
    }

    public String getDeaths() {
        return deaths;
    }
}
