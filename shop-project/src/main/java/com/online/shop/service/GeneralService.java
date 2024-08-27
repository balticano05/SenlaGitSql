package com.online.shop.service;

import java.util.List;

public interface GeneralService<T> {
    T add(T entityDto);
    T update(Long id, T entityDto);
    T getById(Long id);
    List<T> getAll();
    T delete(Long id);
}
