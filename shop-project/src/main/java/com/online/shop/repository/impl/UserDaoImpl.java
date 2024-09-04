package com.online.shop.repository.impl;

import com.online.shop.entity.*;
import com.online.shop.repository.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class UserDaoImpl implements UserDao {

    private DataSource dataSource;

    @Autowired
    public UserDaoImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public Optional<User> findById(Long id) {
        try (Connection connection = dataSource.getConnection()) {
            User user = getUserById(id);
            if (user != null) {
                user.setRole(getRoleByUserId(id));
                user.setTransactions(getTransactionsByUserId(id));
                user.setCourses(getCoursesByUserId(id));
            }
            return Optional.ofNullable(user);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private User getUserById(Long id) throws SQLException {
        String QUERY_GET_USER_BY_ID = "SELECT * FROM users WHERE id = ?";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement userStatement = connection.prepareStatement(QUERY_GET_USER_BY_ID)) {
            userStatement.setLong(1, id);
            ResultSet userResultSet = userStatement.executeQuery();
            if (userResultSet.next()) {
                return User.builder()
                        .id(userResultSet.getLong("id"))
                        .email(userResultSet.getString("email"))
                        .password(userResultSet.getString("password"))
                        .createdAt(userResultSet.getTimestamp("created_at").toLocalDateTime())
                        .build();
            }
        }
        return null;
    }

    private Role getRoleByUserId(Long userId) throws SQLException {
        String QUERY_GET_ROLE_BY_USER = "SELECT r.* FROM roles r JOIN users u ON u.role_id = r.id WHERE u.id = ?";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement roleStatement = connection.prepareStatement(QUERY_GET_ROLE_BY_USER)) {
            roleStatement.setLong(1, userId);
            ResultSet roleResultSet = roleStatement.executeQuery();
            if (roleResultSet.next()) {
                return Role.builder()
                        .id(roleResultSet.getLong("id"))
                        .name(roleResultSet.getString("name"))
                        .description(roleResultSet.getString("description"))
                        .build();
            }
        }
        return null;
    }

    private List<Transaction> getTransactionsByUserId(Long userId) throws SQLException {
        String QUERY_GET_TRANSACTIONS_BY_USER = "SELECT t.*, c.title, c.description, c.created_at FROM transactions t " +
                "JOIN courses c ON c.id = t.course_id " +
                "JOIN users u ON u.id = t.user_id WHERE u.id = ?";
        List<Transaction> userTransactions = new ArrayList<>();
        try (Connection connection = dataSource.getConnection();
             PreparedStatement transactionStatement = connection.prepareStatement(QUERY_GET_TRANSACTIONS_BY_USER)) {
            transactionStatement.setLong(1, userId);
            ResultSet transactionResultSet = transactionStatement.executeQuery();
            while (transactionResultSet.next()) {
                Transaction transaction = Transaction.builder()
                        .id(transactionResultSet.getLong("id"))
                        .price(transactionResultSet.getBigDecimal("price"))
                        .dateTime(transactionResultSet.getTimestamp("datetime").toLocalDateTime())
                        .build();
                userTransactions.add(transaction);
            }
        }
        return userTransactions;
    }

    private List<Course> getCoursesByUserId(Long userId) throws SQLException {
        String QUERY_GET_COURSES_BY_USER = "SELECT t.*, c.title, c.description, c.created_at, cc.duration, cc.practice_count, cc.lesson_count FROM transactions t\n" +
                "JOIN courses c ON c.id = t.course_id\n" +
                "JOIN course_plans cc ON c.id = cc.id\n" +
                "JOIN users u ON u.id = t.user_id WHERE u.id = ?;";
        List<Course> userCourses = new ArrayList<>();
        try (Connection connection = dataSource.getConnection();
             PreparedStatement courseStatement = connection.prepareStatement(QUERY_GET_COURSES_BY_USER)) {
            courseStatement.setLong(1, userId);
            ResultSet courseResultSet = courseStatement.executeQuery();
            while (courseResultSet.next()) {
                CoursePlan coursePlan = CoursePlan.builder()
                        .id(courseResultSet.getLong("id"))
                        .practiceCount(courseResultSet.getInt("practice_count"))
                        .lessonCount(courseResultSet.getInt("lesson_count"))
                        .duration(courseResultSet.getInt("duration"))
                        .build();

                Course course = Course.builder()
                        .id(courseResultSet.getLong("course_id"))
                        .title(courseResultSet.getString("title"))
                        .price(courseResultSet.getBigDecimal("price"))
                        .description(courseResultSet.getString("description"))
                        .createdAt(courseResultSet.getTimestamp("created_at").toLocalDateTime())
                        .coursePlan(coursePlan)
                        .reviews(getReviewsByCourseId(courseResultSet.getLong("course_id")))
                        .categories(getCategoriesByUserId(courseResultSet.getLong("course_id")))
                        .build();

                userCourses.add(course);
            }
        }
        return userCourses;
    }

    private List<Review> getReviewsByCourseId(Long courseId) throws SQLException {
        String QUERY_GET_REVIEWS_BY_COURSE = "SELECT r.* FROM reviews r JOIN courses c ON r.course_id = c.id WHERE c.id = ?";
        List<Review> reviewsCourse = new ArrayList<>();
        try (Connection connection = dataSource.getConnection();
             PreparedStatement reviewsCourseStatement = connection.prepareStatement(QUERY_GET_REVIEWS_BY_COURSE)) {
            reviewsCourseStatement.setLong(1, courseId);
            ResultSet reviewsCourseResultSet = reviewsCourseStatement.executeQuery();

            while (reviewsCourseResultSet.next()) {
                User user = User.builder()
                        .id(reviewsCourseResultSet.getLong("user_id"))
                        .build();

                Review review = Review.builder()
                        .id(reviewsCourseResultSet.getLong("id"))
                        .content(reviewsCourseResultSet.getString("content"))
                        .rating(reviewsCourseResultSet.getInt("rating"))
                        .createdAt(reviewsCourseResultSet.getTimestamp("created_at").toLocalDateTime())
                        .user(user)
                        .build();

                reviewsCourse.add(review);
            }
        }
        return reviewsCourse;
    }

    private List<Category> getCategoriesByUserId(Long courseId) throws SQLException {
        String QUERY_GET_CATEGORIES_BY_COURSE = "SELECT category.* FROM categories category JOIN course_categories cc ON category.id = cc.category_id WHERE cc.course_id = ?";
        List<Category> categories = new ArrayList<>();
        try (Connection connection = dataSource.getConnection();
             PreparedStatement categoriesCourseStatement = connection.prepareStatement(QUERY_GET_CATEGORIES_BY_COURSE)) {
            categoriesCourseStatement.setLong(1, courseId);
            ResultSet categoriesCourseResultSet = categoriesCourseStatement.executeQuery();

            while (categoriesCourseResultSet.next()) {
                Category category = Category.builder()
                        .id(categoriesCourseResultSet.getLong("id"))
                        .name(categoriesCourseResultSet.getString("name"))
                        .description(categoriesCourseResultSet.getString("description"))
                        .build();
                categories.add(category);
            }
        }
        return categories;
    }

    @Override
    public List<User> getAll() {
        List<User> users = new ArrayList<>();
        String QUERY_GET_ALL_USERS = "SELECT * FROM users";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement userStatement = connection.prepareStatement(QUERY_GET_ALL_USERS);
             ResultSet userResultSet = userStatement.executeQuery()) {

            while (userResultSet.next()) {
                User user = User.builder()
                        .id(userResultSet.getLong("id"))
                        .email(userResultSet.getString("email"))
                        .password(userResultSet.getString("password"))
                        .createdAt(userResultSet.getTimestamp("created_at").toLocalDateTime())
                        .role(getRoleByUserId(userResultSet.getLong("id")))
                        .transactions(getTransactionsByUserId(userResultSet.getLong("id")))
                        .courses(getCoursesByUserId(userResultSet.getLong("id")))
                        .build();
                users.add(user);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return users;
    }

    @Override
    public Long insert(User entity) {
        String QUERY_INSERT_USER = "INSERT INTO users (email, password, role_id) VALUES (?, ?, ?) RETURNING id";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement insertStatement = connection.prepareStatement(QUERY_INSERT_USER)) {

            insertStatement.setString(1, entity.getEmail());
            insertStatement.setString(2, entity.getPassword());
            insertStatement.setLong(3, entity.getRole().getId());
            ResultSet rs = insertStatement.executeQuery();

            if (rs.next()) {
                return rs.getLong("id");
            } else {
                throw new SQLException("Failed to insert user");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Optional<Optional<User>> update(Long id, User entity) {
        String QUERY_UPDATE_USER = "UPDATE users SET email = ?, password = ?, role_id = ? WHERE id = ?";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement updateUser = connection.prepareStatement(QUERY_UPDATE_USER)) {
            updateUser.setString(1, entity.getEmail());
            updateUser.setString(2, entity.getPassword());
            updateUser.setLong(3, entity.getRole().getId());
            updateUser.setLong(4, id);
            updateUser.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return Optional.ofNullable(findById(id));
    }

    @Override
    public Boolean delete(Long id) {
        String QUERY_DELETE_USER = "DELETE FROM users WHERE id = ?";
        int affectedRows = 0;

        try (Connection connection = dataSource.getConnection();
             PreparedStatement deleteUser = connection.prepareStatement(QUERY_DELETE_USER)) {
            deleteUser.setLong(1, id);
            affectedRows = deleteUser.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return affectedRows > 0;
    }

}