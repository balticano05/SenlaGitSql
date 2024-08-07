INSERT INTO users (email, password, role_id, created_at)
    SELECT email, password, role_id, created_at
    FROM (
         VALUES
             ('admin1.shopcourses@gmail.com', 'd79a734b37ccf46f8b07d7b61b7f6e62', 2, '2024-01-01 08:00:00'),
             ('admin2.shopcourses@gmail.com', 'f67b7c9a41038d77d8dcf35a0a973c05', 2, '2024-01-02 09:00:00'),
             ('mainadmin.shopcourses@gmail.com', '0e6c3e7e0841c6b3286a8a6b6b4a22f0', 2, '2024-01-03 10:00:00'),
             ('alice.smith@gmail.com', '482c811da5d5b4bc6d497fff5d0e0fbd', 1, '2024-02-01 11:00:00'),
             ('bob.johnson@gmail.com', 'e99a18c428cb38d5f260853678922e03', 1, '2024-02-05 12:00:00'),
             ('charlie.brown@gmail.com', 'd5e3d6a2b4d9a87373b40a39d70db6d4', 1, '2024-03-01 13:00:00'),
             ('dave.williams@gmail.com', 'fbf83e7db43d16a48e3a5a8a5828b59d', 1, '2024-03-15 14:00:00'),
             ('eve.davis@gmail.com', 'b6d9a1f4a3d7b47c7b23eec585f5b8f3', 1, '2024-04-01 15:00:00'),
             ('frank.miller@gmail.com', '17a4d75e609f5e16b7ed3f1c5d45f3d5', 1, '2024-04-10 16:00:00'),
             ('grace.moore@gmail.com', '46e0f757b6d4595b38eab3e5e7dc0b9b', 1, '2024-05-01 17:00:00'),
             ('helen.taylor@gmail.com', '52e586d97d7dcac9c2b594d91cfbb67b', 1, '2024-05-15 18:00:00'),
             ('ivan.anderson@gmail.com', '81e0d7b6d8b788d96cb3d0cf12e8c8b2', 1, '2024-06-01 19:00:00'),
             ('julia.thomas@gmail.com', 'a080e078f42f6842f8f5c2f0648059ef', 1, '2024-06-15 20:00:00'),
             ('karen.white@gmail.com', 'a74c04436f7119e59f8c8b059b92c95e', 1, '2024-07-01 21:00:00'),
             ('luke.martin@gmail.com', '7b9b4d51caa0c26e2d9d75c219f748b8', 1, '2024-07-15 22:00:00'),
             ('michael.clark@gmail.com', '7e22a4d64d99e0f95bb50b5c91a046a1', 1, '2024-08-01 23:00:00'),
             ('nancy.lewis@gmail.com', 'a7e6c5a7c89758e5a3c28bfc07ed8b83', 1, '2024-08-15 08:00:00'),
             ('oliver.king@gmail.com', 'e9b9b8f6f5e8d8a50e8f95d87db4a0a5', 1, '2024-09-01 09:00:00'),
             ('paul.lee@gmail.com', '8d7f7413b2f41f605c01cf1b7cc7e5f3', 1, '2024-09-15 10:00:00'),
             ('quincy.hall@gmail.com', '7c36a2a107a3bb73e2b4841e20fc8b2a', 1, '2024-10-01 11:00:00'),
             ('rachel.scott@gmail.com', 'e5a87cb9b1f6e79a19d068c1b7592d0c', 1, '2024-10-15 12:00:00'),
             ('simon.young@gmail.com', 'a2a6c24d0d29db51fa8b4240bbba5366', 1, '2024-11-01 13:00:00'),
             ('terry.adams@gmail.com', '02c3d2298bb8d3fd8f04e46e87349a83', 1, '2024-11-15 14:00:00'),
             ('ursula.wright@gmail.com', 'd7d3f51e26463f55d5e7396a823b0a3a', 1, '2024-12-01 15:00:00'),
             ('victor.green@gmail.com', '3bda3cba635a74001ad38c5d752b796d', 1, '2024-12-15 16:00:00'),
             ('wendy.hill@gmail.com', '6f091f0e83711c46e646e6efb05f4a22', 1, '2025-01-01 17:00:00')
     ) AS t (email, password, role_id, created_at)
AS t (email, password, role_id) WHERE NOT EXISTS (SELECT 1 FROM users);