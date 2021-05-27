package com.example.myapplication;

public class CovidDataModel {
    private final String state;
    private final String count;
    private final String recover;
    private final String deaths;


    public CovidDataModel(String state, String count, String recover, String deaths) {
        this.state = state;
        this.count = count;
        this.recover = recover;
        this.deaths = deaths;
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
