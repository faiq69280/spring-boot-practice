package com.example;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import java.sql.Timestamp;
import org.springframework.cglib.core.Local;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "users")
public class User  {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String name;

    String password;

    String salt;

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public User() {
    }

}
