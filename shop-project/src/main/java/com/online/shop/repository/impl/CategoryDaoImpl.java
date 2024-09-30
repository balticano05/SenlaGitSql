package com.online.shop.repository.impl;

import com.online.shop.entity.Category;
import com.online.shop.repository.AbstractDao;
import com.online.shop.repository.CategoryDao;
import org.springframework.stereotype.Repository;

@Repository
public class CategoryDaoImpl extends AbstractDao<Category> implements CategoryDao {
    @Override
    protected Class<Category> getEntityClass() {
        return Category.class;
    }
}