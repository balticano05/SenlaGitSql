package com.online.shop.repository.impl;

import com.online.shop.repository.AbstractDao;
import com.online.shop.entity.Role;

import com.online.shop.repository.RoleDao;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class RoleDaoImpl extends AbstractDao<Role> implements RoleDao {
    @Override
    protected Class<Role> getEntityClass() {
        return Role.class;
    }

    public Role getByName(String name) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Role> query = criteriaBuilder.createQuery(Role.class);
        Root<Role> root = query.from(Role.class);
        Predicate predicate = criteriaBuilder.equal(root.get("name"), name);
        query.select(root).where(predicate);
        return entityManager.createQuery(query).getSingleResult();
    }
}