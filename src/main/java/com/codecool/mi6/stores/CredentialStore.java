package com.codecool.mi6.stores;

import com.codecool.mi6.models.Credential;

public interface CredentialStore {
    Credential find(String userName, String hash);
}
