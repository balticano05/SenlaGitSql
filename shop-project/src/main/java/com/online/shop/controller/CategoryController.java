package com.online.shop.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Controller;

@Controller
public class CategoryController extends GenericObjectMapper {

    public CategoryController(ObjectMapper objectMapper) {
        super(objectMapper);
    }

}