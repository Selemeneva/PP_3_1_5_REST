package com.selemeneva.rest.repositories;

import com.selemeneva.rest.model.User;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;


@Repository
public class UserRepositoryImpl implements UserRepository {

    private final EntityManager entityManager;

    public UserRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public Optional<User> findByUsername(String email) {
        return Optional.ofNullable(entityManager.createQuery(
                "SELECT u FROM User u left join fetch u.roles WHERE u.email = :email",
                User.class).setParameter("email", email).getSingleResult());
    }
    @Override
    public Optional<User> findById(Long id) {
        return Optional.ofNullable(entityManager.find(User.class, id));
    }

    @Override
    public void deleteById(Long id) {
        entityManager.createQuery(
                "DELETE User WHERE id = :id").setParameter("id", id).executeUpdate();
    }

    @Override
    public void save(User user) {
        entityManager.merge(user);
    }

    @Override
    public Set<User> findAll() {
        return entityManager.createQuery("from User", User.class)
                .getResultList().stream().collect(Collectors.toSet());
    }
}
