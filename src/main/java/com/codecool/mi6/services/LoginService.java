package com.codecool.mi6.services;

import com.codecool.mi6.models.Credential;
import com.codecool.mi6.stores.CredentialStore;
import org.apache.commons.codec.digest.DigestUtils;

public class LoginService {

    private final CredentialStore credentials;
    DigestUtils md5 = new DigestUtils("MD5");
    private final AlertService alertService;

    public LoginService(CredentialStore credentials, AlertService alertService) {
        this.credentials = credentials;
        this.alertService = alertService;
    }

    public Credential login(String userName, String password) {
        Credential credential = credentials.find(userName, hashPassword(password));
        if (credential == null || !credential.isValid) {
            alertService.raiseSecurityAlert("Invalid login!");
        }
        return credential;
    }

    private String hashPassword(String password) {
        return md5.digestAsHex(password);
    }
}
