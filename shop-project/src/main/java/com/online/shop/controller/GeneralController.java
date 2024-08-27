package com.online.shop.controller;

public interface GeneralController {
    String insert(String jsonEntity);
    String update(Long id, String jsonEntity);
    String delete(Long id);
    String getAll();
    String getById(Long id);
}
