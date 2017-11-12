package com.stefano.briky.model;

import org.hibernate.annotations.LazyToOne;
import org.hibernate.annotations.LazyToOneOption;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Tokens {
    private int id;
    private String value;
    private int userId;
    private Date createdAt;

    private Users user;

    @Id
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "value", nullable = false, length = 160)
    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Basic
    @Column(name = "user_id", nullable = false)
    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Basic
    @Column(name = "created_at", nullable = true)
    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    @LazyToOne(LazyToOneOption.PROXY)
    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id", insertable = false, nullable = false, updatable = false)
    public Users getUser() {
        return user;
    }

    public void setUser(Users user) {
        this.user = user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Tokens tokens = (Tokens) o;

        if (id != tokens.id) return false;
        if (userId != tokens.userId) return false;
        if (value != null ? !value.equals(tokens.value) : tokens.value != null) return false;
        if (createdAt != null ? !createdAt.equals(tokens.createdAt) : tokens.createdAt != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (value != null ? value.hashCode() : 0);
        result = 31 * result + userId;
        result = 31 * result + (createdAt != null ? createdAt.hashCode() : 0);
        return result;
    }
}
