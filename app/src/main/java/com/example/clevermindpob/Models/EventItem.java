package com.example.clevermindpob.Models;

public class EventItem {
    private String title;
    private String day;
    private String date;
    private String time;

    public EventItem(String title, String day, String date, String time) {
        this.title = title;
        this.day = day;
        this.date = date;
        this.time = time;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
