package com.online.shop;

import com.online.shop.controller.UserController;
import com.online.shop.сontext.AppConfig;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Application {

    public static void main(String[] args) {

        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        UserController userController = context.getBean(UserController.class);

        String jsonForInsert = "{\n" +
                "    \"email\": \"test1.com\",\n" +
                "    \"password\": \"new#password\",\n" +
                "    \"role\": {\n" +
                "      \"id\": 1\n" +
                "    }\n" +
                "  }\n" +
                "}";

        String jsonForUpdate = "{\n" +
                "    \"id\": 67,\n" +
                "    \"email\": \"test2.com\",\n" +
                "    \"password\": \"new#password2\",\n" +
                "    \"role\": {\n" +
                "      \"id\": 1\n" +
                "    }\n" +
                "  }\n" +
                "}";

        Runnable task1 = () -> {
            userController.insert(jsonForInsert);
        };

        Runnable task2 = () -> {
            userController.update(2L, jsonForUpdate);
        };

        Thread t1 = new Thread(task1);
        Thread t2 = new Thread(task2);

        t1.start();
        t2.start();

        try {
            t1.join();
            t2.join();
        } catch (RuntimeException | InterruptedException e) {
            throw new RuntimeException(e);
        }

    }

}