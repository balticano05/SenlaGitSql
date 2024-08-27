package com.online.shop.controller.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.online.shop.controller.CrudController;
import com.online.shop.controller.RoleController;

public class RoleControllerImpl extends CrudController implements RoleController {

    public RoleControllerImpl(ObjectMapper objectMapper) {
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
