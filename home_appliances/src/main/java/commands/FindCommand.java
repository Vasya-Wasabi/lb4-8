package commands;

import electricalAppliances.ApplianceManager;
import system.LogService;

/**
 * Handles search operations for appliances.
 * Supports finding by name or by power range.
 */
public class FindCommand implements Command {

    /** Reference to the appliance manager. */
    private final ApplianceManager manager;

    /**
     * Creates a new FindCommand instance.
     *
     * @param manager appliance manager used for search operations
     */
    public FindCommand(ApplianceManager manager) {
        this.manager = manager;
    }

    /**
     * Executes the find command.
     * Supports:
     * - find -a <applianceName> : find appliance by name
     * - find -r <minPower> <maxPower> : find appliances within power range
     *
     * @param args command arguments
     */
    @Override
    public void execute(String[] args) {
        if (args.length == 0) {
            System.out.println("""
            Usage:
              find -a <applianceName>              - find appliance by name
              find -r <minPower> <maxPower>        - find appliances within power range
            """);
            return;
        }

        switch (args[0]) {
            case "-a" -> {
                if (args.length < 2) {
                    System.out.println("Usage: find -a <applianceName>");
                    return;
                }
                manager.find(args[1]);
            }

            case "-r" -> {
                if (args.length < 3) {
                    System.out.println("Usage: find -r <minPower> <maxPower>");
                    return;
                }
                try {
                    int min = Integer.parseInt(args[1]);
                    int max = Integer.parseInt(args[2]);
                    manager.findByPowerRange(min, max);
                } catch (NumberFormatException e) {
                    System.out.println("Error: Power values must be integers. " + e.getMessage());
                    LogService.error("Error: Power values must be integers. ", e);
                }
            }

            default -> System.out.println("Unknown flag: " + args[0]);
        }
    }
}
