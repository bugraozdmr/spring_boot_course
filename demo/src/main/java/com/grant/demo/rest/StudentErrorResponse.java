package com.grant.demo.rest;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data // Getter, Setter, toString, equals, hashCode
@NoArgsConstructor
@AllArgsConstructor
public class StudentErrorResponse {
    private int status;
    private String message;
    private long timeStamp;
}
