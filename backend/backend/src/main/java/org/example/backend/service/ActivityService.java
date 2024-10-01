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

public void addActivity(Activity activity, Long activity_id, String name, String description, Long weightLimit, Long ageLimit, String season) {
    activityRepository.addActivity(activity, activity_id, name, description,weightLimit, ageLimit, season);
}

public void deleteActivity(Long activity_id) {
    activityRepository.deleteActivity(activity_id);
}

    public List<Activity> getAllActivities() {
        return activityRepository.getAllActivities();
    }

}
