package com.example.uas_10119113;

public class Notes {
    private long id;
    private String title, body, category, date, time;

    public Notes(){}
    public Notes(String title, String body, String category, String date, String time)
    {
        this.title = title;
        this.body = body;
        this.category = category;
        this.date = date;
        this.time = time;
    }
    public Notes(long id, String title, String body, String category, String date, String time)
    {
        this.id = id;
        this.title = title;
        this.body = body;
        this.category = category;
        this.date = date;
        this.time = time;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
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

//NIM : 10119113
//Nama : Dafa Rizky Fahreza
//Kelas : IF3
