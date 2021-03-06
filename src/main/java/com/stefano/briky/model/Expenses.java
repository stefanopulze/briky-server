package com.stefano.briky.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.stefano.briky.json.ExpenseJson;
import org.hibernate.annotations.LazyToOne;
import org.hibernate.annotations.LazyToOneOption;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Entity
public class Expenses {

    private int id;
    private Integer userId;
    private Double value;
    private Double latitude;
    private Double longitude;
    private Integer accuracy;
    private String description;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private Categories category;

    private List<Tags> tags = new ArrayList<>();

    public Expenses() {
    }

    public Expenses(ExpenseJson json) {
        value = json.getValue();
        latitude = json.getLatitude();
        longitude = json.getLongitude();
        accuracy = json.getAccuracy();
        description = json.getDescription();
        if (null == json.getCreatedAt()) {
            createdAt = LocalDateTime.now();
        } else {
            createdAt = json.getCreatedAt();
        }
        updatedAt = LocalDateTime.now();

        /*tags = json.getTags()
                .stream()
                .map(Tags::new)
                .collect(Collectors.toList());
        //.forEach(tag -> tag.setExpence(this));
        */
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "user_id", nullable = true, length = 45)
    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    @Basic
    @Column(name = "value", nullable = true, precision = 2)
    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    @Column(name = "latitude", nullable = true, precision = 8)
    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    @Basic
    @Column(name = "longitude", nullable = true, precision = 8)
    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    @Basic
    @Column(name = "accuracy", nullable = true)
    public Integer getAccuracy() {
        return accuracy;
    }

    public void setAccuracy(Integer accuracy) {
        this.accuracy = accuracy;
    }

    @Basic
    @Column(name = "description", nullable = true, length = 160)
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Basic
    @Column(name = "created_at", nullable = true)
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    @Basic
    @Column(name = "updated_at", nullable = true)
    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "expenses_tags",
            joinColumns = {@JoinColumn(name = "expense_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "tag_id", referencedColumnName = "id")}
    )
    public List<Tags> getTags() {
        return tags;
    }

    public void setTags(List<Tags> tags) {
        this.tags = tags;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id", referencedColumnName = "id")
    public Categories getCategory() {
        return category;
    }

    public void setCategory(Categories category) {
        this.category = category;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Expenses)) return false;

        Expenses expenses = (Expenses) o;

        return id == expenses.id;
    }

    @Override
    public int hashCode() {
        return id;
    }

    @Override
    public String toString() {
        return "Expenses{" +
                "id=" + id +
                ", userId='" + userId + '\'' +
                ", value=" + value +
                ", description='" + description + '\'' +
                ", createdAt=" + createdAt +
                '}';
    }
}
