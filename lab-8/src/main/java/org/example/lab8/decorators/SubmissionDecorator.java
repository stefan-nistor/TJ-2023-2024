package org.example.lab8.decorators;

import jakarta.decorator.Decorator;
import jakarta.decorator.Delegate;
import jakarta.inject.Inject;
import org.example.lab8.entities.Preference;
import org.example.lab8.services.PreferenceService;

import java.time.LocalTime;

@Decorator
public abstract class SubmissionDecorator implements PreferenceService {
    private final LocalTime startTime = LocalTime.of(10,0);
    private final LocalTime endTime = LocalTime.of(12,0);

    @Inject
    @Delegate
    private PreferenceService preferenceServiceDelegate;

    @Override
    public void createPreference(Preference preference) {
        var now = LocalTime.now();
        if(now.isBefore(startTime) || now.isAfter(endTime)) {
            throw new RuntimeException("Submission allowed only between 10:00-12:00");
        }
        preferenceServiceDelegate.createPreference(preference);
    }

}
