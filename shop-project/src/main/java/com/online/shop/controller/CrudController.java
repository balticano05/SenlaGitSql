package com.online.shop.controller;

import com.fasterxml.jackson.databind.ObjectMapper;

public abstract class CrudController implements GeneralController {
    protected ObjectMapper objectMapper;

    public CrudController(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }
}
