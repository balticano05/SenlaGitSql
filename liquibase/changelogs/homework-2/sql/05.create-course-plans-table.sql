CREATE TABLE course_plans (
    id BIGSERIAL PRIMARY KEY,
    lesson_count INTEGER NOT NULL CHECK (lesson_count >= 0),
    practice_count INTEGER NOT NULL CHECK (practice_count >= 0),
    duration INTEGER NOT NULL CHECK (duration >= 0),
    FOREIGN KEY (id) REFERENCES courses(id) ON DELETE CASCADE
);