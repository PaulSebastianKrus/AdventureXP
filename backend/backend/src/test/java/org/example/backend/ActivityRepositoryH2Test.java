package org.example.backend;

import org.example.backend.model.Activity;
import org.example.backend.repository.ActivityRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.*;

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

        Activity retrievedActivity = repository.getActivityById(3L);

        assertEquals("testActivity", retrievedActivity.getActivityName());
    }


    @Test
    public void editActivity(){
        Activity existingActivity = repository.getActivityById(1L);

        assertNotNull(existingActivity);

        existingActivity.setActivityName("updatedActivity");
        existingActivity.setDescription("updatedDescription");
        existingActivity.setWeightLimit(70L);
        existingActivity.setAgeLimit(18L);
        existingActivity.setSeason("updatedSeason");

        Activity updatedActivity = repository.updateActivity(existingActivity);

        assertNotNull(updatedActivity);
        assertEquals("updatedActivity", updatedActivity.getActivityName());
        assertEquals("updatedDescription", updatedActivity.getDescription());
        assertEquals(70L, updatedActivity.getWeightLimit());
        assertEquals(18L, updatedActivity.getAgeLimit());
        assertEquals("updatedSeason", updatedActivity.getSeason());

    }

    @Test
    public void deleteActivity(){

        Activity existingActivity = repository.getActivityById(2L);

        assertNotNull(existingActivity);

        repository.deleteActivity(existingActivity.getActivity_id());

        Activity deletedActivity = null;
        try {
            deletedActivity = repository.getActivityById(existingActivity.getActivity_id());
        } catch (EmptyResultDataAccessException e) {
        }

        assertNull(deletedActivity);




    }









}