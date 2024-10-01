package org.example.backend.repository;

import org.example.backend.model.Activity;
import org.springframework.stereotype.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;


@Repository
public class ActivityRepository {


@Autowired
private JdbcTemplate jdbcTemplate;

public ActivityRepository(JdbcTemplate jdbcTemplate){
    this.jdbcTemplate = jdbcTemplate;
}

    public void addActivity(Activity activity, Long activity_id, String name, String description, Long weightLimit, Long ageLimit, String season) {
        String insertQuery = "INSERT INTO activities (activity_id, name, description, weightlimit, agelimit, season) VALUES (?, ?, ?, ?, ?, ?)";
    }


    public void deleteActivity(Long activity_id) {
        String query = "DELETE FROM activities WHERE activity_id = ?";
        jdbcTemplate.update(query, activity_id);
    }

    public List<Activity> getAllActivities() {
        String query = "SELECT * FROM activities";
        return jdbcTemplate.query(query, (rs, rowNum) -> {
            Activity activity = new Activity();
            activity.setActivity_id(rs.getLong("activity_id"));
            activity.setActivityName(rs.getString("name"));
            activity.setDescription(rs.getString("description"));
            activity.setWeightLimit(rs.getLong("weightlimit"));
            activity.setAgeLimit(rs.getLong("agelimit"));
            activity.setSeason(rs.getString("season"));
            return activity;
        });
    }


}
