package ro.uaic.info.lab7.repositories;

import jakarta.persistence.TypedQuery;
import ro.uaic.info.lab7.entities.User;

public class UserRepository extends DataRepository<User, Long> {
    protected UserRepository(Class<User> entityClass) {
        super(entityClass);
    }

    public User findByUsername(String username) {
        TypedQuery<User> query = entityManager.createNamedQuery("Project.findByCategory",
                User.class);
        query.setParameter("username", username);
        return query.getSingleResult();
    }

}
