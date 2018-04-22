package com.devmonologue.reacty;

import io.dropwizard.auth.AuthenticationException;
import io.dropwizard.auth.Authenticator;
import io.dropwizard.auth.basic.BasicCredentials;

import java.util.Optional;

public class ReactyAuthenticator implements Authenticator<BasicCredentials, User> {

    @Override
    public Optional<User> authenticate(BasicCredentials credentials) throws AuthenticationException {
        if (credentials.getUsername().equals("admin")) {
            return Optional.of(new User());
        }
        else {
            return Optional.empty();
        }
    }
}
