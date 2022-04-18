package com.example.clevermindpob.Models;

public class JobItem {
    private String title;
    private String experience;
    private int image;

    public JobItem(String title, String experience, int image) {
        this.title = title;
        this.experience = experience;
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getExperience() {
        return experience;
    }

    public void setExperience(String experience) {
        this.experience = experience;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }
}
