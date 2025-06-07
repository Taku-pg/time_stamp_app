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

INSERT INTO working_hour (Start_Time, End_Time, Auto_Leave, Employee_Id) VALUES
    ('2025-06-01 10:00:00','2025-06-01 20:00:00',FALSE,1 );
INSERT INTO working_hour (Start_Time, End_Time, Auto_Leave, Employee_Id) VALUES
    ('2025-06-02 12:00:00','2025-06-03 00:00:00',FALSE,1 );
INSERT INTO working_hour (Start_Time, End_Time, Auto_Leave, Employee_Id) VALUES
    ('2025-06-03 10:00:00','2025-06-03 18:00:00',FALSE,1 );
INSERT INTO working_hour (Start_Time, End_Time, Auto_Leave, Employee_Id) VALUES
    ('2025-06-04 9:00:00','2025-06-04 16:45:00',FALSE,1 );
INSERT INTO working_hour (Start_Time, End_Time, Auto_Leave, Employee_Id) VALUES
    ('2025-06-05 8:00:00','2025-06-05 17:32:00',FALSE,1 );
INSERT INTO working_hour (Start_Time, End_Time, Auto_Leave, Employee_Id) VALUES
    ('2025-06-06 12:00:00','2025-06-06 20:00:00',FALSE,1 );
INSERT INTO working_hour (Start_Time, End_Time, Auto_Leave, Employee_Id) VALUES
    ('2025-06-07 9:00:00','2025-06-07 15:25:00',FALSE,1 );
INSERT INTO working_hour (Start_Time, End_Time, Auto_Leave, Employee_Id) VALUES
    ('2025-06-08 9:00:00','2025-06-09 05:00:00',TRUE,1);
INSERT INTO working_hour (Start_Time, End_Time, Auto_Leave, Employee_Id) VALUES
    ('2025-06-09 16:00:00','2025-06-10 00:00:00',FALSE,1);

INSERT INTO working_hour_segment(Duration, Working_hour_Id,Segment_type_id) VALUES
    ( 480,(SELECT id FROM working_hour wh WHERE DATE(wh.start_time)='2025-06-01'),1);
INSERT INTO working_hour_segment(Duration, Working_hour_Id,Segment_type_id) VALUES
    (120,(SELECT id FROM working_hour wh WHERE DATE(wh.start_time)='2025-06-01'),3);
INSERT INTO working_hour_segment(Duration, Working_hour_Id,Segment_type_id) VALUES
    (480,(SELECT id FROM working_hour wh WHERE DATE(wh.start_time)='2025-06-02'),1);
INSERT INTO working_hour_segment(Duration, Working_hour_Id,Segment_type_id) VALUES
    ( 120,(SELECT id FROM working_hour wh WHERE DATE(wh.start_time)='2025-06-02'),3);
INSERT INTO working_hour_segment(Duration, Working_hour_Id,Segment_type_id) VALUES
    (120,(SELECT id FROM working_hour wh WHERE DATE(wh.start_time)='2025-06-02'),4);
INSERT INTO working_hour_segment(Duration, Working_hour_Id,Segment_type_id) VALUES
    (480,(SELECT id FROM working_hour wh WHERE DATE(wh.start_time)='2025-06-03'),1);
INSERT INTO working_hour_segment(Duration, Working_hour_Id,Segment_type_id) VALUES
    ( 465,(SELECT id FROM working_hour wh WHERE DATE(wh.start_time)='2025-06-04'),1);
INSERT INTO working_hour_segment(Duration, Working_hour_Id,Segment_type_id) VALUES
    (480,(SELECT id FROM working_hour wh WHERE DATE(wh.start_time)='2025-06-05'),1);
INSERT INTO working_hour_segment(Duration, Working_hour_Id,Segment_type_id) VALUES
    (92,(SELECT id FROM working_hour wh WHERE DATE(wh.start_time)='2025-06-05'),3);
INSERT INTO working_hour_segment(Duration, Working_hour_Id,Segment_type_id) VALUES
    ( 480,(SELECT id FROM working_hour wh WHERE DATE(wh.start_time)='2025-06-06'),1);
