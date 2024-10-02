package org.example.backend.service;

import org.example.backend.model.Activity;
import org.example.backend.repository.ActivityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ActivityService {

    @Autowired
    private ActivityRepository activityRepository;

    public void addActivity(Activity activity) {
        activityRepository.addActivity(activity);
    }

public void deleteActivity(Long activity_id) {
    activityRepository.deleteActivity(activity_id);
}

    public List<Activity> getAllActivities() {
        return activityRepository.getAllActivities();
    }

    public Activity updateActivity(Activity activity) {
    return activityRepository.updateActivity(activity);
    }



}
