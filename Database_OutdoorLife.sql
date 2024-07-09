USE SWP1;
GO

-- Create ACCOUNT table
CREATE TABLE ACCOUNT (
    Account_id INT PRIMARY KEY IDENTITY(1,1),
    first_name NVARCHAR(255),
    last_name NVARCHAR(255),
    Gmail NVARCHAR(MAX),
    phone_number VARCHAR(15),
    isAdmin BIT,
    isUser BIT,
    passwordHash VARCHAR(256)
);
GO

-- Create STAFF table
CREATE TABLE STAFF (
    Staff_id INT PRIMARY KEY IDENTITY(1,1),
    Account_id INT,
    BirthDate DATETIME,
    PersonID INT,
    Address NVARCHAR(MAX),
    FOREIGN KEY (Account_id) REFERENCES ACCOUNT(Account_id)
);
GO

-- Create PRICE table
CREATE TABLE PRICE (
    Price_id INT PRIMARY KEY IDENTITY(1,1),
    StartDate DATETIME,
    EndDate DATETIME,
    Price DECIMAL(10, 2)
);
GO

-- Create CAMPSITE table
CREATE TABLE CAMPSITE (
    Campsite_id INT PRIMARY KEY IDENTITY(1,1),
    Price_id INT,
    Address NVARCHAR(255),
    Name NVARCHAR(255),
    Description NVARCHAR(MAX),
    Status BIT,
    Image VARBINARY(MAX),
    Limite INT,
    FOREIGN KEY (Price_id) REFERENCES PRICE(Price_id)
);
GO

-- Create GEAR table
CREATE TABLE GEAR (
    Gear_id INT PRIMARY KEY IDENTITY(1,1),
    Price_id INT,
    Name NVARCHAR(255),
    Description NVARCHAR(MAX),
    Image NVARCHAR(255),
    FOREIGN KEY (Price_id) REFERENCES PRICE(Price_id)
);
GO

-- Create NOTIFICATION table
CREATE TABLE NOTIFICATION (
    Notification_id INT PRIMARY KEY IDENTITY(1,1),
    Sender INT,
    Receiver INT,
    Content NVARCHAR(MAX),
    URL NVARCHAR(MAX),
    TimeStamp DATETIME,
    title NVARCHAR(255),
    Status BIT,
    FOREIGN KEY (Sender) REFERENCES ACCOUNT(Account_id),
    FOREIGN KEY (Receiver) REFERENCES ACCOUNT(Account_id)
);
GO

-- Create FEEDBACK table
CREATE TABLE FEEDBACK (
    Feedback_id INT PRIMARY KEY IDENTITY(1,1),
    Campsite_id INT,
    StarNumber INT,
    TimeStamp DATETIME,
    Reviewer INT,
    Content NVARCHAR(MAX),
    FOREIGN KEY (Campsite_id) REFERENCES CAMPSITE(Campsite_id),
    FOREIGN KEY (Reviewer) REFERENCES ACCOUNT(Account_id)
);
GO

-- Create ORDERS table
CREATE TABLE ORDERS (
    Orders_id INT PRIMARY KEY IDENTITY(1,1) NOT NULL,
    TimeStamp DATETIME,
    Booker INT,
    Book_id INT NOT NULL,
    Quantity INT,
    StartDate DATETIME,
    EndDate DATETIME,
    ApproveStatus BIT,
    PaymentStatus BIT,
    FOREIGN KEY (Booker) REFERENCES ACCOUNT(Account_id)
);
GO

-- Create ORDER_DETAIL table
CREATE TABLE ORDER_DETAIL (
    OrderDetail_id INT PRIMARY KEY IDENTITY(1,1),
    Orders_id INT,
    Type BIT,  -- 0 for Campsite, 1 for Gear
    Item_id INT,
    Number INT,
    FOREIGN KEY (Orders_id) REFERENCES ORDERS(Orders_id),
    -- Need to handle Type-specific foreign key relationships in the application logic
    -- Use CHECK constraints to ensure valid item types
    CONSTRAINT FK_OrderDetail_Campsite FOREIGN KEY (Item_id) REFERENCES CAMPSITE(Campsite_id),
    CONSTRAINT FK_OrderDetail_Gear FOREIGN KEY (Item_id) REFERENCES GEAR(Gear_id)
);
GO

