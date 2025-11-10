package commands;

import electricalAppliances.ApplianceManager;

/**
 * Removes categories or appliances from the system.
 * Handles both category deletion and appliance removal.
 */
public class RmCommand implements Command {

    /** Reference to the appliance manager. */
    private final ApplianceManager manager;

    /**
     * Creates a new RmCommand.
     *
     * @param manager appliance manager instance
     */
    public RmCommand(ApplianceManager manager) {
        this.manager = manager;
    }

    /**
     * Executes the remove command.
     * Deletes either a category or an appliance depending on provided flags.
     *
     * @param args command arguments
     */
    @Override
    public void execute(String[] args) {
        if (args.length == 0) {
            System.out.println("""
            Usage:
              rm -c <categoryName>                              - to remove category.
              rm -a <applianceName> -c <categoryName>           - to remove appliance in category.
            """);
            return;
        }

        switch (args[0]) {
            case "-c" -> manager.removeCategory(args[1]);
            case "-a" -> manager.removeAppliance(args[1]);
            default -> System.out.println("Unknown ls flag.");
        }
    }
}
