package commands;

import electricalAppliances.ApplianceManager;

/**
 * Lists categories or appliances based on user-specified flags.
 * Supports listing categories, all appliances, specific category appliances, or plugged devices.
 */
public class LsCommand implements Command {

    /** Reference to the appliance manager. */
    private final ApplianceManager manager;

    /**
     * Creates a new LsCommand.
     *
     * @param manager appliance manager instance
     */
    public LsCommand(ApplianceManager manager) {
        this.manager = manager;
    }

    /**
     * Executes the list command.
     * Lists categories, appliances, or plugged devices based on provided flags.
     *
     * @param args command arguments
     */
    @Override
    public void execute(String[] args) {
        if (args.length == 0) {
            System.out.println("""
            Usage:
              ls -c                         - to list categories
              ls -a <categoryName>          - to list all appliance in current categories
              ls -all                       - to list all appliances in all categories
              ls -on                        - to list all plugged appliances.
            """);
            return;
        }

        switch (args[0]) {
            case "-c" -> manager.listCategories();
            case "-a" -> {
                if (args.length < 2) {
                    System.out.println("Usage: ls -a <categoryName>");
                    return;
                }
                manager.listApplianceByCategories(args[1]);
            }
            case "-all" -> manager.listAllAppliance();
            case "-on" -> manager.listPluggedAppliances();
            default -> System.out.println("Unknown ls flag.");
        }
    }
}
