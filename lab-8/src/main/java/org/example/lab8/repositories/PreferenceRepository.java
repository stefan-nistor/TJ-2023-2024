package org.example.lab8.repositories;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Named;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.example.lab8.entities.Preference;

import java.util.List;
import java.util.Optional;

@Named("PreferenceRepository")
@ApplicationScoped
@Transactional
public class PreferenceRepository {

    @PersistenceContext(unitName = "tjlabs")
    EntityManager em;

    public void delete(Long id) {
        em.createNativeQuery("delete from preferences where id=(?)").setParameter(1, id).executeUpdate();
    }

    public void update(Preference preference) {

        var preferenceName = preference.getName();
        var oldId = preference.getId();
        var preferenceContent = preference.getContent();

        if (preferenceName != null && preferenceName.length() > 0) {
            em.createNativeQuery("update preferences set name=(?) where id=(?)")
                    .setParameter(1, preferenceName)
                    .setParameter(2, oldId)
                    .executeUpdate();
        }
        if (preferenceContent != null && preferenceContent.length() > 0) {
            em.createNativeQuery("update preferences set content=(?) where id=(?)")
                    .setParameter(1, preferenceContent)
                    .setParameter(2, oldId)
                    .executeUpdate();
        }
    }

    public void save(Preference preference) {
        em.persist(preference);
    }

    public List<Preference> getAll() {
        return em.createNativeQuery("SELECT * FROM preferences", Preference.class).getResultList();
    }

}
