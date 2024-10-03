DROP TABLE IF EXISTS activities;
CREATE TABLE activities (
                            activity_id BIGINT AUTO_INCREMENT PRIMARY KEY,
                            name VARCHAR(50) NOT NULL UNIQUE,
                            description TEXT,
                            weightlimit BIGINT NOT NULL,
                            agelimit BIGINT NOT NULL,
                            season VARCHAR(100) NOT NULL
);