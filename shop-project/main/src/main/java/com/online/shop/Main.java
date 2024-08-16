package com.online.shop;

import com.myioc.context.ApplicationContext;
import com.online.shop.controller.Controller;

public class Main {
    public static void main(String[] args) {
        Controller controller = ApplicationContext.initializeContext().getBean(Controller.class);
        String result = controller.execute();
        System.out.println(result);
    }
}
