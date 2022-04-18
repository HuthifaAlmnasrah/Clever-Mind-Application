package com.example.clevermindpob.Models;

public class LatestNewsItem {
    private String title;
    private String shortDesc;
    private String longDesc;
    private int image;

    public LatestNewsItem(String title, String shortDesc, String longDesc, int image) {
        this.title = title;
        this.shortDesc = shortDesc;
        this.longDesc = longDesc;
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getShortDesc() {
        return shortDesc;
    }

    public void setShortDesc(String shortDesc) {
        this.shortDesc = shortDesc;
    }

    public String getLongDesc() {
        return longDesc;
    }

    public void setLongDesc(String longDesc) {
        this.longDesc = longDesc;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }
}
