package com.online.shop.controller;

import com.myioc.annotations.Autowired;
import com.myioc.annotations.Component;
import com.online.shop.service.SomeService;

@Component
public class Controller {

    private SomeService someService;

    @Autowired
    public Controller(SomeService someService){
        this.someService = someService;
    }

    public String execute(){
        return someService.execute();
    }
}
