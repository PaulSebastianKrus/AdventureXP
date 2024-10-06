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

    @GetMapping("/{id}")
    public ResponseEntity<Activity> getActivityById(@PathVariable Long id) {
        Activity activity = activityService.getActivityById(id);
        return ResponseEntity.ok(activity);
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

    @PostMapping("/{id}")
    public ResponseEntity<String> updateActivity(@PathVariable Long id, @RequestBody Activity newActivity) {
        Activity oldActivity = activityService.getActivityById(id);

        if (oldActivity == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Activity not found");
        }

        // Only update fields that are not null
        if (newActivity.getActivityName() != null) {
            oldActivity.setActivityName(newActivity.getActivityName());
        }
        if (newActivity.getDescription() != null) {
            oldActivity.setDescription(newActivity.getDescription());
        }
        if (newActivity.getWeightLimit() != null) {
            oldActivity.setWeightLimit(newActivity.getWeightLimit());
        }
        if (newActivity.getAgeLimit() != null) {
            oldActivity.setAgeLimit(newActivity.getAgeLimit());
        }
        if (newActivity.getSeason() != null) {
            oldActivity.setSeason(newActivity.getSeason());
        }
        if (newActivity.getMaterialName() != null) {
            oldActivity.setMaterialName(newActivity.getMaterialName());
        }
        if (newActivity.getAmount() != null) {
            oldActivity.setAmount(newActivity.getAmount());
        }


        activityService.updateActivity(oldActivity);
        return ResponseEntity.ok("Activity updated");
    }






}
