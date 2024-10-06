package org.example.backend.model;

import jakarta.persistence.*;

@Entity
@Table(name = "activities")
public class Activity {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "activity_id")
    private Long activity_id;

    @Column(name = "name",nullable = false, unique = true)
    public String activityName;

    @Column(name = "description",nullable = false)
    public String description;

    @Column(name = "weightlimit", nullable = false)
    public Long weightLimit;

    @Column(name = "agelimit", nullable = false)
    public Long ageLimit;

    @Column(name = "season", nullable = false)
    public String season;

    @Column(name = "materialname", nullable = false)
    public String materialName;

    @Column(name = "amount", nullable = false)
    public Long amount;






    public Activity(){

    }

    public Activity(String activityName, String description, Long weightLimit, Long ageLimit, String season, String materialName, Long amount ) {
        this.activityName = activityName;
        this.description = description;
        this.weightLimit = weightLimit;
        this.ageLimit = ageLimit;
        this.season = season;
        this.materialName = materialName;
        this.amount = amount;
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

    public Long getAmount() {
        return amount;
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

    public void setAmount(Long amount) {
        this.amount = amount;
    }
}
