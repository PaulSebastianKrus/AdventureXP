DROP TABLE IF EXISTS activities;
CREATE TABLE activities
(
    activity_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name        VARCHAR(50)  NOT NULL UNIQUE,
    description TEXT,
    weightlimit BIGINT       NOT NULL,
    agelimit    BIGINT       NOT NULL,
    season      VARCHAR(100) NOT NULL
);
INSERT INTO activities (activity_id, name, description, weightlimit, agelimit, season)
VALUES (1, 'testActivity1', 'testDescription1', 10, 18, 'testSommer1'),
       (2, 'testActivity2', 'testDescription2', 11, 19, 'testWinter2');