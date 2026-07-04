-- V2: Create exam_officer table

CREATE TABLE IF NOT EXISTS exam_officer (
    exam_officer_id BIGSERIAL PRIMARY KEY,
    username VARCHAR(255) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    name VARCHAR(255)
);
