package org.example.backend.controller;

import org.example.backend.model.Activity;
import org.example.backend.service.ActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/activity")
public class ActivityController {

    @Autowired
    private ActivityService activityService;


    @GetMapping
    public List<Activity> getAllActivities() {
        return activityService.getAllActivities();
    }

    @PostMapping("/add")
    public ResponseEntity<String> addActivity(@RequestBody Activity activity){
        activityService.addActivity(activity);
        return ResponseEntity.status(HttpStatus.CREATED).body("Activity added successfully");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteActivity(@PathVariable Long id){
        activityService.deleteActivity(id);
        return ResponseEntity.ok("Activity deleted successfully");
    }






}
