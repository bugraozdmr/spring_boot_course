package com.grant.springcoredemo.common;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.stereotype.Component;

@Component
public class TennisCoach implements ICoach{
    public TennisCoach() {
        System.out.println("In constructor: " + getClass().getSimpleName());
    }

    // define our init method
    // sonra calisir basladiktan sonra yani
    @PostConstruct
    public void doMyStartupStuff() {
        System.out.println("In doMyStartupStuff: " + getClass().getSimpleName());
    }

    // define our destroy method
    // bitmeden once calisir mesela programı kapatma
    @PreDestroy
    public void doMyCleanupStuff() {
        System.out.println("In doMyCleanupStuff: " + getClass().getSimpleName());
    }


    @Override
    public String getDailyWorkout() {
        return "Spend 30 minutes in backhand volley";
    }
}
