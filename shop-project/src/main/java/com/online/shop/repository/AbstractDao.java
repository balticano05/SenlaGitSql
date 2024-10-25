package com.online.shop.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.beans.PropertyDescriptor;

import java.util.*;

@Slf4j
@Repository
public abstract class AbstractDao<T> {

    @PersistenceContext
    protected EntityManager entityManager;

    protected abstract Class<T> getEntityClass();

    public Optional<T> getById(Long id) {
        log.info("Executing getById method with id: {}", id);
        return Optional.ofNullable(entityManager.find(getEntityClass(), id));
    }

    public List<T> getAll() {
        log.info("Executing getAll method.");
        return entityManager.createQuery("FROM " + getEntityClass().getSimpleName()).getResultList();
    }

    public Long insert(T entity) {
        log.info("Executing insert method.", entity);
        entityManager.persist(entity);
        entityManager.flush();
        return (Long) entityManager.getEntityManagerFactory().getPersistenceUnitUtil().getIdentifier(entity);
    }

    public Optional<T> update(Long id, T entity) {
        log.info("Executing update method with id: {} and entity: {}", id, entity);
        return getById(id).map(existingEntity -> {
            BeanUtils.copyProperties(entity, existingEntity, getNullPropertyNames(entity));
            entityManager.merge(existingEntity);
            return existingEntity;
        });
    }

    private String[] getNullPropertyNames(Object source) {
        log.info("Executing getNullProperty method.");
        BeanWrapper src = new BeanWrapperImpl(source);
        return Arrays.stream(src.getPropertyDescriptors())
                .map(PropertyDescriptor::getName)
                .filter(name -> src.getPropertyValue(name) == null)
                .toArray(String[]::new);
    }

    public Boolean delete(Long id) {
        log.info("Executing delete method with id: {}", id);
        Optional<T> entity = getById(id);
        if (entity.isPresent()) {
            entityManager.remove(entity.get());
            return true;
        }
        return false;
    }

}