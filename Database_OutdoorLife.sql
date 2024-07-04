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
(1, N'Túi ng?', N'Túi ng? mùa ?ông gi? ?m t?t, ch?t li?u m?m m?i, thích h?p cho nh?ng chuy?n ?i trong mùa l?nh.', 'tui_ngu.jpg'),
(2, N'B?p ga du l?ch', N'B?p ga nh? g?n, ti?n l?i khi mang theo, ti?t ki?m nhiên li?u và d? s? d?ng.', 'bep_ga.jpg'),
(3, N'Bình n??c', N'Bình n??c gi? nhi?t 1 lít, gi? l?nh và gi? nóng t?t, thi?t k? ch?c ch?n, d? mang theo.', 'binh_nuoc.jpg'),
(4, N'Ba lô du l?ch', N'Ba lô du l?ch ch?ng n??c, dung tích 50L, nhi?u ng?n ti?n l?i, dây ?eo êm ái, phù h?p cho nh?ng chuy?n ?i dài.', 'ba_lo.jpg'),
(5, N'?èn pin', N'?èn pin LED siêu sáng, s?c ???c, th?i gian chi?u sáng lâu, thích h?p cho các ho?t ??ng ngoài tr?i.', 'den_pin.jpg'),
(6, N'D?ng c? n?u ?n', N'B? d?ng c? n?u ?n g?n nh? cho c?m tr?i, bao g?m n?i, ch?o, và d?ng c? ?n u?ng, ch?t li?u an toàn.', 'dung_cu_nau_an.jpg'),
(7, N'T?m tr?i picnic', N'T?m tr?i picnic ch?ng th?m n??c, d? dàng lau s?ch, kích th??c l?n, thích h?p cho gia ?ình.', 'tam_trai.jpg'),
(8, N'Dây th?ng', N'Dây th?ng c?m tr?i ch?u l?c cao, ?? b?n t?t, ?a d?ng, phù h?p cho nhi?u m?c ?ích khác nhau trong c?m tr?i.', 'day_thung.jpg'),
(9, N'G?y ?i b?', N'G?y ?i b? ?i?u ch?nh ???c ?? dài, ch?t li?u nh?, tay c?m êm, h? tr? t?t trong vi?c di chuy?n trên ??a hình khó kh?n.', 'gay_di_bo.jpg'),
(10, N'?èn pin siêu sáng', N'?èn pin LED siêu sáng, thích h?p cho các ho?t ??ng ngoài tr?i vào bu?i t?i.', 'den_pin.jpg'),
(2, N'Gi??ng ng? xách tay', N'Gi??ng ng? xách tay nh? nhàng, d? dàng mang theo khi ?i c?m tr?i ho?c du l?ch.', 'giuong_ngu.jpg'),
(3, N'Bình n??c gi? nhi?t', N'Bình n??c gi? nhi?t 500ml, gi? n??c nóng ho?c l?nh trong th?i gian dài.', 'binh_nuoc.jpg'),
(4, N'Gh? g?p di ??ng', N'Gh? g?p di ??ng nh? nhàng, ti?n l?i cho vi?c ng?i ngh? trong các chuy?n ?i dã ngo?i.', 'ghe_gap.jpg'),
(5, N'B?p c?n mini', N'B?p c?n mini g?n nh?, d? dàng s? d?ng ?? n?u ?n trong các chuy?n picnic ho?c c?m tr?i.', 'bep_con_mini.jpg'),
(6, N'Áo khoác ch?ng n??c', N'Áo khoác ch?ng n??c phù h?p cho m?i ?i?u ki?n th?i ti?t, gi? b?n khô ráo khi tr?i m?a.', 'ao_khoac.jpg'),
(7, N'Dây th?ng ch?u l?c', N'Dây th?ng ch?u l?c cao, thích h?p cho vi?c c?m tr?i, treo ?? ho?c xây d?ng tr?i.', 'day_thung.jpg'),
(8, N'Bình ??ng n??c siêu nh?', N'Bình ??ng n??c siêu nh? và ch?c ch?n, d? dàng mang theo khi leo núi ho?c trekking.', 'binh_dung_nuoc.jpg'),
(9, N'N?i c?m ?i?n du l?ch', N'N?i c?m ?i?n du l?ch nh? g?n, ti?n l?i ?? n?u c?m trong các chuy?n ?i dã ngo?i.', 'noi_com_dien.jpg'),
(10, N'G?ng tay cách nhi?t', N'G?ng tay cách nhi?t gi? ?m t?t, b?o v? tay khi th?c hi?n các ho?t ??ng ngoài tr?i vào mùa ?ông.', 'gang_tay.jpg'),
(1, N'L?u c?m tr?i', N'L?u c?m tr?i ch?ng n??c cho 4 ng??i, d? dàng d?ng và g?p g?n, phù h?p cho m?i ??a hình.', 'leu_cam_trai.jpg'),
(2, N'L?u 2 l?p ch?ng m?a', N'L?u 2 l?p ch?ng m?a, ch?t li?u b?n ??p, d? dàng di chuy?n và s? d?ng.', 'leu_2_lop.jpg'),
(3, N'L?u dã ngo?i nh?', N'L?u dã ngo?i nh?, d? dàng g?p g?n và mang theo, phù h?p cho các chuy?n ?i trekking.', 'leu_da_ngoai.jpg'),
(4, N'L?u cho gia ?ình', N'L?u c?m tr?i cho gia ?ình, thi?t k? r?ng rãi, tho?i mái và an toàn cho m?i thành viên.', 'leu_gia_dinh.jpg'),
(5, N'L?u ch?ng gió', N'L?u c?m tr?i ch?ng gió, ch?ng n??c, gi? ?m t?t trong m?i ?i?u ki?n th?i ti?t kh?c nghi?t.', 'leu_chong_gio.jpg'),
(6, N'L?u c?m tr?i cho mùa ?ông', N'L?u c?m tr?i chuyên dùng cho mùa ?ông, ch?ng n??c, gi? ?m t?t, b?n ??p.', 'leu_mua_dong.jpg'),
(7, N'L?u c?m tr?i 3 mùa', N'L?u c?m tr?i ?a d?ng, phù h?p cho 3 mùa: xuân, h?, thu, ch?ng n??c và gi? ?m.', 'leu_3_mua.jpg'),
(8, N'L?u dành cho c?p ?ôi', N'L?u c?m tr?i dành cho c?p ?ôi, thi?t k? nh? g?n, tho?i mái, lãng m?n.', 'leu_doi.jpg'),
(9, N'L?u c?m tr?i m?t ng??i', N'L?u c?m tr?i ??n gi?n dành cho m?t ng??i, g?p g?n, ti?n l?i cho du l?ch ??n.', 'leu_mot_nguoi.jpg'),
(10, N'L?u c?m tr?i cho các chuy?n trekking', N'L?u c?m tr?i chuyên dùng cho các chuy?n trekking, nh? nhàng, d? dàng l?p ráp.', 'leu_trekking.jpg'),
(11, N'L?u c?m tr?i 4 ng??i', N'L?u c?m tr?i cho 4 ng??i, ch?ng n??c t?t, d? dàng l?p ráp và g?p g?n.', 'leu_4_nguoi.jpg');
GO
