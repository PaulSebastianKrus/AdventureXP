package org.example.backend.controller;

import org.example.backend.model.Activity;
import org.example.backend.service.ActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ActivityController {

    @Autowired
    private ActivityService activityService;


    @GetMapping("/api/activity")
    public ResponseEntity<List<Activity>> getActivities() {
        // Assuming Activity is a model class representing an activity
        List<Activity> activities = activityService.getAllActivities();

        // Return the list of activities with a 200 OK status
        return ResponseEntity.ok(activities);
    }





}
