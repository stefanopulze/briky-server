package com.stefano.briky.json;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ExpenseJson {

    private int id;
    private double value;
    private double latitude;
    private double longitude;
    private int accuracy;
    private String description;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    private List<TagJson> tags = new ArrayList<>();

    public ExpenseJson() {
    }

    public ExpenseJson(Double amount, String tag) {
        value = amount;
        tags.add(new TagJson(tag));
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public int getAccuracy() {
        return accuracy;
    }

    public void setAccuracy(int accuracy) {
        this.accuracy = accuracy;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public List<TagJson> getTags() {
        return tags;
    }

    public void setTags(List<TagJson> tags) {
        this.tags = tags;
    }

}
