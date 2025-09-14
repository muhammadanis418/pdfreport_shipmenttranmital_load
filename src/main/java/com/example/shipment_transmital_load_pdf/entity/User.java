package com.example.shipment_transmital_load_pdf.entity;


import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "users")
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id", nullable = false, unique = true)
    private int id;
    @Column(name = "user_name",nullable = false)
    private String userName;
    @Column(name = "user_email", nullable = false, unique = true)
    private String email;
    @Column(name="user_role")
    private String role;

}
