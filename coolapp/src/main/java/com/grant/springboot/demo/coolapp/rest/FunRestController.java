package com.grant.springboot.demo.coolapp.rest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FunRestController {
    @Value("${team.name}")
    private String teamName;

    @Value("${coach.name}")
    private String coachName;

    // expose team info endpoint

    @GetMapping("/team-info")
    public String getTeamInfo() {
        return "Coach: " + coachName + "<br>Team Name: " + teamName;
    }

    // expose "/" than return "Hello World"

    @GetMapping("/")
    public String sayHello(){
        return "Hello World";
    }

    // expose a new endpoint for "workout"
    @GetMapping("/workout")
    public String getDailyWorkout(){
        return "Work every day";
    }
}
