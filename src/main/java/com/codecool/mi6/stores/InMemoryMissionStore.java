package com.codecool.mi6.stores;

import com.codecool.mi6.models.Mission;
import com.codecool.mi6.models.SecurityLevel;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static com.codecool.mi6.models.SecurityLevel.INVALID;
import static com.codecool.mi6.models.SecurityLevel.ONE;

public class InMemoryMissionStore implements MissionStore {

    private final List<Mission> missions;

    public InMemoryMissionStore() {
        missions = new ArrayList<>();
        missions.add(new Mission("License to kill", true, "Sanchez", ONE, "", LocalDate.now().plusDays(8)));
        missions.add(new Mission("GoldenEye", true, "Auric Goldfinger", ONE, "", LocalDate.now().plusDays(15)));
    }

    @Override
    public void save(Mission m) {
        missions.add(m);
    }

    @Override
    public void update(Mission m) {
        // this is empty, because In memory store is already updated
    }

    @Override
    public List<Mission> findActive(SecurityLevel level) {
        return missions.stream().filter(m -> INVALID.compareTo(level) < 0).toList();
    }

    @Override
    public Mission findByTitle(String title) {
        return missions.stream().filter(m -> m.getTitle().equals(title)).findFirst().orElse(null);
    }
}
