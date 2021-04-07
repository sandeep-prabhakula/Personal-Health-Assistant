package com.example.myapplication;

public class FitnessModel {
    private final int img;
    private final String name;

    public FitnessModel(int img, String name) {
        this.img = img;
        this.name = name;
    }

    public int getImg() {
        return img;
    }

    public String getName() {
        return name;
    }
}
