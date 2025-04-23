CREATE DATABASE payroll_db;

-- Use the database
USE payroll_db;

-- Create the employees table
CREATE TABLE employees (
    id INT PRIMARY KEY,
    name VARCHAR(100),
    basic_salary DOUBLE,
    total_salary DOUBLE
);
