INSERT INTO `User` ( Username,Password,`Role`) VALUES
    ('admin','$2a$10$WocKS5djfwc5WMdXO6uSWex2uGPenF1a6Wt9vqn/.AXU0TVbf/tOu','ADMIN'),
    ('employee1@gmail.com','$2a$10$yZiza8pB3noJfCk/nQ4RHuKOsaLYPAc3nswx04F2Qzuo7IIBor7ie','EMPLOYEE'),
    ('employee2@gmail.com','$2a$10$yt08eEyfEDUGGTynQMiDOekYzojsNfyM.Jz2bYmM5Ng57KDdiu57a','EMPLOYEE'),
    ('employee3@gmail.com','$2a$10$gMJgc90mQvgoF9FXfN6YZufqbm1cDFYDX0VWqZNVuyGGdctp0dZ12','EMPLOYEE');

INSERT INTO Department (Name) VALUES
    ("Manager"),
    ("Kitchen"),
    ("Hall");

INSERT INTO Status (Type) VALUES
    ('Leave'),
    ('Work'),
    ('Break');

INSERT INTO Employee (First_Name,Last_Name,Salary,Email,User_Id,Department_Id,Status_ID) VALUES
    ('John','Smith',80,'employee1@gmail.com',2,1,1),
    ('James','Bond',32,'employee2@gmail.com',3,2,1),
    ('Bob','Takahashi',26,'employee3@gmail.com',4,3,1);

INSERT INTO segment_type (Name, Magnification) VALUES
    ('regular',1.00);
INSERT INTO segment_type (Name, Magnification) VALUES
    ('night',1.25);
INSERT INTO segment_type (Name, Magnification) VALUES
    ('overtime',1.25);
INSERT INTO segment_type (Name, Magnification) VALUES
    ('over-night',1.57);
