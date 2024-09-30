package com.online.shop.utils;

public final class QueryStorage {
    public final static String QUERY_GET_USER_WITH_ROLE_BY_ID =
            "SELECT u.*, r.id AS role_id, r.name AS role_name, r.description AS role_description " +
                    "FROM users u " +
                    "JOIN roles r ON u.role_id = r.id " +
                    "WHERE u.id = ?";
    public final static String QUERY_GET_TRANSACTIONS_BY_USER =
            "SELECT t.*, c.title, c.description, c.created_at " +
                    "FROM transactions t " +
                    "JOIN courses c ON c.id = t.course_id " +
                    "JOIN users u ON u.id = t.user_id WHERE u.id = ?";
    public final static String QUERY_GET_COURSES_BY_USER =
            "SELECT t.*, c.title, c.description, c.created_at, cc.duration," +
                    " cc.practice_count, cc.lesson_count FROM transactions t\n" +
                    "JOIN courses c ON c.id = t.course_id\n" +
                    "JOIN course_plans cc ON c.id = cc.id\n" +
                    "JOIN users u ON u.id = t.user_id WHERE u.id = ?;";
    public final static String QUERY_GET_REVIEWS_BY_COURSE =
            "SELECT r.* FROM reviews r JOIN courses c ON r.course_id" +
                    " = c.id WHERE c.id = ?";
    public final static String QUERY_GET_CATEGORIES_BY_COURSE =
            "SELECT category.* FROM categories category JOIN" +
                    " course_categories cc ON category.id = cc.category_id WHERE cc.course_id = ?";
    public final static String QUERY_GET_ALL_USERS =
            "SELECT u.*, r.id AS role_id, r.name AS role_name, r.description AS" +
                    " role_description\n" +
                    "FROM users u\n" +
                    "JOIN roles r ON u.role_id = r.id;";
    public final static String QUERY_INSERT_USER =
            "INSERT INTO users (email, password, role_id) VALUES " +
                    "(?, ?, ?) RETURNING id";
    public final static String QUERY_UPDATE_USER = "UPDATE users SET email = ?, password = ?, role_id = ? WHERE id = ?";
    public final static String QUERY_DELETE_USER = "DELETE FROM users WHERE id = ?";
}
