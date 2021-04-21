package com.magednan.forsale.models;

public class User {
    private String user_id;
    private String name;
    private String country;
    private String state_province;
    private String city;
    private String email;

    public User(String user_id, String name, String country, String state_province, String city, String email) {
        this.user_id = user_id;
        this.name = name;
        this.country = country;
        this.state_province = state_province;
        this.city = city;
        this.email = email;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getState_province() {
        return state_province;
    }

    public void setState_province(String state_province) {
        this.state_province = state_province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}
