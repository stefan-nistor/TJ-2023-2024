package org.example.lab8.services;

import jakarta.inject.Inject;
import jakarta.inject.Named;
import org.example.lab8.annotations.RegistrationNumber;
import org.example.lab8.entities.Preference;
import org.example.lab8.repositories.PreferenceRepository;

import java.util.List;

@Named("PreferenceService")
public class PreferenceServiceImpl implements PreferenceService {

    @Inject
    private PreferenceRepository preferenceRepository;

    @Inject
    @RegistrationNumber
    private String generatedRegistrationNumber;

    @Override
    public List<Preference> getAllPreferences(){
        return preferenceRepository.getAll();
    }

    @Override
    public void deletePreference(Long id) {
        preferenceRepository.delete(id);
    }

    @Override
    public void createPreference(Preference preference) {
        preference.setRegistrationNumber(generatedRegistrationNumber);
        preferenceRepository.save(preference);
    }

    @Override
    public Preference getPreferenceById(Long id) {
        return preferenceRepository.getAll().get(id.intValue());
    }

    @Override
    public void updatePreference(Preference preference) {
        preferenceRepository.update(preference);
    }

}
