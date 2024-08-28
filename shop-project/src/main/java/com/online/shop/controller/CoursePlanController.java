package com.online.shop.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Controller;

@Controller
public class CoursePlanController extends GenericObjectMapper {

    public CoursePlanController(ObjectMapper objectMapper) {
        super(objectMapper);
    }

}