package com.example.mvvmtodo.model;

public class ToDo {
    private int id;
    private String title;
    private String description;

    public ToDo(int id, String title, String description) {
        this.id = id;
        this.title = title;
        this.description = description;
    }

    public ToDo(String title, String description) {
        this.title = title;
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
