package com.stefano.briky.json;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ExpenceJson {

    private int id;
    private double value;
    private double latitude;
    private double longitude;
    private int accuracy;
    private String description;
    private Date createdAt;
    private Date updatedAt;

    private List<TagJson> tags = new ArrayList<>();

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

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public List<TagJson> getTags() {
        return tags;
    }

    public void setTags(List<TagJson> tags) {
        this.tags = tags;
    }

}
