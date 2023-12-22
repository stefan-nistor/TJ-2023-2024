package org.example.lab8.decorators;

import jakarta.decorator.Decorator;
import jakarta.decorator.Delegate;
import jakarta.inject.Inject;
import org.example.lab8.entities.User;
import org.example.lab8.services.PreferenceService;
import org.example.lab8.services.UserService;

import java.time.LocalTime;

@Decorator
public abstract class RegistrationDecorator implements UserService {
    private final LocalTime startTime = LocalTime.of(10,0);
    private final LocalTime endTime = LocalTime.of(12,0);

    @Inject
    @Delegate
    private UserService userServiceDelegate;

    @Override
    public void register(User user) {
        var now = LocalTime.now();
        if(now.isBefore(startTime) || now.isAfter(endTime)) {
            throw new RuntimeException("Registration allowed only between 10:00-12:00");
        }
        userServiceDelegate.register(user);
    }
}
