package com.grant.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data // Getter, Setter, toString, equals, hashCode
@NoArgsConstructor
@AllArgsConstructor
public class Student {
    private String firstName;
    private String lastName;
}