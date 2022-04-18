package com.example.clevermindpob.Models;

import java.io.Serializable;

public class TrainingItem implements Serializable {
    private String title;
    private String days;
    private String time;
    private int image;
    private String courseInfo;
    private String price;

    public TrainingItem(String title, String days, String time, int image) {
        this.title = title;
        this.days = days;
        this.time = time;
        this.image = image;
    }

    public TrainingItem(String title, String days, String time, int image, String courseInfo, String price) {
        this.title = title;
        this.days = days;
        this.time = time;
        this.image = image;
        this.courseInfo = courseInfo;
        this.price = price;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDays() {
        return days;
    }

    public void setDays(String days) {
        this.days = days;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getCourseInfo() {
        return courseInfo;
    }

    public void setCourseInfo(String courseInfo) {
        this.courseInfo = courseInfo;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
