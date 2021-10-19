package com.springframework.dependencyinjection.Service;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

// Set as Default Profile
// Default Profile will be used if no Active Profile exist
@Profile({"FN", "default"})
@Service("I18nService")
public class I18nFrenchGreetingService implements GreetingService {
    @Override
    public String sayGreeting() {
        return "Bonjour";
    }
}