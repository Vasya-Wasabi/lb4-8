package ui;

import electricalAppliances.ApplianceManager;
import commands.Command;
import commands.CommandRegistry;
import system.LogService;

import java.util.Arrays;
import java.util.Scanner;
import java.util.Locale;

/**
 * Entry point of the Home Appliances app.
 * Initializes appliance manager and command registry,
 * reads user input, executes commands, and logs actions.
 * Author: Taras
 * Version: 1.0.0
 * Since: 2025-11-03
 */

public class Main {

    /** Scanner used to read user input from the console. */
    private static final Scanner scanner = new Scanner(System.in);

    /**
     * Entry point of the program.
     * Initializes the appliance manager, command registry,
     * and starts the main input loop for user interaction.
     *
     * @param args command-line arguments (not used)
     */
    public static void main(String[] args) {
        Locale.setDefault(Locale.US);
        LogService.info("PROGRAM STARTED.");

        ApplianceManager manager = new ApplianceManager();
        CommandRegistry registry = new CommandRegistry(manager);

        LogService.info("Command registry and manager initialized.");
        System.out.println("Home Appliances (type 'help' for commands)");

        while (true) {
            System.out.print("> ");
            String input = scanner.nextLine().trim();
            if (input.isEmpty()) continue;

            LogService.info("User input: -> " + input);

            String[] multiCommands = input.split(";");

            for (String cmdPart : multiCommands) {
                cmdPart = cmdPart.trim();
                if (cmdPart.isEmpty()) continue;
        
                String[] parts = cmdPart.split("\\s+");
                String name = parts[0];
                String[] cmdArgs = Arrays.copyOfRange(parts, 1, parts.length);

                Command command = registry.get(name);

                if (command == null) {
                    LogService.warn("Invalid command: " + name);
                    System.out.println("Invalid command: " + name);
                    continue;
                }

                command.execute(cmdArgs);
            }
        }
    }
}
