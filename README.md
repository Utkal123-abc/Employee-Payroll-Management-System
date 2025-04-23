# Employee Payroll Management System

A Java application to manage employee payroll data. It allows users to:
- Add employee records
- View all employees
- Delete employee records
- Auto-calculate total salary with a 10% bonus

##  Tech Stack
- Core Java
- JDBC
- MySQL

##  How to Run
1. Create the MySQL database and table using `payroll_db_schema.sql`
2. Update the DB username and password in `EmployeePayrollSystem.java`
3. Compile and run the application

## 🗃 Sample SQL Schema
```sql
CREATE DATABASE payroll_db;
USE payroll_db;

CREATE TABLE employees (
    id INT PRIMARY KEY,
    name VARCHAR(100),
    basic_salary DOUBLE,
    total_salary DOUBLE
);
