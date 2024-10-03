package org.example.backend;

import org.example.backend.model.Activity;
import org.example.backend.repository.ActivityRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;

@SpringBootTest
@ActiveProfiles("h2")
@Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD, scripts = "classpath:h2init.sql")
public class ActivityRepositoryH2Test {

    @Autowired
    ActivityRepository repository;

    @Test
    void addActivity(){


        Activity newActivity = new Activity();
        newActivity.setActivityName("testActivity");
        newActivity.setWeightLimit("testWeight");
        newActivity.setDescription("testDescription");
        newActivity.setAgeLimit("testDescription");




    }








}
