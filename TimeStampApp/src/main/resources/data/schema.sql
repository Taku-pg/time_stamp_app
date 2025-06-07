-- tables
-- Table: Break
CREATE TABLE Break (
    Id bigint NOT NULL AUTO_INCREMENT,
    Start_Time datetime  NOT NULL,
    End_Time datetime  NULL,
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
    Last_update datetime null,
    User_Id bigint  NOT NULL,
    Department_Id bigint  NOT NULL,
    Status_Id bigint  NOT NULL,
    CONSTRAINT Employee_pk PRIMARY KEY (Id)
);

-- Table: Segment_type
CREATE TABLE Segment_type(
     Id bigint NOT NULL AUTO_INCREMENT,
     Name varchar(20) NOT NULL ,
     Magnification double(5,4) NOT NULL ,
     CONSTRAINT Segment_type_pk PRIMARY KEY (id)
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
    Start_Time datetime  NOT NULL,
    End_Time datetime  NULL,
    Auto_Leave bool  NOT NULL,
    Employee_Id bigint  NOT NULL,
    CONSTRAINT Working_hour_pk PRIMARY KEY (Id)
);

-- Table: Working_hour_segment
CREATE TABLE Working_hour_segment (
    Id bigint NOT NULL AUTO_INCREMENT,
    Duration double(10,2)  NOT NULL,
    Working_hour_Id bigint  NOT NULL,
    Segment_type_id bigint NOT NULL ,
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

-- Reference: Segment_type_Working_hour_segment (table: Working_hour_segment)
ALTER TABLE Working_hour_segment ADD CONSTRAINT Segment_type_Working_hour_segment FOREIGN KEY Segment_type_Working_hour_segment (Segment_type_id)
    REFERENCES Segment_type (id);


-- End of file.

