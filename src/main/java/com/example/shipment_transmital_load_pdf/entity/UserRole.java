package com.example.shipment_transmital_load_pdf.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serial;
import java.io.Serializable;

@Entity
@Table(name = "user_role")
@Data
public class UserRole implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    @Id()
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "role_id", nullable = false)
    private int id;
    @Column(name = "role_name", nullable = false)
    private String role;

}