-- Create ORDER_REPORT table
CREATE TABLE ORDER_REPORT (
    OrderReport_id INT PRIMARY KEY IDENTITY(1,1),
    Staff_ID INT,
    TimeStamp DATETIME,
    Type BIT,  -- Define the meaning of this field in your application logic
    Order_ID INT,
    Status BIT,
    Description_report NVARCHAR(MAX),
    FOREIGN KEY (Staff_ID) REFERENCES STAFF(Staff_id),
    FOREIGN KEY (Order_ID) REFERENCES ORDERS(Orders_id)
);
GO

-- Insert initial data into ACCOUNT table
INSERT INTO ACCOUNT (first_name, last_name, Gmail, phone_number, isAdmin, isUser, passwordHash)
VALUES ('Admin', 'ad', 'admin@gmail.com', '0762584567', 0, 0, '123');
GO

-- Insert data into PRICE table
INSERT INTO PRICE (StartDate, EndDate, Price) VALUES
('2023-01-01', '2023-01-31', 100000.00),
('2023-02-01', '2023-02-28', 110000.00),
('2023-03-01', '2023-03-31', 120000.00),
('2023-04-01', '2023-04-30', 130000.00),
('2023-05-01', '2023-05-31', 140000.00),
('2023-06-01', '2023-06-30', 150000.00),
('2023-07-01', '2023-07-31', 160000.00),
('2023-08-01', '2023-08-31', 170000.00),
('2023-09-01', '2023-09-30', 180000.00),
('2023-10-01', '2023-10-31', 190000.00),
('2023-11-01', '2023-11-30', 200000.00),
('2023-12-01', '2023-12-31', 210000.00),
('2024-01-01', '2024-01-31', 220000.00),
('2024-02-01', '2024-02-29', 230000.00),
('2024-03-01', '2024-03-31', 240000.00),
('2024-04-01', '2024-04-30', 250000.00),
('2024-05-01', '2024-05-31', 260000.00),
('2024-06-01', '2024-06-30', 270000.00),
('2024-07-01', '2024-07-31', 280000.00),
('2024-08-01', '2024-08-31', 290000.00),
('2024-09-01', '2024-09-30', 300000.00),
('2024-10-01', '2024-10-31', 310000.00),
('2024-11-01', '2024-11-30', 320000.00),
('2024-12-01', '2024-12-31', 330000.00),
('2025-01-01', '2025-01-31', 340000.00),
('2025-02-01', '2025-02-28', 350000.00),
('2025-03-01', '2025-03-31', 360000.00),
('2025-04-01', '2025-04-30', 370000.00),
('2025-05-01', '2025-05-31', 380000.00),
('2025-06-01', '2025-06-30', 390000.00);
GO

-- Insert data into GEAR table

