/*ALTER TABLE Break
DROP FOREIGN KEY Break_Working_hour;*/

/*ALTER TABLE Employee
DROP FOREIGN KEY Employee_Department;*/

/*ALTER TABLE Employee
DROP FOREIGN KEY Employee_Status;

ALTER TABLE Employee
DROP FOREIGN KEY Employee_User;*/

/*ALTER TABLE Working_hour
DROP FOREIGN KEY Working_hour_Employee;*/

/*ALTER TABLE Working_hour_segment
DROP FOREIGN KEY Working_hour_segment_Working_hour;*/

-- tables
/*SET FOREIGN_KEY_CHECKS = 0;
DROP TABLE IF EXISTS Break;

DROP TABLE IF EXISTS Department;

DROP TABLE IF EXISTS Employee;

DROP TABLE IF EXISTS Status;

DROP TABLE IF EXISTS User;

DROP TABLE IF EXISTS Working_hour;

DROP TABLE IF EXISTS Working_hour_segment;

SET FOREIGN_KEY_CHECKS = 1;*/


-- tables
-- Table: Break
CREATE TABLE Break (
    Id bigint NOT NULL AUTO_INCREMENT,
    Start_Time date  NOT NULL,
    End_Time date  NULL,
    Working_hour_Id bigint  NOT NULL,
    CONSTRAINT Break_pk PRIMARY KEY (Id)
);

-- Table: Department
CREATE TABLE Department (
    Id bigint NOT NULL AUTO_INCREMENT,
    Name varchar(50)  NOT NULL,
    CONSTRAINT Department_pk PRIMARY KEY (Id)
);

-- Table: Employee
CREATE TABLE Employee (
    Id bigint NOT NULL AUTO_INCREMENT,
    First_Name varchar(20)  NOT NULL,
    Last_Name varchar(20)  NOT NULL,
    Salary int  NOT NULL,
    Email varchar(50)  NOT NULL,
    User_Id bigint  NOT NULL,
    Department_Id bigint  NOT NULL,
    Status_Id bigint  NOT NULL,
    CONSTRAINT Employee_pk PRIMARY KEY (Id)
);

-- Table: Status
CREATE TABLE Status (
    Id bigint  NOT NULL AUTO_INCREMENT,
    Type varchar(20)  NOT NULL,
    CONSTRAINT Status_pk PRIMARY KEY (Id)
);

-- Table: User
CREATE TABLE `User` (
    Id bigint NOT NULL AUTO_INCREMENT,
    Username varchar(20)  NOT NULL,
    Password varchar(200)  NOT NULL,
    `Role` varchar(15)  NOT NULL,
    CONSTRAINT User_pk PRIMARY KEY (Id)
);

-- Table: Working_hour
CREATE TABLE Working_hour (
    Id bigint NOT NULL AUTO_INCREMENT,
    Start_Time date  NOT NULL,
    End_Time date  NULL,
    Auto_Leave bool  NOT NULL,
    Employee_Id bigint  NOT NULL,
    CONSTRAINT Working_hour_pk PRIMARY KEY (Id)
);

-- Table: Working_hour_segment
CREATE TABLE Working_hour_segment (
    Id bigint NOT NULL AUTO_INCREMENT,
    Type varchar(20)  NOT NULL,
    Duration double(10,2)  NOT NULL,
    Working_hour_Id bigint  NOT NULL,
    CONSTRAINT Working_hour_segment_pk PRIMARY KEY (Id)
);

-- foreign keys
-- Reference: Break_Working_hour (table: Break)
ALTER TABLE Break ADD CONSTRAINT Break_Working_hour FOREIGN KEY Break_Working_hour (Working_hour_Id)
    REFERENCES Working_hour (Id);

-- Reference: Employee_Department (table: Employee)
ALTER TABLE Employee ADD CONSTRAINT Employee_Department FOREIGN KEY Employee_Department (Department_Id)
    REFERENCES Department (Id);

-- Reference: Employee_Status (table: Employee)
ALTER TABLE Employee ADD CONSTRAINT Employee_Status FOREIGN KEY Employee_Status (Status_Id)
    REFERENCES Status (Id);

-- Reference: Employee_User (table: Employee)
ALTER TABLE Employee ADD CONSTRAINT Employee_User FOREIGN KEY Employee_User (User_Id)
    REFERENCES User (Id);

-- Reference: Working_hour_Employee (table: Working_hour)
ALTER TABLE Working_hour ADD CONSTRAINT Working_hour_Employee FOREIGN KEY Working_hour_Employee (Employee_Id)
    REFERENCES Employee (Id);

-- Reference: Working_hour_segment_Working_hour (table: Working_hour_segment)
ALTER TABLE Working_hour_segment ADD CONSTRAINT Working_hour_segment_Working_hour FOREIGN KEY Working_hour_segment_Working_hour (Working_hour_Id)
    REFERENCES Working_hour (Id);

-- End of file.

