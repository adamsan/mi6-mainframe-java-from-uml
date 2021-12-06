package com.codecool.mi6.services;

public class ConsoleAlertService implements AlertService {
    @Override
    public void raiseSecurityAlert(String cause) {
        System.out.println(cause);
    }
}
