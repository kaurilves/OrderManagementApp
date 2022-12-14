package com.example.ordermanagement.entities;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Size;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "customer")
public class CustomerEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "fullname", nullable = false)
    @Size (max = 255)
    private String fullName;

    @Column(name = "registrationcode")
    private String registrationCode;

    @Column(name = "email", nullable = false)
    @Size (max = 255)
    private String email;

    @Column(name = "telephone", nullable = false)
    @Size (max = 255)
    private String telephone;
}