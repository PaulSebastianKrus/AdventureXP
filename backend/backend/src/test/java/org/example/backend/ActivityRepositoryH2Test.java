package org.example.backend;

import org.example.backend.model.Activity;
import org.example.backend.repository.ActivityRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("h2")
@Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD, scripts = "classpath:h2init.sql")
public class ActivityRepositoryH2Test {

    @Autowired
    private ActivityRepository repository;

    @Test
    public void addActivity() {
        Activity newActivity = new Activity();
        newActivity.setActivityName("testActivity");
        newActivity.setDescription("testDescription");
        newActivity.setWeightLimit(11L);
        newActivity.setAgeLimit(31L);
        newActivity.setSeason("sommer");

        repository.addActivity(newActivity);

        Activity retrievedActivity = repository.getActivityById(1L);

        assertEquals("testActivity", retrievedActivity.getActivityName());
    }
}