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

    public Activity updateActivityName(Activity activity) {
    return activityRepository.updateActivityName(activity);
    }
    public Activity updateActivityDescription(Activity activity) {
    return activityRepository.updateActivityDescription(activity);
    }
    public Activity updateActivityWeightLimit(Activity activity) {
    return activityRepository.updateActivityWeightLimit(activity);
    }
    public Activity updateActivityAgeLimit(Activity activity) {
    return activityRepository.updateActivityAgeLimit(activity);
    }
    public Activity updateActivitySeason(Activity activity) {
    return activityRepository.updateActivitySeason(activity);
    }
    public Activity getActivityById(Long id) {
        return activityRepository.getActivityById(id);
    }



}
