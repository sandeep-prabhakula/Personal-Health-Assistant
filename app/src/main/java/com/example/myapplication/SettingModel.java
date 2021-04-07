package com.example.myapplication;

public class SettingModel {
    private final int img;
    private final String name;

    public SettingModel(int img, String name) {
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
