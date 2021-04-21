package com.magednan.forsale.models;

import java.io.Serializable;

public class AdPost  implements Serializable {
    private String userId;
    private String imageUrl;
    private String title;
    private String category;
    private String description;
    private float price;
    private String country;
    private String state_province;
    private String city;
    private String email;

    public AdPost() {
    }

    public AdPost(String userId, String imageUrl, String title, String category, String description, float price, String country,
                  String state_province, String city, String email) {
        this.userId = userId;
        this.imageUrl = imageUrl;
        this.title = title;
        this.category = category;
        this.description = description;
        this.price = price;
        this.country = country;
        this.state_province = state_province;
        this.city = city;
        this.email = email;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
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
