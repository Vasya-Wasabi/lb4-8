package commands;

import electricalAppliances.ApplianceManager;

/**
 * Displays the total power consumption of all plugged appliances.
 * Uses the appliance manager to calculate the total.
 */
public class TotalCommand implements Command {

    /** Reference to the appliance manager. */
    private final ApplianceManager manager;

    /**
     * Creates a new TotalCommand.
     *
     * @param manager appliance manager instance
     */
    public TotalCommand(ApplianceManager manager) {
        this.manager = manager;
    }

    /**
     * Executes the total command.
     * Prints total power consumption of all connected appliances.
     *
     * @param args not used
     */
    @Override
    public void execute(String[] args) {
        System.out.println("Total power = " + manager.totalPower() + '.');
    }
}
