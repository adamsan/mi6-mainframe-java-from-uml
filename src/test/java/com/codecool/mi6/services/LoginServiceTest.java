package com.codecool.mi6.services;

import com.codecool.mi6.models.Credential;
import com.codecool.mi6.models.SecurityLevel;
import com.codecool.mi6.stores.CredentialStore;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

class LoginServiceTest {

    private LoginService sut;

    @BeforeEach
    void setUp() {
        sut = new LoginService(new FakeCredentialStore(), new ConsoleAlertService());
    }

    @Test
    void login() {
        Credential credential = sut.login("007", "secret123");
        assertNotNull(credential);
    }

    static class FakeCredentialStore implements CredentialStore {

        @Override
        public Credential find(String userName, String hash) {
            return new Credential(SecurityLevel.ONE, true);
        }
    }
}