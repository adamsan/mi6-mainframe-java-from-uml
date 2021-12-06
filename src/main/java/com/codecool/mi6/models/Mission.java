package com.codecool.mi6.models;

import java.time.LocalDate;

public class Mission {
    private String title;
    private boolean active;
    private String targetName;
    private SecurityLevel access;
    private String description;
    private LocalDate deadLine;

    public Mission(String title, boolean active, String targetName, SecurityLevel access, String description, LocalDate deadLine) {
        this.title = title;
        this.active = active;
        this.targetName = targetName;
        this.access = access;
        this.description = description;
        this.deadLine = deadLine;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTargetName() {
        return targetName;
    }

    public void setTargetName(String targetName) {
        this.targetName = targetName;
    }

    public SecurityLevel getAccess() {
        return access;
    }

    public void setAccess(SecurityLevel access) {
        this.access = access;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getDeadLine() {
        return deadLine;
    }

    public void setDeadLine(LocalDate deadLine) {
        this.deadLine = deadLine;
    }
}
