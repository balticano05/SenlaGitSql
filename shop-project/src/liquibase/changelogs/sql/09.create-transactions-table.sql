CREATE TABLE transactions(
     id BIGSERIAL,
     user_id BIGINT NOT NULL,
     course_id INT NOT NULL,
     datetime TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
     price DECIMAL(10,2) NOT NULL CHECK (price >= 0),
     PRIMARY KEY (user_id, course_id),
     FOREIGN KEY (user_id) REFERENCES users(id),
     FOREIGN KEY (course_id) REFERENCES courses(id)
);
