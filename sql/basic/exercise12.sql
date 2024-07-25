-- DDL
CREATE SCHEMA cd;

CREATE TABLE cd.facilities (
    facid INTEGER NOT NULL PRIMARY KEY,
    name CHARACTER VARYING(45) NOT NULL,
    membercost NUMERIC(10,2) NOT NULL DEFAULT 0.00,
    guestcost NUMERIC(10,2)  NOT NULL DEFAULT 0.00,
    initialoutlay NUMERIC(10,2)  NOT NULL DEFAULT 0.00,
    monthlymaintenance NUMERIC(10,2)  NOT NULL DEFAULT 0.00
);

CREATE TABLE cd.members(
	memid INTEGER NOT NULL PRIMARY KEY AUTO_INCREMENT,
    surname CHARACTER VARYING(200) NOT NULL,
    firstname CHARACTER VARYING(200) NOT NULL,
    address CHARACTER VARYING(300) DEFAULT '',
    zipcode INTEGER NOT NULL,
    telephone CHARACTER VARYING(20) NOT NULL,
    recommendedby INTEGER NOT NULL,
    joindate TIMESTAMP NOT NULL
);

-- DML 
INSERT INTO cd.facilities (facid, name, membercost, guestcost, initialoutlay, monthlymaintenance) VALUES
(0, 'Tennis Court 1', 5, 25, 10000, 200),
(1, 'Tennis Court 2', 5, 25, 8000, 200),
(2, 'Badminton Court', 0, 15.5, 4000, 50),
(3, 'Table Tennis', 0, 5, 320, 10),
(4, 'Massage Room 1', 35, 80, 4000, 3000),
(5, 'Massage Room 2', 35, 80, 4000, 3000),
(6, 'Squash Court', 3.5, 17.5, 5000, 80),
(7, 'Snooker Table', 0, 5, 450, 15),
(8, 'Pool Table', 0, 5, 400, 15);


INSERT INTO cd.members (surname, firstname, address, zipcode, telephone, recommendedby, joindate)
VALUES
('Sarwin', 'Ramnaresh', 'Some Address 24', FLOOR(RAND() * 100000 + 100000), '555-0024', 0, '2012-09-01 08:44:42'),
('Jones', 'Douglas', 'Some Address 26', FLOOR(RAND() * 100000 + 100000), '555-0026', 0, '2012-09-02 18:43:05'),
('Rumney', 'Henrietta', 'Some Address 27', FLOOR(RAND() * 100000 + 100000), '555-0027', 0, '2012-09-05 08:42:35'),
('Farrell', 'David', 'Some Address 28', FLOOR(RAND() * 100000 + 100000), '555-0028', 0, '2012-09-15 08:22:05'),
('Worthington-Smyth', 'Henry', 'Some Address 29', FLOOR(RAND() * 100000 + 100000), '555-0029', 0, '2012-09-17 12:27:15'),
('Purview', 'Millicent', 'Some Address 30', FLOOR(RAND() * 100000 + 100000), '555-0030', 0, '2012-09-18 19:04:01'),
('Tupperware', 'Hyacinth', 'Some Address 33', FLOOR(RAND() * 100000 + 100000), '555-0033', 0, '2012-09-18 19:32:05'),
('Hunt', 'John', 'Some Address 35', FLOOR(RAND() * 100000 + 100000), '555-0035', 0, '2012-09-19 11:32:45'),
('Crumpet', 'Erica', 'Some Address 36', FLOOR(RAND() * 100000 + 100000), '555-0036', 0, '2012-09-22 08:36:38'),
('Smith', 'Darren', 'Some Address 37', FLOOR(RAND() * 100000 + 100000), '555-0037', 0, '2012-09-26 18:08:45'),
('Brown', 'David', 'Some Address 6', FLOOR(RAND() * 100000 + 100000), '555-0006', 0, '2007-08-26 13:08:45'),
('Davis', 'Emily', 'Some Address 7', FLOOR(RAND() * 100000 + 100000), '555-0007', 0, '2020-01-03 10:00:01'),
('Miller', 'Frank', 'Some Address 8', FLOOR(RAND() * 100000 + 100000), '555-0008', 0, '2002-07-04 15:30:30'),
('Wilson', 'Grace', 'Some Address 9', FLOOR(RAND() * 100000 + 100000), '555-0009', 0, '2002-06-03 16:56:23'),
('Moore', 'Harry', 'Some Address 10', FLOOR(RAND() * 100000 + 100000), '555-0010', 0, '2003-03-08 15:20:15'),
('Taylor', 'Ivy', 'Some Address 11', FLOOR(RAND() * 100000 + 100000), '555-0011', 0, '2023-09-06 14:18:29');

SELECT firstname, surname, joindate
FROM cd.members
ORDER BY joindate DESC
LIMIT 1;