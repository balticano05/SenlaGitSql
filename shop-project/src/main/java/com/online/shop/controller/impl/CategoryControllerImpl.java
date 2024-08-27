package com.online.shop.controller.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.online.shop.controller.CategoryController;
import com.online.shop.controller.CrudController;

public class CategoryControllerImpl extends CrudController implements CategoryController {

    public CategoryControllerImpl(ObjectMapper objectMapper) {
        super(objectMapper);
    }

    @Override
    public String insert(String jsonEntity) {
        return "";
    }

    @Override
    public String update(Long id, String jsonEntity) {
        return "";
    }

    @Override
    public String delete(Long id) {
        return "";
    }

    @Override
    public String getAll() {
        return "";
    }

    @Override
    public String getById(Long id) {
        return "";
    }
}
