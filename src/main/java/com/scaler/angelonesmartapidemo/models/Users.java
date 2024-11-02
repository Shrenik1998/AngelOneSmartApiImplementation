package com.scaler.angelonesmartapidemo.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "users")  // Map to a specific table name (if needed)
public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "user_name")  // Custom column name in the table
    private String userName;

    @Column(name = "password")  // This can be customized too
    private String password;

    @Column(name = "role")  // Default is to use the field name
    private String role;
}

