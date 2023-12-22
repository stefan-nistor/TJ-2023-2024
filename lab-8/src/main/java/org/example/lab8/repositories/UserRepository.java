package org.example.lab8.repositories;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Named;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.example.lab8.entities.User;

import java.util.List;
import java.util.Optional;

@ApplicationScoped
@Transactional
@Named("UserRepository")
public class UserRepository {

    @PersistenceContext(unitName = "tjlabs")
    EntityManager em;

    public void save(User user) {
        em.persist(user);
    }

    public Optional<User> getByUsername(String username) {
        Object obj;
        try {
            obj = em.createNativeQuery("SELECT * FROM users WHERE username = (?)", User.class)
                    .setParameter(1, username)
                    .getSingleResult();
        } catch (NoResultException ex) {
            return Optional.empty();
        }
        return Optional.of((User) obj);
    }

    public List<User> getAll() {
        return em.createQuery("SELECT u FROM User u", User.class).getResultList();
    }

}
