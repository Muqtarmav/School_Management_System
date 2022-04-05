create database school_db;
create user 'school_user'@'localhost' identified by 'pass_123';
grant all privileges on school_db. * to 'school_user'@'localhost';
flush privileges;
