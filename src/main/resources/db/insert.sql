set foreign_key_checks = 0;

truncate table student;
truncate table teacher;
truncate table course;
truncate table user_course;

insert into student(id, first_Name, last_Name, email_address, mobile_number, gender)
values(1, 'muqtar', 'tunji', 'mavic@gmail.com', '08130249216', 'male'),
    (2, 'sarnk', 'gold', 'sarnk@gmail.com', '097488393', 'male'),
    (3, 'binta', 'queen', 'binta@yahoo.com', '903849893993', 'female');
--
-- insert into course(id, name)
-- values(002, 'PLB');

insert into teacher(id, name, gender, email, mobile_number)
values(11, 'segun kogiy', 'male', 'segunn@gmail.com', '0811456789'),
(2, 'dbanj oganla', 'male', 'oganla@yahoo.com', '0703456789');

-- insert into user_course(student_id, code_no)
-- values(1, 002);

set foreign_key_checks = 1;