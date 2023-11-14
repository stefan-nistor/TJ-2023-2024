package ro.uaic.info.lab7.repositories;

import ro.uaic.info.lab7.entities.Preference;

public class PreferenceRepository extends DataRepository<Preference, Long> {
    protected PreferenceRepository(Class<Preference> entityClass) {
        super(entityClass);
    }

}
