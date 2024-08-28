package com.online.shop;

import com.online.shop.controller.ReviewController;
import com.online.shop.сontext.AppConfig;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Application {

    public static void main(String[] args) {

        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        ReviewController reviewController = context.getBean(ReviewController.class);

        String json1 = "{\n" +
                "    \"id\": 1,\n" +
                "    \"user\": {\n" +
                "        \"id\": 1,\n" +
                "        \"email\": \"jhonde@gmail.com\",\n" +
                "        \"role\": {\n" +
                "            \"id\": 1,\n" +
                "            \"name\": \"Student\",\n" +
                "            \"description\": \"A student role\"\n" +
                "        }\n" +
                "    },\n" +
                "    \"course\": {\n" +
                "        \"id\": 1,\n" +
                "        \"title\": \"Introduction to Programming\",\n" +
                "        \"description\": \"A beginner's course on programming concepts.\",\n" +
                "        \"price\": 2999,\n" +
                "        \"createdAt\": \"2023-10-30T14:30:00\"\n" +
                "    },\n" +
                "    \"content\": \"This course was very informative and helpful.\",\n" +
                "    \"rating\": 5,\n" +
                "    \"createdAt\": \"2023-10-30T12:00:00\"\n" +
                "}";

        String json2 = "{\n" +
                "    \"id\": 2,\n" +
                "    \"user\": {\n" +
                "        \"id\": 2,\n" +
                "        \"email\": \"janesmith@gmail.com\",\n" +
                "        \"role\": {\n" +
                "            \"id\": 2,\n" +
                "            \"name\": \"Instructor\",\n" +
                "            \"description\": \"An instructor role with course management privileges.\"\n" +
                "        }\n" +
                "    },\n" +
                "    \"course\": {\n" +
                "        \"id\": 2,\n" +
                "        \"title\": \"Advanced Java Programming\",\n" +
                "        \"description\": \"In-depth course on advanced Java programming techniques and best practices.\",\n" +
                "        \"price\": 4999,\n" +
                "        \"createdAt\": \"2023-10-25T10:00:00\"\n" +
                "    },\n" +
                "    \"content\": \"This course exceeded my expectations. The materials were well-organized.\",\n" +
                "    \"rating\": 4,\n" +
                "    \"createdAt\": \"2023-10-30T09:00:00\"\n" +
                "}";

        String json3 = "{\n" +
                "    \"id\": 3,\n" +
                "    \"user\": {\n" +
                "        \"id\": 3,\n" +
                "        \"email\": \"michaeljohnso@gmail.com\",\n" +
                "        \"role\": {\n" +
                "            \"id\": 3,\n" +
                "            \"name\": \"Admin\",\n" +
                "            \"description\": \"Administrator with full access to all courses and reviews.\"\n" +
                "        }\n" +
                "    },\n" +
                "    \"course\": {\n" +
                "        \"id\": 3,\n" +
                "        \"title\": \"Data Science Bootcamp\",\n" +
                "        \"description\": \"Comprehensive bootcamp covering data science tools and techniques.\",\n" +
                "        \"price\": 7999,\n" +
                "        \"createdAt\": \"2023-10-20T15:45:00\"\n" +
                "    },\n" +
                "    \"content\": \"Highly recommended! The instructors are knowledgeable and very supportive.\",\n" +
                "    \"rating\": 5,\n" +
                "    \"createdAt\": \"2023-10-30T15:00:00\"\n" +
                "}";

        System.out.println(reviewController.getAll());
        reviewController.insert(json1);
        reviewController.insert(json2);
        System.out.println(reviewController.getAll());
        System.out.println(reviewController.getById(1L));
        System.out.println(reviewController.getAll());
        System.out.println(reviewController.delete(1l));
        System.out.println(reviewController.getAll());
        reviewController.update(2L, json3);
        System.out.println(reviewController.getAll());

    }

}