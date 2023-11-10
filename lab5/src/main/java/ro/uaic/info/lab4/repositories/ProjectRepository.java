package ro.uaic.info.lab4.repositories;

import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import ro.uaic.info.lab4.entities.Project;

import java.util.List;

@Transactional
public class ProjectRepository extends DataRepository<Project, Long> {

    protected ProjectRepository(Class<Project> entityClass) {
        super(entityClass);
    }

    public List<Project> findByCategory(String category) {
        TypedQuery<Project> query = entityManager.createNamedQuery("Project.findByCategory",
                Project.class);
        query.setParameter("category", category);
        return query.getResultList();
    }

}
