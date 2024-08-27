package com.online.shop.repository;

import java.util.List;

public interface GeneralDao<T> {
    T findById(Long id);
    List<T> getAll();
    void add(T entity);
    void update(Long id, T entity);
    void delete(Long entity);
}
