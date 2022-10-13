package com.example.ordermanagement.entities;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Size;

@Entity
@Data
@Table(name = "customers")
public class CustomerEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "fullname", nullable = false)
    @Size (max = 255)
    private String fullName;

    @Column(name = "registrationcode")
    private Integer registrationCode;

    @Column(name = "email", nullable = false)
    @Size (max = 255)
    private String email;

    @Column(name = "telephone", nullable = false)
    @Size (max = 255)
    private String telephone;

}