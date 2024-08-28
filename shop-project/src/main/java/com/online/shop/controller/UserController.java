package com.online.shop.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Controller;

@Controller
public class UserController extends GenericObjectMapper {

    public UserController(ObjectMapper objectMapper) {
        super(objectMapper);
    }

}