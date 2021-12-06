package com.codecool.mi6;

import com.codecool.mi6.models.Credential;
import com.codecool.mi6.models.Mission;
import com.codecool.mi6.models.SecurityLevel;
import com.codecool.mi6.services.AlertService;
import com.codecool.mi6.services.ConsoleAlertService;
import com.codecool.mi6.services.LoginService;
import com.codecool.mi6.services.MissionService;
import com.codecool.mi6.stores.InMemoryCredentialStore;
import com.codecool.mi6.stores.InMemoryMissionStore;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class App {

    private final Scanner scanner;
    private final LoginService loginService;
    private final MissionService missionService;

    public App(Scanner scanner) {
        this.scanner = scanner;
        AlertService alertService = new ConsoleAlertService();
        loginService = new LoginService(new InMemoryCredentialStore(), alertService);
        missionService = new MissionService(new InMemoryMissionStore(), alertService);
    }

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            var app = new App(scanner);
            app.start();
        }
    }

    public void start() {
        Credential credential = promptForLogin();
        if (!credential.isValid) return;

        switch (promptMenuChoice()) {
            case 0 -> System.out.println("Exiting...");
            case 1 -> listMissions(credential);
            case 2 -> promptForMissionToDeactivate(credential);
            case 3 -> promptForNewMissionToAdd(credential);
            default -> throw new IllegalStateException("Unexpected value");
        }
    }

    private void promptForMissionToDeactivate(Credential credential) {
        System.out.println("Input mission title to mark as complete:");
        String title = scanner.nextLine();
        missionService.deactivate(credential, title);
    }

    private void promptForNewMissionToAdd(Credential credential) {
        System.out.println("New mission title:");
        String title = scanner.nextLine();
        System.out.println("Security Level [ONE, TWO, THREE]:");
        SecurityLevel access = SecurityLevel.valueOf(scanner.nextLine());
        System.out.println("Target: ");
        String target = scanner.nextLine();
        System.out.println("Description: ");
        String description = scanner.nextLine();
        System.out.println("Deadline (YYYY-MM-DD): ");
        String dateString = scanner.nextLine();
        LocalDate deadLine = LocalDate.parse(dateString);

        Mission mission = new Mission(title, true, target, access, description, deadLine);
        missionService.addMission(credential, mission);
    }

    private void listMissions(Credential credential) {
        List<Mission> missions = missionService.findActiveMissions(credential);
        System.out.println("Active missions");
        for (Mission mission : missions) {
            System.out.println("-".repeat(80));
            System.out.println("Mission: " + mission.getTitle());
            System.out.println("-".repeat(80));
            System.out.println("Security level:" + mission.getAccess());
            System.out.println("Target: " + mission.getTargetName());
            System.out.println("Deadline: " + mission.getDeadLine());
            System.out.println("Description: " + mission.getDescription());
            System.out.println();
        }
    }

    private int promptMenuChoice() {
        System.out.println("""
                0 - Exit
                1 - List missions
                2 - Mark complete
                3 - Add new Mission
                """);
        int i = scanner.nextInt();
        scanner.nextLine(); // consume line break
        return i;
    }

    private Credential promptForLogin() {
        System.out.println("---- LOGIN ----");
        System.out.print("user: ");
        String userName = scanner.nextLine();
        System.out.println("password: ");
        String password = scanner.nextLine();
        return loginService.login(userName, password);
    }
}
