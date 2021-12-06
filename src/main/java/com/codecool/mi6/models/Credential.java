package com.codecool.mi6.models;

public final class Credential {
    public final SecurityLevel level;
    public final boolean isValid;

    public Credential(SecurityLevel level, boolean isValid) {
        this.level = level;
        this.isValid = isValid;
    }
}
