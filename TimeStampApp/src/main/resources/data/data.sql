INSERT INTO `User` (Id, Username,Password,`Role`) VALUES
    (1,'admin','$2a$10$WocKS5djfwc5WMdXO6uSWex2uGPenF1a6Wt9vqn/.AXU0TVbf/tOu','ADMIN');
INSERT INTO `User` (Id, Username,Password,`Role`) VALUES
    (2,'employee1@gmail.com','$2a$10$yZiza8pB3noJfCk/nQ4RHuKOsaLYPAc3nswx04F2Qzuo7IIBor7ie','EMPLOYEE');
INSERT INTO `User` (Id, Username,Password,`Role`) VALUES
    (3,'employee2@gmail.com','$2a$10$yt08eEyfEDUGGTynQMiDOekYzojsNfyM.Jz2bYmM5Ng57KDdiu57a','EMPLOYEE');
INSERT INTO `User` (Id, Username,Password,`Role`) VALUES
    (4,'employee3@gmail.com','$2a$10$gMJgc90mQvgoF9FXfN6YZufqbm1cDFYDX0VWqZNVuyGGdctp0dZ12','EMPLOYEE');

INSERT INTO Department (Id,Name) VALUES
    (1,"Manager");
INSERT INTO Department (Id,Name) VALUES
    (2,"Kitchen");
INSERT INTO Department (Id,Name) VALUES
    (3,"Hall");

INSERT INTO Status (Id,Type) VALUES
    (1,'Leave');
INSERT INTO Status (Id,Type) VALUES
    (2,'Work');
INSERT INTO Status (Id,Type) VALUES
    (3,'Break');

INSERT INTO Employee (Id,FirstName,LastName,Salary,Email,User_Id,Department_Id,Status_ID) VALUES
    (1,'John','Smith',80,'employee1@gmail.com',2,1,1);
INSERT INTO Employee (Id,FirstName,LastName,Salary,Email,User_Id,Department_Id,Status_ID) VALUES
    (2,'James','Bond',32,'employee2@gmail.com',3,2,1);
INSERT INTO Employee (Id,FirstName,LastName,Salary,Email,User_Id,Department_Id,Status_ID) VALUES
    (3,'Bob','Takahashi',26,'employee3@gmail.com',4,3,1);
