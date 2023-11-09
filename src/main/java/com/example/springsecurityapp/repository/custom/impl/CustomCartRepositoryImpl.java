package com.example.springsecurityapp.repository.custom.impl;

import com.example.springsecurityapp.entity.ProductEntity;
import com.example.springsecurityapp.repository.custom.CustomCartRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;

import java.util.List;

public class CustomCartRepositoryImpl implements CustomCartRepository {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<ProductEntity> findCartItemsByUsername(final String username) {
        String sql = "SELECT p FROM CartItemEntity ci JOIN ci.product p JOIN ci.cart c WHERE c.userName = :username";
        TypedQuery<ProductEntity> query = entityManager.createQuery(sql, ProductEntity.class);
        query.setParameter("username", username);
        return query.getResultList();
    }
}
