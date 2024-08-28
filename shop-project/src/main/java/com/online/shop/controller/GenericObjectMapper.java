package com.online.shop.controller;

import com.fasterxml.jackson.databind.ObjectMapper;

public abstract class GenericObjectMapper {

    protected ObjectMapper objectMapper;

    public GenericObjectMapper(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

}