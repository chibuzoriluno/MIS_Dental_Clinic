

create database dental_clinic;

CREATE TABLE `dental_clinic`.`users` (
  `user_id` int NOT NULL AUTO_INCREMENT,
  `email` varchar(225)  NOT NULL,
  `passwd` varchar(225) NOT NULL,
  `first_name` varchar(225) NOT NULL,
  `last_name` varchar(225) NOT NULL,
  `address` text NOT NULL,
  `city` varchar(50) NOT NULL,
  `phone` varchar(15) NOT NULL,
  `section` enum('doctor','nurse','admin','employee') NOT NULL,
  `created_at` timestamp NOT NULL DEFAULT current_timestamp(),
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `email` (`email`)
);


CREATE TABLE dental_clinic.doctors (
  `doctor_id` int NOT NULL AUTO_INCREMENT,
  `user_id` int NOT NULL,
  `email` varchar(225) NOT NULL,
  `speciality` enum('endodontist','orthodontist','periodontist','anesthesiologist','pedodontist','maxillofacial','prosthodontist','none') NOT NULL,
  PRIMARY KEY (`doctor_id`),
  KEY `email` (`email`),
  KEY `user_id` (`user_id`),
  CONSTRAINT `doctors_ibfk_1` FOREIGN KEY (`email`) REFERENCES `users` (`email`),
  CONSTRAINT `doctors_ibfk_2` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`)
);


CREATE TABLE `dental_clinic`.`nurses` (
  `nurse_id` int NOT NULL AUTO_INCREMENT,
  `user_id` int NOT NULL,
  `email` varchar(225) NOT NULL,
  `speciality` enum('matron','nurse1','nurse2') NOT NULL,
  PRIMARY KEY (`nurse_id`),
  KEY `email` (`email`),
  KEY `user_id` (`user_id`),
  FOREIGN KEY (`email`) REFERENCES `users` (`email`),
  FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`)
);


CREATE TABLE dental_clinic.employee (
  `employee_id` int NOT NULL AUTO_INCREMENT,
  `user_id` int NOT NULL,
  `email` varchar(225) NOT NULL,
  `speciality` enum('secretary', 'receptionist', 'accountant') NOT NULL,
  PRIMARY KEY (`employee_id`),
  KEY `email` (`email`),
  KEY `user_id` (`user_id`),
  FOREIGN KEY (`email`) REFERENCES `users` (`email`),
  FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`)
);

CREATE TABLE dental_clinic.admin (
  `admin_id` int NOT NULL AUTO_INCREMENT,
  `user_id` int NOT NULL,
  `email` varchar(225) NOT NULL,
  `speciality` enum('hr', 'director', 'cmo') NOT NULL,
  PRIMARY KEY (`admin_id`),
  KEY `email` (`email`),
  KEY `user_id` (`user_id`),
  FOREIGN KEY (`email`) REFERENCES `users` (`email`),
  FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`)
);

create table dental_clinic.patient (
patient_id int primary key not null unique auto_increment, 
employee_email varchar(225) not null,
first_name varchar(225) not null, 
last_name varchar(225) not null,
address text NOT NULL,
city varchar(50) NOT NULL,
phone varchar(15) NOT NULL,
created_at timestamp NOT NULL DEFAULT current_timestamp(), 
FOREIGN KEY (`employee_email`) REFERENCES `employee` (`email`)
);

create table dental_clinic.treatment (
trmt_id int primary key not null unique auto_increment, 
doctor_email varchar(225) not null,
patient_id int not null,
diagnosis text not null,
trmt_procedure text not null,
medication text not null,
trmt_time timestamp NOT NULL DEFAULT current_timestamp(), 
FOREIGN KEY (`doctor_email`) REFERENCES `doctors` (`email`),
FOREIGN KEY (`patient_id`) REFERENCES `patient` (`patient_id`)
);

create table dental_clinic.appointment (
apt_id int primary key not null unique auto_increment, 
employee_email varchar(225) not null, 
doctor_email varchar(225) not null, 
patient_id int not null, 
this_day date not null, 
this_time time not null,
created_at timestamp NOT NULL DEFAULT current_timestamp(), 
 FOREIGN KEY (`employee_email`) REFERENCES `employee` (`email`),
FOREIGN KEY (`doctor_email`) REFERENCES `doctors` (`email`),
FOREIGN KEY (`patient_id`) REFERENCES `patient` (`patient_id`)
);

create table dental_clinic.nrs_visits_patient (
nurse_email varchar(225) not null, 
patient_id int not null, 
visited_at timestamp NOT NULL DEFAULT current_timestamp(), 
FOREIGN KEY (`nurse_email`) REFERENCES `nurses` (`email`),
FOREIGN KEY (`patient_id`) REFERENCES `patient` (`patient_id`)
);

create table dental_clinic.activity_report (
activity_id int primary key not null unique auto_increment, 
email varchar(225) not null,
activity varchar(225) not null,
activity_time timestamp NOT NULL DEFAULT current_timestamp(), 
FOREIGN KEY (`email`) REFERENCES `users` (`email`)
);

SELECT * FROM dental_clinic.users;
INSERT INTO dental_clinic.users (user_id, email, passwd, first_name, last_name, address, city, phone, section, created_at)
VALUES 
(1, 'user1@example.com', 'passwd1', 'Apple', 'One', '123 Main St', 'New York', '123-456-7890', 'doctor', NOW()),
(2, 'user2@example.com', 'passwd2', 'Oluwaseyi', 'Two', '456 Park Ave', 'Chicago', '123-456-7891', 'nurse', NOW()),
(3, 'user3@example.com', 'passwd3', 'Ekene', 'Three', '789 Elm St', 'Los Angeles', '123-456-7892', 'admin', NOW()),
(4, 'user4@example.com', 'passwd4', 'Ngozi', 'Four', '321 Maple St', 'Miami', '123-456-7893', 'employee', NOW()),
(5, 'user5@example.com', 'passwd5', 'Adeyinka', 'Five', '654 Pine St', 'Dallas', '123-456-7894', 'doctor', NOW()),
(6, 'user6@example.com', 'passwd6', 'Elizabeth', 'Six', '987 Cedar St', 'Philadelphia', '123-456-7895', 'nurse', NOW()),
(7, 'user7@example.com', 'passwd7', 'Ihuoma', 'Seven', '246 Oak St', 'San Francisco', '123-456-7896', 'admin', NOW()),
(8, 'user8@example.com', 'passwd8', 'David', 'Eight', '369 Birch St', 'Seattle', '123-456-7897', 'employee', NOW()),
(9, 'user9@example.com', 'passwd9', 'Nkemakonam', 'Nine', '159 Maple St', 'Boston', '123-456-7898', 'doctor', NOW()),
(10, 'user10@example.com', 'passwd10', 'Mary', 'Ten', '753 Cedar St', 'Washington D.C.', '123-456-7899', 'nurse', NOW());


INSERT INTO dental_clinic.doctors (user_id, email, speciality)
values (5,'user5@example.com', 'none'), (1, 'user1@example.com', 'endodontist');

INSERT INTO dental_clinic.nurses (user_id, email, speciality)
values (2,'user2@example.com', 'matron'), (6, 'user6@example.com', 'nurse1');

INSERT INTO dental_clinic.employee (user_id, email, speciality)
values (8,'user8@example.com', 'receptionist'), (4, 'user4@example.com', 'secretary');

INSERT INTO dental_clinic.admin (user_id, email, speciality)
values (7,'user7@example.com', 'hr'), (3, 'user3@example.com', 'director');