INSERT INTO GEAR (Price_id, Name, Description, Image) VALUES
(1, N'Túi ngủ', N'Túi ngủ mùa đông giữ ấm tốt, chất liệu mềm mại, thích hợp cho những chuyến đi trong mùa lạnh.', 'tui_ngu.jpg'),
(2, N'Bếp ga du lịch', N'Bếp ga nhỏ gọn, tiện lợi khi mang theo, tiết kiệm nhiên liệu và dễ sử dụng.', 'bep_ga.jpg'),
(3, N'Bình nước', N'Bình nước giữ nhiệt 1 lít, giữ lạnh và giữ nóng tốt, thiết kế chắc chắn, dễ mang theo.', 'binh_nuoc.jpg'),
(4, N'Ba lô du lịch', N'Ba lô du lịch chống nước, dung tích 50L, nhiều ngăn tiện lợi, dây đeo êm ái, phù hợp cho những chuyến đi dài.', 'ba_lo.jpg'),
(5, N'Đèn pin', N'Đèn pin LED siêu sáng, sạc được, thời gian chiếu sáng lâu, thích hợp cho các hoạt động ngoài trời.', 'den_pin.jpg'),
(6, N'Dụng cụ nấu ăn', N'Bộ dụng cụ nấu ăn gọn nhẹ cho cắm trại, bao gồm nồi, chảo, và dụng cụ ăn uống, chất liệu an toàn.', 'dung_cu_nau_an.jpg'),
(7, N'Tấm trải picnic', N'Tấm trải picnic chống thấm nước, dễ dàng lau sạch, kích thước lớn, thích hợp cho gia đình.', 'tam_trai.jpg'),
(8, N'Dây thừng', N'Dây thừng cắm trại chịu lực cao, độ bền tốt, đa dụng, phù hợp cho nhiều mục đích khác nhau trong cắm trại.', 'day_thung.jpg'),
(9, N'Gậy đi bộ', N'Gậy đi bộ điều chỉnh được độ dài, chất liệu nhẹ, tay cầm êm, hỗ trợ tốt trong việc di chuyển trên địa hình khó khăn.', 'gay_di_bo.jpg'),
(10, N'Đèn pin siêu sáng', N'Đèn pin LED siêu sáng, thích hợp cho các hoạt động ngoài trời vào buổi tối.', 'den_pin.jpg'),
(2, N'Giường ngủ xách tay', N'Giường ngủ xách tay nhẹ nhàng, dễ dàng mang theo khi đi cắm trại hoặc du lịch.', 'giuong_ngu.jpg'),
(3, N'Bình nước giữ nhiệt', N'Bình nước giữ nhiệt 500ml, giữ nước nóng hoặc lạnh trong thời gian dài.', 'binh_nuoc.jpg'),
(4, N'Ghế gập di động', N'Ghế gập di động nhẹ nhàng, tiện lợi cho việc ngồi nghỉ trong các chuyến đi dã ngoại.', 'ghe_gap.jpg'),
(5, N'Bếp cồn mini', N'Bếp cồn mini gọn nhẹ, dễ dàng sử dụng để nấu ăn trong các chuyến picnic hoặc cắm trại.', 'bep_con_mini.jpg'),
(6, N'Áo khoác chống nước', N'Áo khoác chống nước phù hợp cho mọi điều kiện thời tiết, giữ bạn khô ráo khi trời mưa.', 'ao_khoac.jpg'),
(7, N'Dây thừng chịu lực', N'Dây thừng chịu lực cao, thích hợp cho việc cắm trại, treo đồ hoặc xây dựng trại.', 'day_thung.jpg'),
(8, N'Bình đựng nước siêu nhẹ', N'Bình đựng nước siêu nhẹ và chắc chắn, dễ dàng mang theo khi leo núi hoặc trekking.', 'binh_dung_nuoc.jpg'),
(9, N'Nồi cơm điện du lịch', N'Nồi cơm điện du lịch nhỏ gọn, tiện lợi để nấu cơm trong các chuyến đi dã ngoại.', 'noi_com_dien.jpg'),
(10, N'Găng tay cách nhiệt', N'Găng tay cách nhiệt giữ ấm tốt, bảo vệ tay khi thực hiện các hoạt động ngoài trời vào mùa đông.', 'gang_tay.jpg'),
(1, N'Lều cắm trại', N'Lều cắm trại chống nước cho 4 người, dễ dàng dựng và gấp gọn, phù hợp cho mọi địa hình.', 'leu_cam_trai.jpg'),
(2, N'Lều 2 lớp chống mưa', N'Lều 2 lớp chống mưa, chất liệu bền đẹp, dễ dàng di chuyển và sử dụng.', 'leu_2_lop.jpg'),
(3, N'Lều dã ngoại nhỏ', N'Lều dã ngoại nhỏ, dễ dàng gấp gọn và mang theo, phù hợp cho các chuyến đi trekking.', 'leu_da_ngoai.jpg'),
(4, N'Lều cho gia đình', N'Lều cắm trại cho gia đình, thiết kế rộng rãi, thoải mái và an toàn cho mọi thành viên.', 'leu_gia_dinh.jpg'),
(5, N'Lều chống gió', N'Lều cắm trại chống gió, chống nước, giữ ấm tốt trong mọi điều kiện thời tiết khắc nghiệt.', 'leu_chong_gio.jpg'),
(6, N'Lều cắm trại cho mùa đông', N'Lều cắm trại chuyên dùng cho mùa đông, chống nước, giữ ấm tốt, bền đẹp.', 'leu_mua_dong.jpg'),
(7, N'Lều cắm trại 3 mùa', N'Lều cắm trại đa dụng, phù hợp cho 3 mùa: xuân, hạ, thu, chống nước và giữ ấm.', 'leu_3_mua.jpg'),
(8, N'Lều dành cho cặp đôi', N'Lều cắm trại dành cho cặp đôi, thiết kế nhỏ gọn, thoải mái, lãng mạn.', 'leu_doi.jpg'),
(9, N'Lều cắm trại một người', N'Lều cắm trại đơn giản dành cho một người, gấp gọn, tiện lợi cho du lịch đơn.', 'leu_mot_nguoi.jpg'),
(10, N'Lều cắm trại cho các chuyến trekking', N'Lều cắm trại chuyên dùng cho các chuyến trekking, nhẹ nhàng, dễ dàng lắp ráp.', 'leu_trekking.jpg'),
(11, N'Lều cắm trại 4 người', N'Lều cắm trại cho 4 người, chống nước tốt, dễ dàng lắp ráp và gấp gọn.', 'leu_4_nguoi.jpg');
GO