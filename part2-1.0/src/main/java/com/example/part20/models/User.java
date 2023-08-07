package com.example.part20.models;


public class User {
    private String username;
private int id;
    private String password;
    private String rank;

    public User(  String username, String password, String rank) {
        this.username = username;
        this.password = password;
        this.rank = rank;
    }

    // Getters and Setters for attributes


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }


    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRank() {
        return rank;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }
}
