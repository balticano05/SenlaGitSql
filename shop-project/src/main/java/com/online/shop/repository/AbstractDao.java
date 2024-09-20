package com.online.shop.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.stereotype.Repository;

import java.util.*;

import static com.online.shop.Application.log;
import static com.online.shop.utils.StringConst.*;

@Repository
public abstract class AbstractDao<T> {

    private Class<T> entityClass;

    @PersistenceContext
    protected EntityManager entityManager;

    public final void setClazz(Class<T> entityClass) {
        log.info(LOG_EXECUTING_SET_CLAZZ_METHOD);
        this.entityClass = entityClass;
    }

    public Optional<T> getById(Long id) {
        log.info(LOG_EXECUTING_GET_BY_ID_METHOD_WITH_ID, id);
        return Optional.ofNullable(entityManager.find(entityClass, id));
    }

    public List<T> getAll() {
        log.info(LOG_EXECUTING_GET_ALL_METHOD);
        return entityManager.createQuery("FROM " + entityClass.getSimpleName()).getResultList();
    }

    public Long insert(T entity) {
        log.info(LOG_EXECUTING_INSERT_METHOD_WITH_ENTITY, entity);
        entityManager.persist(entity);
        entityManager.flush();
        return (Long) entityManager.getEntityManagerFactory().getPersistenceUnitUtil().getIdentifier(entity);
    }

    public Optional<T> update(Long id, T entity) {
        log.info(LOG_EXECUTING_UPDATE_METHOD_WITH_ID_AND_ENTITY, id, entity);
        return getById(id).map(existingEntity -> {
            BeanUtils.copyProperties(entity, existingEntity, getNullPropertyNames(entity));
            entityManager.merge(existingEntity);
            return existingEntity;
        });
    }

    private String[] getNullPropertyNames(Object source) {
        log.info(LOG_EXECUTING_GET_NULL_PROPERTY_METHOD);
        final BeanWrapper src = new BeanWrapperImpl(source);
        return Arrays.stream(src.getPropertyDescriptors())
                .map(java.beans.PropertyDescriptor::getName)
                .filter(name -> src.getPropertyValue(name) == null)
                .toArray(String[]::new);
    }

    public Boolean delete(Long id) {
        log.info(LOG_EXECUTING_DELETE_METHOD_WITH_ID, id);
        Optional<T> entity = getById(id);
        if (entity.isPresent()) {
            entityManager.remove(entity.get());
            return true;
        }
        return false;
    }

}
