package com.online.shop.service;

import com.myioc.annotations.Autowired;
import com.myioc.annotations.Component;
import com.online.shop.database.Database;

@Component
public class SomeService implements Service{

    private Database database;

    @Autowired
    public void setDatabase(Database database) {
        this.database = database;
    }

    @Override
    public String execute() {
        return database.execute();
    }
}
