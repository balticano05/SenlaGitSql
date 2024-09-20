package com.online.shop.utils;

public final class StringConst {
    public final static String EXCEPTION_PROCESSING_JSON = "Exception processing json.";
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
    public final static String EXCEPTION_EMAIL_CANNOT_BE_NULL_OR_EMPTY = "Email cannot be null or empty";
    public final static String DATE_FORMAT = "dd.MM.yyyy";
    public final static String LOG_EXECUTING_INSERT_METHOD = "Executing insert method.";
    public final static String LOG_EXECUTING_UPDATE_METHOD = "Executing update method.";
    public final static String LOG_EXECUTING_DELETE_METHOD = "Executing delete method.";
    public final static String LOG_EXECUTING_GET_ALL_METHOD = "Executing getAll method.";
    public final static String LOG_EXECUTING_GET_BY_ID_METHOD = "Executing getById method.";
    public final static String LOG_EXECUTING_GET_BY_DATE_METHOD = "Executing getByDate method.";
    public final static String LOG_EXECUTING_GET_REVIEWS_METHOD = "Executing getReviewsByUser method.";
    public final static String LOG_EXECUTING_GET_TRANSACTIONS_METHOD = "Executing getTransactionsByCourse method.";
    public final static String LOG_EXECUTING_GET_BY_EMAIL_METHOD = "Executing getByEmail method.";
    public final static String LOG_EXECUTING_SET_CLAZZ_METHOD = "Executing setClazz method.";
    public final static String LOG_EXECUTING_GET_BY_ID_METHOD_WITH_ID = "Executing getById method with id: {}";
    public final static String LOG_EXECUTING_INSERT_METHOD_WITH_ENTITY = "Executing insert method with entity: {}";
    public final static String LOG_EXECUTING_UPDATE_METHOD_WITH_ID_AND_ENTITY = "Executing update method with id: {} and entity: {}";
    public final static String LOG_EXECUTING_GET_NULL_PROPERTY_METHOD = "Executing getNullProperty method.";
    public final static String LOG_EXECUTING_DELETE_METHOD_WITH_ID = "Executing delete method with id: {}";
    public final static String LOG_EXECUTING_FIND_BY_CREATED_AT_METHOD = "Executing findByCreatedAt method by {}";
    public final static String LOG_EXECUTING_FIND_BY_EMAIL_METHOD = "Executing findByEmail method by {}";
    public final static String LOG_EXECUTING_FIND_BY_ID_METHOD = "Executing findById method.";
    public final static String LOG_EXECUTION_FIND_TRANSACTIONS_BY_EMAIL_METHOD = "Executing findTransactionsByEmail method by {}";
    public final static String LOG_EXECUTION_FIND_BY_DATE = "Executing findByDate method.";
    public final static String LOG_CREATING_OBJECT_MAPPER_BEAN = "Creating ObjectMapper bean";
    public final static String LOG_MODEL_MAPPER_BEAN = "Creating ModelMapper bean";
    public final static String LOG_CREATING_DATASOURCE_BEAN_WITH_URL = "Creating DataSource bean with URL: ";
    public final static String LOG_SPRING_LIQUIBASE_BEAN_CHANGE_LOG_FILE = "Creating SpringLiquibase bean with changeLogFile: {}";
    public final static String LOG_SUCCESSFULLY_CONNECTED_TO_THE_DATABASE = "Successfully connected to the database";
    public final static String LOG_FAILED_TO_VALIDATE_THE_DATABASE_CONNECTION = "Failed to validate the database connection.";
    public final static String LOG_ERROR_WHILE_CHECKING_THE_DATABASE_CONNECTION = "Error while checking the database connectio: ";
    public final static String LOG_CREATING_LOCAL_CONTAINER_MANAGER_BEAN = "Creating LocalContainerEntityManager bean";
    public final static String LOG_CREATING_JPA_TRANSACTION_MANAGER_BEAN = "Creating JpaTransactionManager bean";
}