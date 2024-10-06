package org.example.backend.repository;

import org.example.backend.model.Activity;
import org.springframework.stereotype.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.ArrayList;
import java.util.List;


@Repository
public class ActivityRepository {


@Autowired
private JdbcTemplate jdbcTemplate;

public ActivityRepository(JdbcTemplate jdbcTemplate){
    this.jdbcTemplate = jdbcTemplate;
}

    public void addActivity(Activity activity) {
        String insertQuery = "INSERT INTO activities (name, description, weightlimit, agelimit, season, materialname, amount) VALUES (?, ?, ?, ?, ?,?,?)";
        jdbcTemplate.update(insertQuery, activity.getActivityName(), activity.getDescription(), activity.getWeightLimit(), activity.getAgeLimit(), activity.getSeason(),activity.getMaterialName(),activity.getAmount());
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
            activity.setMaterialName(rs.getString("materialname"));
            activity.setAmount(rs.getLong("amount"));

            return activity;
        });
    }

    public Activity getActivityById(Long id) {
        String query = "SELECT * FROM activities WHERE activity_id = ?";
        return jdbcTemplate.queryForObject(query, new Object[]{id}, (rs, rowNum) -> {
            Activity activity = new Activity();
            activity.setActivity_id(rs.getLong("activity_id"));
            activity.setActivityName(rs.getString("name"));
            activity.setDescription(rs.getString("description"));
            activity.setWeightLimit(rs.getLong("weightlimit"));
            activity.setAgeLimit(rs.getLong("agelimit"));
            activity.setSeason(rs.getString("season"));
            activity.setMaterialName(rs.getString("materialname"));
            activity.setAmount(rs.getLong("amount"));

            return activity;
        });
    }


    public Activity updateActivityName(Activity activity) {
        String query = "UPDATE activities SET name = ?, WHERE = activity_id";
        int rowsUpdated = jdbcTemplate.update(query, activity.getActivityName(), activity.getActivity_id());
        if (rowsUpdated> 0) {
            return activity;
        }
        return null;
    }

    public Activity updateActivityDescription(Activity activity) {
        String query = "UPDATE activities SET description = ?, WHERE = activity_id";
        int rowsUpdated = jdbcTemplate.update(query, activity.getDescription(), activity.getActivity_id());
        if (rowsUpdated> 0) {
            return activity;
        }
        return null;
    }

    public Activity updateActivityWeightLimit(Activity activity) {
        String query = "UPDATE activities SET weightlimit = ?, WHERE = activity_id";
        int rowsUpdated = jdbcTemplate.update(query, activity.getWeightLimit(), activity.getActivity_id());
        if (rowsUpdated> 0) {
            return activity;
        }
        return null;
    }
    public Activity updateActivityAgeLimit(Activity activity) {
        String query = "UPDATE activities SET agelimit = ?, WHERE = activity_id";
        int rowsUpdated = jdbcTemplate.update(query, activity.getAgeLimit(), activity.getActivity_id());
        if (rowsUpdated> 0) {
            return activity;
        }
        return null;
    }
    public Activity updateActivitySeason(Activity activity) {
        String query = "UPDATE activities SET season = ?, WHERE = activity_id";
        int rowsUpdated = jdbcTemplate.update(query, activity.getSeason(), activity.getActivity_id());
        if (rowsUpdated> 0) {
            return activity;
        }
        return null;
    }



    public Activity updateActivity(Activity activity) {
        StringBuilder query = new StringBuilder("UPDATE activities SET ");
        List<Object> parameters = new ArrayList<>();

        if (activity.getActivityName() != null) {
            query.append("name = ?, ");
            parameters.add(activity.getActivityName());
        }
        if (activity.getDescription() != null) {
            query.append("description = ?, ");
            parameters.add(activity.getDescription());
        }
        if (activity.getWeightLimit() != null) {
            query.append("weightlimit = ?, ");
            parameters.add(activity.getWeightLimit());
        }
        if (activity.getAgeLimit() != null) {
            query.append("agelimit = ?, ");
            parameters.add(activity.getAgeLimit());
        }
        if (activity.getSeason() != null) {
            query.append("season = ?, ");
            parameters.add(activity.getSeason());
        }
        if(activity.getMaterialName()!=null){
            query.append("materialname = ?, ");
            parameters.add(activity.getMaterialName());
        }
         if(activity.getAmount()!=null){
            query.append("amount = ?, ");
            parameters.add(activity.getAmount());
        }



        if (!parameters.isEmpty()) {
            query.setLength(query.length() - 2); // Remove last comma and space
            query.append(" WHERE activity_id = ?");
            parameters.add(activity.getActivity_id());

            int rowsUpdated = jdbcTemplate.update(query.toString(), parameters.toArray());
            if (rowsUpdated > 0) {
                return activity;
            }
        }
        return null;
    }



}