INSERT INTO working_hour_segment(Duration, Working_hour_Id,Segment_type_id) VALUES
    ( 385,(SELECT id FROM working_hour wh WHERE DATE(wh.start_time)='2025-06-07'),1);
INSERT INTO working_hour_segment(Duration, Working_hour_Id,Segment_type_id) VALUES
    ( 480,(SELECT id FROM working_hour wh WHERE DATE(wh.start_time)='2025-06-08'),1);
INSERT INTO working_hour_segment(Duration, Working_hour_Id,Segment_type_id) VALUES
    ( 300,(SELECT id FROM working_hour wh WHERE DATE(wh.start_time)='2025-06-08'),3);
INSERT INTO working_hour_segment(Duration, Working_hour_Id,Segment_type_id) VALUES
    (420,(SELECT id FROM working_hour wh WHERE DATE(wh.start_time)='2025-06-08'),4);
INSERT INTO working_hour_segment(Duration, Working_hour_Id,Segment_type_id) VALUES
    ( 360,(SELECT id FROM working_hour wh WHERE DATE(wh.start_time)='2025-06-09'),1);
INSERT INTO working_hour_segment(Duration, Working_hour_Id,Segment_type_id) VALUES
    (120,(SELECT id FROM working_hour wh WHERE DATE(wh.start_time)='2025-06-09'),2);

INSERT INTO break (Start_Time, End_Time, Working_hour_Id) VALUES
    ('2025-06-01 14:00:00', '2025-06-01 15:00:00',
     (SELECT id FROM working_hour wh WHERE DATE(wh.start_time)='2025-06-01'));
INSERT INTO break (Start_Time, End_Time, Working_hour_Id) VALUES
    ('2025-06-02 14:00:00', '2025-06-02 14:45:00',
     (SELECT id FROM working_hour wh WHERE DATE(wh.start_time)='2025-06-02'));
INSERT INTO break (Start_Time, End_Time, Working_hour_Id) VALUES
    ('2025-06-03 11:00:00', '2025-06-03 12:10:00',
     (SELECT id FROM working_hour wh WHERE DATE(wh.start_time)='2025-06-03'));
INSERT INTO break (Start_Time, End_Time, Working_hour_Id) VALUES
    ('2025-06-04 12:15:00', '2025-06-04 13:00:00',
     (SELECT id FROM working_hour wh WHERE DATE(wh.start_time)='2025-06-04'));
INSERT INTO break (Start_Time, End_Time, Working_hour_Id) VALUES
    ('2025-06-05 12:00:00', '2025-06-05 13:00:00',
     (SELECT id FROM working_hour wh WHERE DATE(wh.start_time)='2025-06-05'));
INSERT INTO break (Start_Time, End_Time, Working_hour_Id) VALUES
    ('2025-06-06 17:00:00', '2025-06-06 18:00:00',
     (SELECT id FROM working_hour wh WHERE DATE(wh.start_time)='2025-06-06'));
INSERT INTO break (Start_Time, End_Time, Working_hour_Id) VALUES
    ('2025-06-07 14:00:00', '2025-06-07 14:37:00',
     (SELECT id FROM working_hour wh WHERE DATE(wh.start_time)='2025-06-07'));
INSERT INTO break (Start_Time, End_Time, Working_hour_Id) VALUES
    ('2025-06-08 12:00:00', '2025-06-08 12:30:00',
     (SELECT id FROM working_hour wh WHERE DATE(wh.start_time)='2025-06-08'));
INSERT INTO break (Start_Time, End_Time, Working_hour_Id) VALUES
    ('2025-06-08 17:25:00', '2025-06-08 17:55:00',
     (SELECT id FROM working_hour wh WHERE DATE(wh.start_time)='2025-06-08'));
INSERT INTO break (Start_Time, End_Time, Working_hour_Id) VALUES
    ('2025-06-09 22:00:00', '2025-06-09 22:40:00',
     (SELECT id FROM working_hour wh WHERE DATE(wh.start_time)='2025-06-09'));