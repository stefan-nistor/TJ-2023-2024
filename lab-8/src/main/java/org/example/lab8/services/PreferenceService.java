package org.example.lab8.services;

import org.example.lab8.entities.Preference;

import java.util.List;

public interface PreferenceService {
    List<Preference> getAllPreferences();
    void deletePreference(Long id);
    void createPreference(Preference preference);

    Preference getPreferenceById(Long id);

    void updatePreference(Preference preference);
}
