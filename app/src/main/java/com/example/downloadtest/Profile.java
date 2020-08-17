package com.example.downloadtest;

public class Profile {
    private String date;
    private String day;
    private String name;
    private String photo;
    private String time;
    private String status;

    public Profile() {
    }

    public Profile(String date, String day, String name, String photo, String time, String status) {
        this.date = date;
        this.day = day;
        this.name = name;
        this.photo = photo;
        this.time = time;
        this.status = status;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getStatus(){
        return status;
    }

    public void setStatus(String status){
        this.status = status;
    }
}
