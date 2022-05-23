# practicePostgreSQL

CREATE DATABASE students_repository;
-- DROP TABLE students; 
CREATE TABLE students
(
    id BIGSERIAL PRIMARY KEY ,
    first_name  VARCHAR(20),
    middle_name VARCHAR(20),
    last_name   VARCHAR(20),
    age         SMALLINT
);

