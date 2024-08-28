package com.online.shop.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Controller;

@Controller
public class CourseController extends GenericObjectMapper {

    public CourseController(ObjectMapper objectMapper) {
        super(objectMapper);
    }

}