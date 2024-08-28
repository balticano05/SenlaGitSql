package com.online.shop.service;

import java.util.List;

public interface CrudOperations<T> {
    T insert(T entityDto);

    T update(Long id, T entityDto);

    T findById(Long id);

    List<T> getAll();

    void delete(Long id);
}