package com.codecool.mi6.services;

import com.codecool.mi6.models.Credential;
import com.codecool.mi6.models.Mission;
import com.codecool.mi6.models.SecurityLevel;
import com.codecool.mi6.stores.MissionStore;

import java.util.Collections;
import java.util.List;

import static com.codecool.mi6.models.SecurityLevel.*;

public class MissionService {
    private final MissionStore missionStore;
    private final AlertService alertService;

    public MissionService(MissionStore missionStore, AlertService alertService) {
        this.missionStore = missionStore;
        this.alertService = alertService;
    }

    public List<Mission> findActiveMissions(Credential credential) {
        if (credential.isValid && (credential.level == ONE || credential.level == TWO || credential.level == THREE)) {
            return missionStore.findActive(credential.level);
        }
        alertService.raiseSecurityAlert("Invalid access - trying to list missions without valid security level");
        return Collections.emptyList();
    }

    public void addMission(Credential c, Mission m) {
        if (c.isValid && c.level == SecurityLevel.THREE) {
            missionStore.save(m);
        } else {
            alertService.raiseSecurityAlert("Invalid access - trying to add new mission without valid security level");
        }
    }

    public void deactivate(Credential c, String title) {
        if (c.isValid && INVALID.compareTo(c.level) < 0) {
            Mission m = missionStore.findByTitle(title);
            m.setActive(false);
            missionStore.update(m);
        } else {
            alertService.raiseSecurityAlert("Invalid access - trying to deactivate mission without valid security level");
        }
    }
}
