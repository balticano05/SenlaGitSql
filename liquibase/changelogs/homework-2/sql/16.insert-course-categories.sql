
INSERT INTO course_categories (course_id, category_id)
    SELECT course_id, category_id
    FROM (
        VALUES
            (1, 1),
            (2, 2),
            (3, 1),
            (4, 1),
            (5, 1),
            (6, 3),
            (7, 3),
            (8, 3),
            (9, 3),
            (10, 3),
            (11, 5),
            (12, 5),
            (13, 5),
            (14, 5),
            (15, 5),
            (16, 4),
            (17, 4),
            (18, 4),
            (19, 4),
            (20, 4),
            (21, 2),
            (22, 2),
            (23, 2),
            (24, 2),
            (25, 2),
            (26, 6),
            (27, 6),
            (28, 6),
            (29, 6),
            (30, 6)
         ) AS t (course_id, category_id)
WHERE NOT EXISTS (SELECT 1 FROM course_categories);
