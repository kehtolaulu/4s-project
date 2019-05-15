package app.services;

import app.entities.Profile;

@FunctionalInterface
public interface BindToProfile {
    void to(Profile profile);
}
