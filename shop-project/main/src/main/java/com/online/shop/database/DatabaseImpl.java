package com.online.shop.database;

import com.myioc.annotations.Autowired;
import com.myioc.annotations.Component;
import com.online.shop.utils.ParametersHolder;

@Component
public class DatabaseImpl implements Database{

    @Autowired
    private ParametersHolder parametersHolder;

    @Override
    public String execute() {
        return parametersHolder.getSomeText();
    }
}
