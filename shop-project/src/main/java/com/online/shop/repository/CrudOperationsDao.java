package com.online.shop.repository;

import java.util.List;
import java.util.Optional;

public interface CrudOperationsDao<T> {
    Optional<T> findById(Long id);

    List<T> getAll();

    void insert(T entity);

    void update(Long id, T entity);

    void delete(Long entity);
}