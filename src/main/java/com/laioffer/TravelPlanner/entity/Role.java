package com.laioffer.TravelPlanner.entity;

import javax.persistence.Entity;
import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="role")
public class Role implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name="email")
    private String email;

    @Column(name="role")
    private String role;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
