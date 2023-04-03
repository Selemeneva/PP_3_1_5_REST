package com.selemeneva.rest.repositories;

import com.selemeneva.rest.model.Role;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

@Repository
public class RoleRepositoryImp implements RoleRepository {
    private final EntityManager entityManager;

    public RoleRepositoryImp(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public void save(Role role) {
        entityManager.persist(role);
    }

}

