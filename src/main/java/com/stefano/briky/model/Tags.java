package com.stefano.briky.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.stefano.briky.json.TagJson;
import org.hibernate.annotations.LazyToOne;
import org.hibernate.annotations.LazyToOneOption;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Tags {
    private int id;
    private String name;
    private String slug;
    private int userId;

    private Users user;
    private List<Expenses> expenses = new ArrayList<>();

    public Tags() {
    }

    public Tags(TagJson json) {
        id = json.getId();
        name = json.getName();
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
    @Column(name = "name", nullable = false, length = 45)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @JsonIgnore
    @Column(name = "user_id")
    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @JsonIgnore
    @LazyToOne(LazyToOneOption.PROXY)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false, insertable = false, updatable = false)
    public Users getUser() {
        return user;
    }

    public void setUser(Users user) {
        this.user = user;

        if(user != null) {
            this.userId = user.getId();
        }
    }

    @JsonIgnore
    @ManyToMany(mappedBy = "tags", cascade = CascadeType.ALL)
    public List<Expenses> getExpenses() {
        return expenses;
    }

    public void setExpenses(List<Expenses> expenses) {
        this.expenses = expenses;
    }

    public void setExpence(Expenses expence) {
        this.expenses.add(expence);
    }

    @Column(name = "slug", length = 45)
    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Tags)) return false;

        Tags tags = (Tags) o;

        return id == tags.id;
    }

    @Override
    public int hashCode() {
        return id;
    }

    @Override
    public String toString() {
        return "Tags{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }


}
