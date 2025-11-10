package commands;

import electricalAppliances.ApplianceManager;

/**
 * Handles editing of appliances or categories.
 * Supports changing appliance name, category, power, or renaming a category.
 */
public class EditCommand implements Command {

    /** Reference to the appliance manager. */
    private final ApplianceManager manager;

    /**
     * Creates a new EditCommand with the given appliance manager.
     *
     * @param manager appliance manager used to perform edit operations
     */
    public EditCommand(ApplianceManager manager) {
        this.manager = manager;
    }

    /**
     * Executes the edit command.
     * Supports:
     * - edit -a <oldName> -n <newName> : rename an appliance
     * - edit -a <oldName> -c <newCategory> : move appliance to another category
     * - edit -a <oldName> -p <power> : change appliance power
     * - edit -c <oldCategory> -n <newCategory> : rename category
     *
     * @param args command arguments entered by user
     */
    @Override
    public void execute(String[] args) {
        if (args.length == 0) {
            System.out.println("""
            Usage:
              edit -a <currentApplianceName> -n <newName>                  - to edit name.
              edit -a < currentApplianceName> -c <NewCategoryName>         - to move to a new category.
              edit -a <currentApplianceName> -p <powerW >= 0>              - to edit powerW.
              edit -c <currentCategoryName> -n <newCategoryName>           - to change CategoryName;
            
              You can combine flags, for example:
              edit -a Kettle -n Teapot -p 1500
            """);
            return;
        }

        // Category rename mode
        if (args[0].equals("-c")) {
            if (args.length < 4 || !args[2].equals("-n")) {
                System.out.println("Usage: edit -c <oldCategoryName> -n <newCategoryName>");
                return;
            }
            String oldCat = args[1];
            String newCat = args[3];
            manager.renameCategory(oldCat, newCat);
            return;
        }

        // Appliance edit mode
        String oldName = null;
        String newName = null;
        String newCategory = null;
        Integer newPower = null;

        for (int i = 0; i < args.length; i++) {
            switch (args[i]) {
                case "-a" -> oldName = args[++i];
                case "-n" -> newName = args[++i];
                case "-c" -> newCategory = args[++i];
                case "-p" -> newPower = Integer.parseInt(args[++i]);
            }
        }

        if (oldName == null) {
            System.out.println("Missing required flag: -a <currentApplianceName>");
            return;
        }

        manager.editAppliance(oldName, newName, newCategory, newPower);
    }
}
