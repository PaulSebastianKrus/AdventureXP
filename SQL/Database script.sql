CREATE DATABASE IF NOT EXISTS adventurexp_db;


CREATE TABLE IF NOT EXISTS activities ( 
    activity_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(50) NOT NULL UNIQUE,
    description TEXT, 
    weightlimit BIGINT NOT NULL,
    agelimit BIGINT NOT NULL,
    season VARCHAR(100) NOT NULL,
    materialname VARCHAR(50) NOT NULL,
    duration TIME NOT NULL
);


CREATE TABLE IF NOT EXISTS bookings ( 
    booking_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    activityname VARCHAR(255) NOT NULL,
    date DATETIME NOT NULL,
    people BIGINT NOT NULL,
    instructor VARCHAR(50),
    personname VARCHAR(50),
    phonenumber BIGINT NOT NULL UNIQUE,
    FOREIGN KEY (activityname) REFERENCES activities(name) ON UPDATE CASCADE
);

CREATE TABLE IF NOT EXISTS tshirts (
shirt_id BIGINT AUTO_INCREMENT PRIMARY KEY,
shirtname VARCHAR(50) NOT NULL,
shirtsize VARCHAR(10) NOT NULL,
price BIGINT
);

CREATE TABLE IF NOT EXISTS snacks (
snack_id BIGINT AUTO_INCREMENT PRIMARY KEY,
snackname VARCHAR(50) NOT NULL,
snacksize VARCHAR(10) NOT NULL,
price BIGINT
);

CREATE TABLE employees (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    role VARCHAR(50) NOT NULL, -- "Instructor" or "Admin"
    activities VARCHAR(75) NOT NULL
);