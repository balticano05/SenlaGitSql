package com.online.shop.utils;

import com.myioc.annotations.Component;
import com.myioc.annotations.Value;

@Component
public class ParametersHolder {
    @Value("my.param.db")
    private String someText;

    public String getSomeText() {
        return someText;
    }

}
