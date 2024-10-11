package org.example.backend.model;

import jakarta.persistence.*;

import java.sql.Time;
import java.time.LocalDateTime;

@Entity
@Table(name = "activities")
public class Activity {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "activity_id")
    private Long activity_id;

    @Column(name = "name",nullable = false, unique = true)
    private String activityName;

    @Column(name = "description",nullable = false)
    private String description;

    @Column(name = "weightlimit", nullable = false)
    private Long weightLimit;

    @Column(name = "agelimit", nullable = false)
    private Long ageLimit;

    @Column(name = "season", nullable = false)
    private String season;

    @Column(name = "materialname", nullable = false)
    private String materialName;

    @Column(name = "duration", nullable = false)
    private Time duration;




    public Activity(){

    }

    public Activity(String activityName, String description, Long weightLimit, Long ageLimit, String season, String materialName, Time duration) {
        this.activityName = activityName;
        this.description = description;
        this.weightLimit = weightLimit;
        this.ageLimit = ageLimit;
        this.season = season;
        this.materialName = materialName;
        this.duration = duration;
    }

    public Long getActivity_id() {
        return activity_id;
    }

    public String getActivityName() {
        return activityName;
    }

    public String getDescription() {
        return description;
    }

    public Long getWeightLimit() {
        return weightLimit;
    }

    public Long getAgeLimit() {
        return ageLimit;
    }

    public String getSeason() {
        return season;
    }

    public String getMaterialName() {
        return materialName;
    }

    public Time getDuration() {
        return duration;
    }

    public void setActivity_id(Long activity_id) {
        this.activity_id = activity_id;
    }

    public void setActivityName(String activityName) {
        this.activityName = activityName;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setWeightLimit(Long weightLimit) {
        this.weightLimit = weightLimit;
    }

    public void setAgeLimit(Long ageLimit) {
        this.ageLimit = ageLimit;
    }

    public void setSeason(String season) {
        this.season = season;
    }

    public void setMaterialName(String materialName) {
        this.materialName = materialName;
    }

    public void setDuration(Time duration) {
        this.duration = duration;
    }
}
