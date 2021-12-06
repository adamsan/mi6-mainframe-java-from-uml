package com.codecool.mi6.stores;

import com.codecool.mi6.models.Mission;
import com.codecool.mi6.models.SecurityLevel;

import java.util.List;

public interface MissionStore {
    void save(Mission m);

    void update(Mission m);

    List<Mission> findActive(SecurityLevel level);

    Mission findByTitle(String title);
}
