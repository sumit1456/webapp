-- V1: Baseline migration for all existing tables

CREATE TABLE IF NOT EXISTS region (
    region_id BIGSERIAL PRIMARY KEY,
    region_name VARCHAR(255) NOT NULL UNIQUE
);

CREATE TABLE IF NOT EXISTS exam_centre (
    centre_id BIGSERIAL PRIMARY KEY,
    centre_code VARCHAR(255) NOT NULL UNIQUE,
    centre_name VARCHAR(255),
    region_id BIGINT NOT NULL REFERENCES region(region_id)
);

CREATE INDEX IF NOT EXISTS idx_exam_centre_region_id ON exam_centre(region_id);

CREATE TABLE IF NOT EXISTS school (
    school_id BIGSERIAL PRIMARY KEY,
    school_name VARCHAR(255) NOT NULL,
    school_code VARCHAR(255),
    board_affiliation VARCHAR(255),
    medium_of_instruction VARCHAR(255),
    establishment_year INTEGER,
    principal_name VARCHAR(255),
    principal_contact_number VARCHAR(255),
    secondary_contact_number VARCHAR(255),
    official_email VARCHAR(255),
    website_url TEXT,
    seating_capacity INTEGER,
    number_of_classrooms INTEGER,
    cctv_available BOOLEAN,
    principal_signature_url TEXT,
    school_stamp_url TEXT,
    address_line1 VARCHAR(255),
    address_line2 VARCHAR(255),
    village_or_city VARCHAR(255),
    taluka VARCHAR(255),
    district VARCHAR(255),
    state VARCHAR(255),
    pincode VARCHAR(255),
    centre_id BIGINT NOT NULL REFERENCES exam_centre(centre_id)
);

CREATE INDEX IF NOT EXISTS idx_school_centre_id ON school(centre_id);

CREATE TABLE IF NOT EXISTS student (
    student_id BIGSERIAL PRIMARY KEY,
    first_name VARCHAR(255),
    middle_name VARCHAR(255),
    last_name VARCHAR(255),
    contact VARCHAR(255),
    email VARCHAR(255),
    password VARCHAR(255),
    age INTEGER,
    mother_tongue VARCHAR(255),
    has_profile BOOLEAN DEFAULT FALSE,
    school_id BIGINT NOT NULL REFERENCES school(school_id)
);

CREATE INDEX IF NOT EXISTS idx_student_school_id ON student(school_id);

CREATE TABLE IF NOT EXISTS student_profile (
    profile_id BIGSERIAL PRIMARY KEY,
    date_of_birth DATE,
    gender VARCHAR(255),
    category VARCHAR(255),
    previous_exam_name VARCHAR(255),
    previous_exam_year INTEGER,
    previous_exam_roll_no VARCHAR(255),
    previous_exam_marks DOUBLE PRECISION,
    father_name VARCHAR(255),
    mother_name VARCHAR(255),
    guardian_contact VARCHAR(255),
    qualification VARCHAR(255),
    profile_photo_url TEXT,
    signature_url TEXT,
    id_proof_document_url TEXT,
    id_proof_number VARCHAR(255),
    profile_completion_status VARCHAR(255),
    address_line1 VARCHAR(255),
    address_line2 VARCHAR(255),
    village_or_city VARCHAR(255),
    taluka VARCHAR(255),
    district VARCHAR(255),
    state VARCHAR(255),
    pincode VARCHAR(255),
    student_id BIGINT NOT NULL UNIQUE REFERENCES student(student_id)
);

CREATE TABLE IF NOT EXISTS exam (
    exam_no BIGSERIAL PRIMARY KEY,
    exam_name VARCHAR(255),
    no_of_papers INTEGER,
    exam_fees DOUBLE PRECISION,
    papers JSON,
    exam_details JSON,
    exam_code VARCHAR(255) NOT NULL UNIQUE,
    application_start_date DATE,
    application_end_date DATE,
    exam_start_date DATE,
    exam_end_date DATE,
    status VARCHAR(255),
    controller_signature_url TEXT,
    board_seal_url TEXT,
    board_logo_url TEXT
);

CREATE TABLE IF NOT EXISTS exam_application (
    application_id BIGSERIAL PRIMARY KEY,
    roll_no VARCHAR(255),
    centre_id BIGINT,
    is_hall_ticket_generated BOOLEAN DEFAULT FALSE,
    form_data JSON,
    status VARCHAR(255),
    exam_no BIGINT REFERENCES exam(exam_no),
    student_student_id BIGINT REFERENCES student(student_id)
);

CREATE TABLE IF NOT EXISTS exam_result (
    id BIGSERIAL PRIMARY KEY,
    result_data JSON,
    published_at TIMESTAMP,
    total_marks DOUBLE PRECISION,
    percentage DOUBLE PRECISION,
    application_id BIGINT NOT NULL UNIQUE REFERENCES exam_application(application_id)
);

CREATE TABLE IF NOT EXISTS admin (
    admin_id BIGSERIAL PRIMARY KEY,
    username VARCHAR(255),
    password VARCHAR(255)
);
