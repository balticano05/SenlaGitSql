package com.online.shop;

import com.myioc.context.ApplicationContext;
import com.online.shop.controller.Controller;

import static com.myioc.utils.StringConst.PACKAGE_NAME;

public class Main {
    public static void main(String[] args) {
        ApplicationContext applicationContext = new ApplicationContext(PACKAGE_NAME);

        Controller controller = applicationContext.getBean(Controller.class);
        String result = controller.execute();
        System.out.println(result);

    }
}
