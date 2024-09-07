package com.online.shop.repository.impl;

import com.online.shop.database.ConnectionHolder;
import com.online.shop.entity.*;
import com.online.shop.repository.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class UserDaoImpl implements UserDao {

    private ConnectionHolder connectionHolder;

    @Autowired
    public UserDaoImpl(ConnectionHolder connectionHolder) {
        this.connectionHolder = connectionHolder;
    }

    @Override
    public Optional<User> getById(Long id) {
        Connection connection = connectionHolder.getConnection();
        try {
            User user = getUserById(id);
            if (user != null) {
                user.setTransactions(getUserTransactions(id));
                user.setCourses(getUserCourses(id));
            }
            return Optional.ofNullable(user);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private User getUserById(Long id) throws SQLException {
        String QUERY_GET_USER_WITH_ROLE_BY_ID =
                "SELECT u.*, r.id AS role_id, r.name AS role_name, r.description AS role_description " +
                        "FROM users u " +
                        "JOIN roles r ON u.role_id = r.id " +
                        "WHERE u.id = ?";
        Connection connection = connectionHolder.getConnection();
        try (PreparedStatement statement = connection.prepareStatement(QUERY_GET_USER_WITH_ROLE_BY_ID)) {
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                Role role = Role.builder()
                        .id(resultSet.getLong("role_id"))
                        .name(resultSet.getString("role_name"))
                        .description(resultSet.getString("role_description"))
                        .build();
                return User.builder()
                        .id(resultSet.getLong("id"))
                        .email(resultSet.getString("email"))
                        .password(resultSet.getString("password"))
                        .createdAt(resultSet.getTimestamp("created_at").toLocalDateTime())
                        .role(role)
                        .build();
            }
        }
        return null;
    }

    private List<Transaction> getUserTransactions(Long userId) throws SQLException {
        String QUERY_GET_TRANSACTIONS_BY_USER = "SELECT t.*, c.title, c.description, c.created_at FROM transactions t " +
                "JOIN courses c ON c.id = t.course_id " +
                "JOIN users u ON u.id = t.user_id WHERE u.id = ?";
        Connection connection = connectionHolder.getConnection();
        List<Transaction> userTransactions = new ArrayList<>();
        try (PreparedStatement transactionStatement = connection.prepareStatement(QUERY_GET_TRANSACTIONS_BY_USER)) {
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

    private List<Course> getUserCourses(Long userId) throws SQLException {
        String QUERY_GET_COURSES_BY_USER = "SELECT t.*, c.title, c.description, c.created_at, cc.duration, cc.practice_count, cc.lesson_count FROM transactions t\n" +
                "JOIN courses c ON c.id = t.course_id\n" +
                "JOIN course_plans cc ON c.id = cc.id\n" +
                "JOIN users u ON u.id = t.user_id WHERE u.id = ?;";
        List<Course> userCourses = new ArrayList<>();
        Connection connection = connectionHolder.getConnection();
        try (PreparedStatement courseStatement = connection.prepareStatement(QUERY_GET_COURSES_BY_USER)) {
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
                        .reviews(getCourseReviews(courseResultSet.getLong("course_id")))
                        .categories(getCourseCategories(courseResultSet.getLong("course_id")))
                        .build();

                userCourses.add(course);
            }
        }
        return userCourses;
    }

    private List<Review> getCourseReviews(Long courseId) throws SQLException {
        String QUERY_GET_REVIEWS_BY_COURSE = "SELECT r.* FROM reviews r JOIN courses c ON r.course_id = c.id WHERE c.id = ?";
        List<Review> reviewsCourse = new ArrayList<>();
        Connection connection = connectionHolder.getConnection();
        try (PreparedStatement reviewsCourseStatement = connection.prepareStatement(QUERY_GET_REVIEWS_BY_COURSE)) {
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

    private List<Category> getCourseCategories(Long courseId) throws SQLException {
        String QUERY_GET_CATEGORIES_BY_COURSE = "SELECT category.* FROM categories category JOIN course_categories cc ON category.id = cc.category_id WHERE cc.course_id = ?";
        Connection connection = connectionHolder.getConnection();
        List<Category> categories = new ArrayList<>();
        try (PreparedStatement categoriesCourseStatement = connection.prepareStatement(QUERY_GET_CATEGORIES_BY_COURSE)) {
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
        String QUERY_GET_ALL_USERS = "SELECT u.*, r.id AS role_id, r.name AS role_name, r.description AS role_description\n" +
                "FROM users u\n" +
                "JOIN roles r ON u.role_id = r.id;";
        Connection connection = connectionHolder.getConnection();
        try (PreparedStatement userStatement = connection.prepareStatement(QUERY_GET_ALL_USERS);
             ResultSet userResultSet = userStatement.executeQuery()) {
            while (userResultSet.next()) {
                Role role = Role.builder()
                        .id(userResultSet.getLong("role_id"))
                        .name(userResultSet.getString("role_name"))
                        .description(userResultSet.getString("description"))
                        .build();
                User user = User.builder()
                        .id(userResultSet.getLong("id"))
                        .email(userResultSet.getString("email"))
                        .password(userResultSet.getString("password"))
                        .createdAt(userResultSet.getTimestamp("created_at").toLocalDateTime())
                        .role(role)
                        .transactions(getUserTransactions(userResultSet.getLong("id")))
                        .courses(getUserCourses(userResultSet.getLong("id")))
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
        Connection connection = connectionHolder.getConnection();
        try (PreparedStatement insertStatement = connection.prepareStatement(QUERY_INSERT_USER)) {
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
    public Optional<User> update(Long id, User entity) {
        String QUERY_UPDATE_USER = "UPDATE users SET email = ?, password = ?, role_id = ? WHERE id = ?";
        Connection connection = connectionHolder.getConnection();
        try (PreparedStatement updateUser = connection.prepareStatement(QUERY_UPDATE_USER)) {
            updateUser.setString(1, entity.getEmail());
            updateUser.setString(2, entity.getPassword());
            updateUser.setLong(3, entity.getRole().getId());
            updateUser.setLong(4, id);
            updateUser.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return getById(id);
    }

    @Override
    public Boolean delete(Long id) {
        String QUERY_DELETE_USER = "DELETE FROM users WHERE id = ?";
        Connection connection = connectionHolder.getConnection();
        int affectedRows = 0;
        try (PreparedStatement deleteUser = connectionHolder.getConnection().prepareStatement(QUERY_DELETE_USER)) {
            deleteUser.setLong(1, id);
            affectedRows = deleteUser.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return affectedRows > 0;
    }

}