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

import static com.online.shop.utils.StringConst.*;

@Repository
public class UserDaoImpl implements UserDao {

    private ConnectionHolder connectionHolder;

    @Autowired
    public UserDaoImpl(ConnectionHolder connectionHolder) {
        this.connectionHolder = connectionHolder;
    }

    @Override
    public Optional<User> getById(Long id) {
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

    @Override
    public List<User> getAll() {
        List<User> users = new ArrayList<>();
        Connection connection = connectionHolder.getConnection();
        try (PreparedStatement statement = connection.prepareStatement(QUERY_GET_ALL_USERS);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                users.add(mapUser(resultSet));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return users;
    }

    @Override
    public Long insert(User entity) {
        Connection connection = connectionHolder.getConnection();
        try (PreparedStatement statement = connection.prepareStatement(QUERY_INSERT_USER)) {
            statement.setString(1, entity.getEmail());
            statement.setString(2, entity.getPassword());
            statement.setLong(3, entity.getRole().getId());
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getLong("id");
            } else {
                throw new SQLException("Failed to insert user");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Optional<User> update(Long id, User entity) {
        Connection connection = connectionHolder.getConnection();
        try (PreparedStatement statement = connection.prepareStatement(QUERY_UPDATE_USER)) {
            statement.setString(1, entity.getEmail());
            statement.setString(2, entity.getPassword());
            statement.setLong(3, entity.getRole().getId());
            statement.setLong(4, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return getById(id);
    }

    @Override
    public Boolean delete(Long id) {
        Connection connection = connectionHolder.getConnection();
        int affectedRows = 0;
        try (PreparedStatement statement = connectionHolder.getConnection().prepareStatement(QUERY_DELETE_USER)) {
            statement.setLong(1, id);
            affectedRows = statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return affectedRows > 0;
    }

    private User getUserById(Long id) throws SQLException {
        Connection connection = connectionHolder.getConnection();
        try (PreparedStatement statement = connection.prepareStatement(QUERY_GET_USER_WITH_ROLE_BY_ID)) {
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return mapUser(resultSet);
            }
        }
        return null;
    }

    private List<Transaction> getUserTransactions(Long userId) throws SQLException {
        Connection connection = connectionHolder.getConnection();
        List<Transaction> userTransactions = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement(QUERY_GET_TRANSACTIONS_BY_USER)) {
            statement.setLong(1, userId);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                userTransactions.add(mapTransaction(resultSet));
            }
        }
        return userTransactions;
    }

    private List<Course> getUserCourses(Long userId) throws SQLException {
        List<Course> userCourses = new ArrayList<>();
        Connection connection = connectionHolder.getConnection();
        try (PreparedStatement statement = connection.prepareStatement(QUERY_GET_COURSES_BY_USER)) {
            statement.setLong(1, userId);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                userCourses.add(mapCourse(resultSet));
            }
        }
        return userCourses;
    }

    private List<Review> getCourseReviews(Long courseId) throws SQLException {
        List<Review> reviewsCourse = new ArrayList<>();
        Connection connection = connectionHolder.getConnection();
        try (PreparedStatement statement = connection.prepareStatement(QUERY_GET_REVIEWS_BY_COURSE)) {
            statement.setLong(1, courseId);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                reviewsCourse.add(mapReview(resultSet));
            }
        }
        return reviewsCourse;
    }

    private List<Category> getCourseCategories(Long courseId) throws SQLException {
        Connection connection = connectionHolder.getConnection();
        List<Category> categories = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement(QUERY_GET_CATEGORIES_BY_COURSE)) {
            statement.setLong(1, courseId);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                categories.add(parseCategory(resultSet));
            }
        }
        return categories;
    }

    private User mapUser(ResultSet resultSet) throws SQLException {
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

    private Course mapCourse(ResultSet resultSet) throws SQLException {
        CoursePlan coursePlan = CoursePlan.builder()
                .id(resultSet.getLong("id"))
                .practiceCount(resultSet.getInt("practice_count"))
                .lessonCount(resultSet.getInt("lesson_count"))
                .duration(resultSet.getInt("duration"))
                .build();

        return Course.builder()
                .id(resultSet.getLong("course_id"))
                .title(resultSet.getString("title"))
                .price(resultSet.getBigDecimal("price"))
                .description(resultSet.getString("description"))
                .createdAt(resultSet.getTimestamp("created_at").toLocalDateTime())
                .coursePlan(coursePlan)
                .reviews(getCourseReviews(resultSet.getLong("course_id")))
                .categories(getCourseCategories(resultSet.getLong("course_id")))
                .build();
    }

    private Transaction mapTransaction(ResultSet resultSet) throws SQLException {
        return Transaction.builder()
                .id(resultSet.getLong("id"))
                .price(resultSet.getBigDecimal("price"))
                .dateTime(resultSet.getTimestamp("datetime").toLocalDateTime())
                .build();
    }

    private Review mapReview(ResultSet resultSet) throws SQLException {
        User user = User.builder()
                .id(resultSet.getLong("user_id"))
                .build();

        return Review.builder()
                .id(resultSet.getLong("id"))
                .content(resultSet.getString("content"))
                .rating(resultSet.getInt("rating"))
                .createdAt(resultSet.getTimestamp("created_at").toLocalDateTime())
                .user(user)
                .build();
    }

    private Category parseCategory(ResultSet resultSet) throws SQLException {
        return Category.builder()
                .id(resultSet.getLong("id"))
                .name(resultSet.getString("name"))
                .description(resultSet.getString("description"))
                .build();
    }

}