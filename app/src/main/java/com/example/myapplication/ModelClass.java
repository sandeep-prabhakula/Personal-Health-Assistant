package com.example.myapplication;

public class ModelClass {
    private final int image;
    private final String name;

    public ModelClass(int image, String name) {
        this.image = image;
        this.name = name;
    }

    public int getImage() {
        return image;
    }

    public String getName() {
        return name;
    }
}
