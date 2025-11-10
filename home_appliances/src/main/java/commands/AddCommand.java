package commands;

import electricalAppliances.ApplianceManager;

/**
 * Handles adding new categories or appliances to the system.
 * Supports two modes:
 * - add -c <categoryName> : adds a new category
 * - add -a <applianceName> -c <categoryName> -p <powerW> : adds an appliance
 */
public class AddCommand implements Command {

    /** Reference to the appliance manager. */
    private final ApplianceManager manager;

    /**
     * Creates a new AddCommand with the specified appliance manager.
     *
     * @param manager appliance manager to handle add operations
     */
    public AddCommand(ApplianceManager manager) {
        this.manager = manager;
    }

    /**
     * Executes the add command with given arguments.
     * Depending on flags, adds a category or a new appliance.
     *
     * @param args command-line arguments
     */
    @Override
    public void execute(String[] args) {
        if (args.length < 2) {
            System.out.println("""
            Usage:
              add -c <categoryName>                                             - to add category
              add -a <applianceName> -c <categoryName> -p <powerW >= 0>         - to add appliance in category
            """);
            return;
        }

        if (args[0].equals("-c")) {
            manager.addCategory(args[1]);
            return;
        }


        if (args[0].equals("-a")) {
            String name = null, category = null;
            double power = 0;

            for (int i = 1; i < args.length; i++) {
                switch (args[i]) {
                    case "-c" -> category = args[++i];
                    case "-p" -> power = Double.parseDouble(args[++i]);
                    default -> {
                        if (name == null) name = args[i];
                    }
                }
            }

            if (name == null || category == null || power <= 0) {
                System.out.println("Usage: add -a" +
                        " <applianceName> -c <categoryName> -p <powerW >= 0>");
                return;
            }

            manager.addAppliance(category, name, power);
            return;
        }

        System.out.println("Unknown add flag.");
    }
}
