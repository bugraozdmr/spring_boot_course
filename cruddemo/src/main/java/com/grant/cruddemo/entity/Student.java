package com.grant.cruddemo.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

// LOMBOK

@Entity
@Table(name = "student")
@Data
@NoArgsConstructor // Parametresiz constructor
@RequiredArgsConstructor // Sadece final veya @NonNull alanlar için constructor
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, nullable = false)
    private int id; // MySQL otomatik olarak bu alanı dolduracak

    @NonNull
    @Column(name = "first_name")
    private String firstName;

    @NonNull
    @Column(name = "last_name")
    private String lastName;

    @NonNull
    @Column(name = "email")
    private String email;
}
