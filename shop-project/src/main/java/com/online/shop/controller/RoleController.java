package com.online.shop.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Controller;

@Controller
public class RoleController extends GenericObjectMapper {

    public RoleController(ObjectMapper objectMapper) {
        super(objectMapper);
    }

}