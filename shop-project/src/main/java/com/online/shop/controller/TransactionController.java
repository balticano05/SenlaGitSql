package com.online.shop.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Controller;

@Controller
public class TransactionController extends GenericObjectMapper {

    public TransactionController(ObjectMapper objectMapper) {
        super(objectMapper);
    }

}