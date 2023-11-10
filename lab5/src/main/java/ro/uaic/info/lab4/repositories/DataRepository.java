package ro.uaic.info.lab4.repositories;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import ro.uaic.info.lab4.entities.AbstractEntity;

import java.io.Serializable;
import java.util.Optional;

public abstract class DataRepository<T extends AbstractEntity, ID extends Serializable> {

    protected Class<T> entityClass;
    @PersistenceContext
    protected EntityManager entityManager;

    protected DataRepository(Class<T> entityClass) {
        this.entityClass = entityClass;
    }

    public Optional<T> findById(ID id) {
        return Optional.ofNullable(entityManager.find(entityClass, id));
    }

    public Optional<T> findByName(String name) {
        return Optional.ofNullable(entityManager.find(entityClass, name));
    }

    public void save(T entity) {
        entityManager.persist(entity);
    }
}
