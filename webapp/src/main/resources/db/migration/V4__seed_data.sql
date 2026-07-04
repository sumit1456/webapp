-- V4: Seed initial data (regions, exam centres, schools, students, exam officers)

-- ============================================
-- 1. SEED REGIONS
-- ============================================
WITH region_insert AS (
    INSERT INTO region (region_name) VALUES
        ('Pune Region'),
        ('Mumbai Region'),
        ('Nagpur Region'),
        ('Nashik Region')
    ON CONFLICT (region_name) DO UPDATE SET region_name = EXCLUDED.region_name
    RETURNING region_id, region_name
),
-- ============================================
-- 2. SEED EXAM CENTRES (2 per region)
-- ============================================
centre_insert AS (
    INSERT INTO exam_centre (centre_code, centre_name, region_id)
    SELECT centre_code, centre_name, region_id
    FROM (VALUES
        ('EC-PUNE-01', 'Pune Central Centre', 'Pune Region'),
        ('EC-PUNE-02', 'Pune East Centre', 'Pune Region'),
        ('EC-MUM-01', 'Mumbai South Centre', 'Mumbai Region'),
        ('EC-MUM-02', 'Mumbai North Centre', 'Mumbai Region'),
        ('EC-NAG-01', 'Nagpur City Centre', 'Nagpur Region'),
        ('EC-NAG-02', 'Nagpur West Centre', 'Nagpur Region'),
        ('EC-NSK-01', 'Nashik Central Centre', 'Nashik Region'),
        ('EC-NSK-02', 'Nashik Road Centre', 'Nashik Region')
    ) AS v(centre_code, centre_name, region_name)
    JOIN region_insert r ON r.region_name = v.region_name
    ON CONFLICT (centre_code) DO UPDATE SET centre_name = EXCLUDED.centre_name
    RETURNING centre_id, centre_code
),
-- ============================================
-- 3. SEED SCHOOLS (5 per exam centre = 40 total)
-- ============================================
school_insert AS (
    INSERT INTO school (
        school_name, school_code, board_affiliation, medium_of_instruction,
        establishment_year, principal_name, principal_contact_number,
        official_email, seating_capacity, number_of_classrooms, cctv_available,
        address_line1, address_line2, village_or_city, taluka, district, state, pincode,
        centre_id
    )
    SELECT
        school_name, school_code, board_affiliation, medium_of_instruction,
        establishment_year, principal_name, principal_contact_number,
        official_email, seating_capacity, number_of_classrooms, cctv_available,
        address_line1, address_line2, village_or_city, taluka, district, state, pincode,
        centre_id
    FROM (VALUES
        -- Pune Central Centre schools
        ('Pune Central Vidyalaya 1', 'SCH-PUNE-1-1', 'CBSE', 'English', 1996, 'Mr. Rahul Joshi', '9820011100', 'info@sch_pune_1_1.edu.in', 265, 13, true, 'Plot No 10, Main Road', 'Near Pune Central Station', 'Pune Central', 'Pune Central', 'Pune Central District', 'Maharashtra', '4110011', 'EC-PUNE-01'),
        ('Pune Central High School 2', 'SCH-PUNE-1-2', 'State Board', 'Marathi', 1997, 'Mr. Sneha Patil', '9820011200', 'info@sch_pune_1_2.edu.in', 280, 14, true, 'Plot No 20, Main Road', 'Near Pune Central Station', 'Pune Central', 'Pune Central', 'Pune Central District', 'Maharashtra', '4110012', 'EC-PUNE-01'),
        ('Pune Central Academy 3', 'SCH-PUNE-1-3', 'CBSE', 'English', 1998, 'Mr. Aditya Deshmukh', '9820011300', 'info@sch_pune_1_3.edu.in', 295, 15, true, 'Plot No 30, Main Road', 'Near Pune Central Station', 'Pune Central', 'Pune Central', 'Pune Central District', 'Maharashtra', '4110013', 'EC-PUNE-01'),
        ('Pune Central Public School 4', 'SCH-PUNE-1-4', 'State Board', 'English', 1999, 'Mr. Pooja Kulkarni', '9820011400', 'info@sch_pune_1_4.edu.in', 310, 16, true, 'Plot No 40, Main Road', 'Near Pune Central Station', 'Pune Central', 'Pune Central', 'Pune Central District', 'Maharashtra', '4110014', 'EC-PUNE-01'),
        ('Pune Central English Medium School 5', 'SCH-PUNE-1-5', 'CBSE', 'English', 2000, 'Mr. Rohan Chavan', '9820011500', 'info@sch_pune_1_5.edu.in', 325, 17, true, 'Plot No 50, Main Road', 'Near Pune Central Station', 'Pune Central', 'Pune Central', 'Pune Central District', 'Maharashtra', '4110015', 'EC-PUNE-01'),
        -- Pune East Centre schools
        ('Pune East Vidyalaya 1', 'SCH-PUNE-2-1', 'State Board', 'English', 2001, 'Mr. Sayali Shinde', '9820012100', 'info@sch_pune_2_1.edu.in', 265, 13, true, 'Plot No 10, Main Road', 'Near Pune East Station', 'Pune East', 'Pune East', 'Pune East District', 'Maharashtra', '4110021', 'EC-PUNE-02'),
        ('Pune East High School 2', 'SCH-PUNE-2-2', 'CBSE', 'Marathi', 2002, 'Mr. Amit Pawar', '9820012200', 'info@sch_pune_2_2.edu.in', 280, 14, true, 'Plot No 20, Main Road', 'Near Pune East Station', 'Pune East', 'Pune East', 'Pune East District', 'Maharashtra', '4110022', 'EC-PUNE-02'),
        ('Pune East Academy 3', 'SCH-PUNE-2-3', 'State Board', 'English', 2003, 'Mr. Tejashree More', '9820012300', 'info@sch_pune_2_3.edu.in', 295, 15, true, 'Plot No 30, Main Road', 'Near Pune East Station', 'Pune East', 'Pune East', 'Pune East District', 'Maharashtra', '4110023', 'EC-PUNE-02'),
        ('Pune East Public School 4', 'SCH-PUNE-2-4', 'CBSE', 'English', 2004, 'Mr. Ketan Gaikwad', '9820012400', 'info@sch_pune_2_4.edu.in', 310, 16, true, 'Plot No 40, Main Road', 'Near Pune East Station', 'Pune East', 'Pune East', 'Pune East District', 'Maharashtra', '4110024', 'EC-PUNE-02'),
        ('Pune East English Medium School 5', 'SCH-PUNE-2-5', 'State Board', 'English', 2005, 'Mr. Anjali Tambe', '9820012500', 'info@sch_pune_2_5.edu.in', 325, 17, true, 'Plot No 50, Main Road', 'Near Pune East Station', 'Pune East', 'Pune East', 'Pune East District', 'Maharashtra', '4110025', 'EC-PUNE-02'),
        -- Mumbai South Centre schools
        ('Mumbai South Vidyalaya 1', 'SCH-MUM-1-1', 'CBSE', 'English', 1991, 'Mr. Sarang Bhosle', '9820021100', 'info@sch_mum_1_1.edu.in', 265, 13, true, 'Plot No 10, Main Road', 'Near Mumbai South Station', 'Mumbai South', 'Mumbai South', 'Mumbai South District', 'Maharashtra', '4110031', 'EC-MUM-01'),
        ('Mumbai South High School 2', 'SCH-MUM-1-2', 'State Board', 'Marathi', 1992, 'Mr. Prachi Jadhav', '9820021200', 'info@sch_mum_1_2.edu.in', 280, 14, true, 'Plot No 20, Main Road', 'Near Mumbai South Station', 'Mumbai South', 'Mumbai South', 'Mumbai South District', 'Maharashtra', '4110032', 'EC-MUM-01'),
        ('Mumbai South Academy 3', 'SCH-MUM-1-3', 'CBSE', 'English', 1993, 'Mr. Vikram Joshi', '9820021300', 'info@sch_mum_1_3.edu.in', 295, 15, true, 'Plot No 30, Main Road', 'Near Mumbai South Station', 'Mumbai South', 'Mumbai South', 'Mumbai South District', 'Maharashtra', '4110033', 'EC-MUM-01'),
        ('Mumbai South Public School 4', 'SCH-MUM-1-4', 'State Board', 'English', 1994, 'Mr. Shreya Patil', '9820021400', 'info@sch_mum_1_4.edu.in', 310, 16, true, 'Plot No 40, Main Road', 'Near Mumbai South Station', 'Mumbai South', 'Mumbai South', 'Mumbai South District', 'Maharashtra', '4110034', 'EC-MUM-01'),
        ('Mumbai South English Medium School 5', 'SCH-MUM-1-5', 'CBSE', 'English', 1995, 'Mr. Gaurav Deshmukh', '9820021500', 'info@sch_mum_1_5.edu.in', 325, 17, true, 'Plot No 50, Main Road', 'Near Mumbai South Station', 'Mumbai South', 'Mumbai South', 'Mumbai South District', 'Maharashtra', '4110035', 'EC-MUM-01'),
        -- Mumbai North Centre schools
        ('Mumbai North Vidyalaya 1', 'SCH-MUM-2-1', 'State Board', 'English', 2006, 'Mr. Minal Kulkarni', '9820022100', 'info@sch_mum_2_1.edu.in', 265, 13, true, 'Plot No 10, Main Road', 'Near Mumbai North Station', 'Mumbai North', 'Mumbai North', 'Mumbai North District', 'Maharashtra', '4110041', 'EC-MUM-02'),
        ('Mumbai North High School 2', 'SCH-MUM-2-2', 'CBSE', 'Marathi', 2007, 'Mr. Rahul Chavan', '9820022200', 'info@sch_mum_2_2.edu.in', 280, 14, true, 'Plot No 20, Main Road', 'Near Mumbai North Station', 'Mumbai North', 'Mumbai North', 'Mumbai North District', 'Maharashtra', '4110042', 'EC-MUM-02'),
        ('Mumbai North Academy 3', 'SCH-MUM-2-3', 'State Board', 'English', 2008, 'Mr. Sneha Shinde', '9820022300', 'info@sch_mum_2_3.edu.in', 295, 15, true, 'Plot No 30, Main Road', 'Near Mumbai North Station', 'Mumbai North', 'Mumbai North', 'Mumbai North District', 'Maharashtra', '4110043', 'EC-MUM-02'),
        ('Mumbai North Public School 4', 'SCH-MUM-2-4', 'CBSE', 'English', 2009, 'Mr. Aditya Pawar', '9820022400', 'info@sch_mum_2_4.edu.in', 310, 16, true, 'Plot No 40, Main Road', 'Near Mumbai North Station', 'Mumbai North', 'Mumbai North', 'Mumbai North District', 'Maharashtra', '4110044', 'EC-MUM-02'),
        ('Mumbai North English Medium School 5', 'SCH-MUM-2-5', 'State Board', 'English', 2010, 'Mr. Pooja More', '9820022500', 'info@sch_mum_2_5.edu.in', 325, 17, true, 'Plot No 50, Main Road', 'Near Mumbai North Station', 'Mumbai North', 'Mumbai North', 'Mumbai North District', 'Maharashtra', '4110045', 'EC-MUM-02'),
        -- Nagpur City Centre schools
        ('Nagpur City Vidyalaya 1', 'SCH-NAG-1-1', 'CBSE', 'English', 1992, 'Mr. Rohan Gaikwad', '9820031100', 'info@sch_nag_1_1.edu.in', 265, 13, true, 'Plot No 10, Main Road', 'Near Nagpur City Station', 'Nagpur City', 'Nagpur City', 'Nagpur City District', 'Maharashtra', '4110051', 'EC-NAG-01'),
        ('Nagpur City High School 2', 'SCH-NAG-1-2', 'State Board', 'Marathi', 1993, 'Mr. Sayali Tambe', '9820031200', 'info@sch_nag_1_2.edu.in', 280, 14, true, 'Plot No 20, Main Road', 'Near Nagpur City Station', 'Nagpur City', 'Nagpur City', 'Nagpur City District', 'Maharashtra', '4110052', 'EC-NAG-01'),
        ('Nagpur City Academy 3', 'SCH-NAG-1-3', 'CBSE', 'English', 1994, 'Mr. Amit Bhosle', '9820031300', 'info@sch_nag_1_3.edu.in', 295, 15, true, 'Plot No 30, Main Road', 'Near Nagpur City Station', 'Nagpur City', 'Nagpur City', 'Nagpur City District', 'Maharashtra', '4110053', 'EC-NAG-01'),
        ('Nagpur City Public School 4', 'SCH-NAG-1-4', 'State Board', 'English', 1995, 'Mr. Tejashree Jadhav', '9820031400', 'info@sch_nag_1_4.edu.in', 310, 16, true, 'Plot No 40, Main Road', 'Near Nagpur City Station', 'Nagpur City', 'Nagpur City', 'Nagpur City District', 'Maharashtra', '4110054', 'EC-NAG-01'),
        ('Nagpur City English Medium School 5', 'SCH-NAG-1-5', 'CBSE', 'English', 1996, 'Mr. Ketan Joshi', '9820031500', 'info@sch_nag_1_5.edu.in', 325, 17, true, 'Plot No 50, Main Road', 'Near Nagpur City Station', 'Nagpur City', 'Nagpur City', 'Nagpur City District', 'Maharashtra', '4110055', 'EC-NAG-01'),
        -- Nagpur West Centre schools
        ('Nagpur West Vidyalaya 1', 'SCH-NAG-2-1', 'State Board', 'English', 2007, 'Mr. Anjali Patil', '9820032100', 'info@sch_nag_2_1.edu.in', 265, 13, true, 'Plot No 10, Main Road', 'Near Nagpur West Station', 'Nagpur West', 'Nagpur West', 'Nagpur West District', 'Maharashtra', '4110061', 'EC-NAG-02'),
        ('Nagpur West High School 2', 'SCH-NAG-2-2', 'CBSE', 'Marathi', 2008, 'Mr. Sarang Deshmukh', '9820032200', 'info@sch_nag_2_2.edu.in', 280, 14, true, 'Plot No 20, Main Road', 'Near Nagpur West Station', 'Nagpur West', 'Nagpur West', 'Nagpur West District', 'Maharashtra', '4110062', 'EC-NAG-02'),
        ('Nagpur West Academy 3', 'SCH-NAG-2-3', 'State Board', 'English', 2009, 'Mr. Prachi Kulkarni', '9820032300', 'info@sch_nag_2_3.edu.in', 295, 15, true, 'Plot No 30, Main Road', 'Near Nagpur West Station', 'Nagpur West', 'Nagpur West', 'Nagpur West District', 'Maharashtra', '4110063', 'EC-NAG-02'),
        ('Nagpur West Public School 4', 'SCH-NAG-2-4', 'CBSE', 'English', 2010, 'Mr. Vikram Shinde', '9820032400', 'info@sch_nag_2_4.edu.in', 310, 16, true, 'Plot No 40, Main Road', 'Near Nagpur West Station', 'Nagpur West', 'Nagpur West', 'Nagpur West District', 'Maharashtra', '4110064', 'EC-NAG-02'),
        ('Nagpur West English Medium School 5', 'SCH-NAG-2-5', 'State Board', 'English', 2011, 'Mr. Shreya Pawar', '9820032500', 'info@sch_nag_2_5.edu.in', 325, 17, true, 'Plot No 50, Main Road', 'Near Nagpur West Station', 'Nagpur West', 'Nagpur West', 'Nagpur West District', 'Maharashtra', '4110065', 'EC-NAG-02'),
        -- Nashik Central Centre schools
        ('Nashik Central Vidyalaya 1', 'SCH-NSK-1-1', 'CBSE', 'English', 1993, 'Mr. Gaurav More', '9820041100', 'info@sch_nsk_1_1.edu.in', 265, 13, true, 'Plot No 10, Main Road', 'Near Nashik Central Station', 'Nashik Central', 'Nashik Central', 'Nashik Central District', 'Maharashtra', '4110071', 'EC-NSK-01'),
        ('Nashik Central High School 2', 'SCH-NSK-1-2', 'State Board', 'Marathi', 1994, 'Mr. Minal Gaikwad', '9820041200', 'info@sch_nsk_1_2.edu.in', 280, 14, true, 'Plot No 20, Main Road', 'Near Nashik Central Station', 'Nashik Central', 'Nashik Central', 'Nashik Central District', 'Maharashtra', '4110072', 'EC-NSK-01'),
        ('Nashik Central Academy 3', 'SCH-NSK-1-3', 'CBSE', 'English', 1995, 'Mr. Rahul Tambe', '9820041300', 'info@sch_nsk_1_3.edu.in', 295, 15, true, 'Plot No 30, Main Road', 'Near Nashik Central Station', 'Nashik Central', 'Nashik Central', 'Nashik Central District', 'Maharashtra', '4110073', 'EC-NSK-01'),
        ('Nashik Central Public School 4', 'SCH-NSK-1-4', 'State Board', 'English', 1996, 'Mr. Sneha Bhosle', '9820041400', 'info@sch_nsk_1_4.edu.in', 310, 16, true, 'Plot No 40, Main Road', 'Near Nashik Central Station', 'Nashik Central', 'Nashik Central', 'Nashik Central District', 'Maharashtra', '4110074', 'EC-NSK-01'),
        ('Nashik Central English Medium School 5', 'SCH-NSK-1-5', 'CBSE', 'English', 1997, 'Mr. Amit Jadhav', '9820041500', 'info@sch_nsk_1_5.edu.in', 325, 17, true, 'Plot No 50, Main Road', 'Near Nashik Central Station', 'Nashik Central', 'Nashik Central', 'Nashik Central District', 'Maharashtra', '4110075', 'EC-NSK-01'),
        -- Nashik Road Centre schools
        ('Nashik Road Vidyalaya 1', 'SCH-NSK-2-1', 'State Board', 'English', 2008, 'Mr. Tejashree Joshi', '9820042100', 'info@sch_nsk_2_1.edu.in', 265, 13, true, 'Plot No 10, Main Road', 'Near Nashik Road Station', 'Nashik Road', 'Nashik Road', 'Nashik Road District', 'Maharashtra', '4110081', 'EC-NSK-02'),
        ('Nashik Road High School 2', 'SCH-NSK-2-2', 'CBSE', 'Marathi', 2009, 'Mr. Ketan Patil', '9820042200', 'info@sch_nsk_2_2.edu.in', 280, 14, true, 'Plot No 20, Main Road', 'Near Nashik Road Station', 'Nashik Road', 'Nashik Road', 'Nashik Road District', 'Maharashtra', '4110082', 'EC-NSK-02'),
        ('Nashik Road Academy 3', 'SCH-NSK-2-3', 'State Board', 'English', 2010, 'Mr. Anjali Deshmukh', '9820042300', 'info@sch_nsk_2_3.edu.in', 295, 15, true, 'Plot No 30, Main Road', 'Near Nashik Road Station', 'Nashik Road', 'Nashik Road', 'Nashik Road District', 'Maharashtra', '4110083', 'EC-NSK-02'),
        ('Nashik Road Public School 4', 'SCH-NSK-2-4', 'CBSE', 'English', 2011, 'Mr. Sarang Kulkarni', '9820042400', 'info@sch_nsk_2_4.edu.in', 310, 16, true, 'Plot No 40, Main Road', 'Near Nashik Road Station', 'Nashik Road', 'Nashik Road', 'Nashik Road District', 'Maharashtra', '4110084', 'EC-NSK-02'),
        ('Nashik Road English Medium School 5', 'SCH-NSK-2-5', 'State Board', 'English', 2012, 'Mr. Prachi Shinde', '9820042500', 'info@sch_nsk_2_5.edu.in', 325, 17, true, 'Plot No 50, Main Road', 'Near Nashik Road Station', 'Nashik Road', 'Nashik Road', 'Nashik Road District', 'Maharashtra', '4110085', 'EC-NSK-02')
    ) AS v(school_name, school_code, board_affiliation, medium_of_instruction,
           establishment_year, principal_name, principal_contact_number,
           official_email, seating_capacity, number_of_classrooms, cctv_available,
           address_line1, address_line2, village_or_city, taluka, district, state, pincode,
           centre_code)
    JOIN centre_insert c ON c.centre_code = v.centre_code
    ON CONFLICT DO NOTHING
    RETURNING school_id, school_code
),
-- ============================================
-- 4. SEED STUDENTS (10 per school = 400 total)
-- ============================================
student_insert AS (
    INSERT INTO student (
        first_name, middle_name, last_name, contact, email, password, age, mother_tongue, school_id
    )
    SELECT
        first_name, middle_name, last_name, contact,
        lower(first_name || last_name) || floor(random() * 900 + 100)::int::text || '@gmail.com',
        'student123', age, 'Marathi', school_id
    FROM (VALUES
        -- Pune Central School 1 students
        ('Rahul', 'Sanjay', 'Joshi', '9123401101', 14, 'SCH-PUNE-1-1'),
        ('Sneha', 'Ramesh', 'Patil', '9123401102', 15, 'SCH-PUNE-1-1'),
        ('Aditya', 'Vijay', 'Deshmukh', '9123401103', 16, 'SCH-PUNE-1-1'),
        ('Pooja', 'Anil', 'Kulkarni', '9123401104', 14, 'SCH-PUNE-1-1'),
        ('Rohan', 'Arjun', 'Chavan', '9123401105', 15, 'SCH-PUNE-1-1'),
        ('Sayali', 'Dilip', 'Shinde', '9123401106', 16, 'SCH-PUNE-1-1'),
        ('Amit', 'Prakash', 'Pawar', '9123401107', 14, 'SCH-PUNE-1-1'),
        ('Tejashree', 'Sunil', 'More', '9123401108', 15, 'SCH-PUNE-1-1'),
        ('Ketan', 'Vilas', 'Gaikwad', '9123401109', 16, 'SCH-PUNE-1-1'),
        ('Anjali', 'Sudhir', 'Tambe', '9123401110', 14, 'SCH-PUNE-1-1'),
        -- Pune Central School 2 students
        ('Sarang', 'Sanjay', 'Bhosle', '9123401201', 15, 'SCH-PUNE-1-2'),
        ('Prachi', 'Ramesh', 'Jadhav', '9123401202', 16, 'SCH-PUNE-1-2'),
        ('Vikram', 'Vijay', 'Joshi', '9123401203', 14, 'SCH-PUNE-1-2'),
        ('Shreya', 'Anil', 'Patil', '9123401204', 15, 'SCH-PUNE-1-2'),
        ('Gaurav', 'Arjun', 'Deshmukh', '9123401205', 16, 'SCH-PUNE-1-2'),
        ('Minal', 'Dilip', 'Kulkarni', '9123401206', 14, 'SCH-PUNE-1-2'),
        ('Rahul', 'Prakash', 'Chavan', '9123401207', 15, 'SCH-PUNE-1-2'),
        ('Sneha', 'Sunil', 'Shinde', '9123401208', 16, 'SCH-PUNE-1-2'),
        ('Aditya', 'Vilas', 'Pawar', '9123401209', 14, 'SCH-PUNE-1-2'),
        ('Pooja', 'Sudhir', 'More', '9123401210', 15, 'SCH-PUNE-1-2'),
        -- Pune Central School 3 students
        ('Rohan', 'Sanjay', 'Gaikwad', '9123401301', 16, 'SCH-PUNE-1-3'),
        ('Sayali', 'Ramesh', 'Tambe', '9123401302', 14, 'SCH-PUNE-1-3'),
        ('Amit', 'Vijay', 'Bhosle', '9123401303', 15, 'SCH-PUNE-1-3'),
        ('Tejashree', 'Anil', 'Jadhav', '9123401304', 16, 'SCH-PUNE-1-3'),
        ('Ketan', 'Arjun', 'Joshi', '9123401305', 14, 'SCH-PUNE-1-3'),
        ('Anjali', 'Dilip', 'Patil', '9123401306', 15, 'SCH-PUNE-1-3'),
        ('Sarang', 'Prakash', 'Deshmukh', '9123401307', 16, 'SCH-PUNE-1-3'),
        ('Prachi', 'Sunil', 'Kulkarni', '9123401308', 14, 'SCH-PUNE-1-3'),
        ('Vikram', 'Vilas', 'Chavan', '9123401309', 15, 'SCH-PUNE-1-3'),
        ('Shreya', 'Sudhir', 'Shinde', '9123401310', 16, 'SCH-PUNE-1-3')
    ) AS v(first_name, middle_name, last_name, contact, age, school_code)
    JOIN school_insert s ON s.school_code = v.school_code
    ON CONFLICT DO NOTHING
)
-- ============================================
-- 5. SEED EXAM OFFICERS
-- ============================================
INSERT INTO exam_officer (username, password, name)
SELECT 'exam_officer1', 'officer123', 'Rajesh Kumar'
WHERE NOT EXISTS (SELECT 1 FROM exam_officer WHERE username = 'exam_officer1');

INSERT INTO exam_officer (username, password, name)
SELECT 'exam_officer2', 'officer123', 'Priya Sharma'
WHERE NOT EXISTS (SELECT 1 FROM exam_officer WHERE username = 'exam_officer2');

INSERT INTO exam_officer (username, password, name)
SELECT 'exam_officer3', 'officer123', 'Amit Deshmukh'
WHERE NOT EXISTS (SELECT 1 FROM exam_officer WHERE username = 'exam_officer3');

-- ============================================
-- 6. SEED ADMIN (skip if already exists from V3)
-- ============================================
INSERT INTO admin (username, password)
SELECT 'admin', 'admin123'
WHERE NOT EXISTS (SELECT 1 FROM admin WHERE username = 'admin');
