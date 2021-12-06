package com.codecool.mi6.stores;

import com.codecool.mi6.models.Credential;
import com.codecool.mi6.models.SecurityLevel;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class InMemoryCredentialStore implements CredentialStore {

    Map<String, String> data = new HashMap<>();
    Map<String, SecurityLevel> levels = new HashMap<>();


    public InMemoryCredentialStore() {
        data.put("007", "5d7845ac6ee7cfffafc5fe5f35cf666d"); //secret123
        levels.put("007", SecurityLevel.ONE);

        data.put("M", "5d7845ac6ee7cfffafc5fe5f35cf666d");
        levels.put("M", SecurityLevel.THREE);
    }

    @Override
    public Credential find(String userName, String hash) {
        if (Objects.equals(data.get(userName), hash)) {
            return new Credential(levels.get(userName), true);
        }
        return new Credential(SecurityLevel.INVALID, false);
    }
}
