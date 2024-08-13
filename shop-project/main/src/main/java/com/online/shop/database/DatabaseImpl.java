package com.online.shop.database;

import com.myioc.annotations.Autowired;
import com.myioc.annotations.Component;
import com.myioc.annotations.Value;
import com.online.shop.utils.ParametersHolder;

import java.lang.annotation.Annotation;

@Component
public class DatabaseImpl implements Database{

    private ParametersHolder parametersHolder;

    @Autowired
    public void setParametersHolder(ParametersHolder parametersHolder) {
        this.parametersHolder = parametersHolder;
    }

    @Override
    public String execute() {
        return parametersHolder.getSomeText();
    }
}
