package commands;

import electricalAppliances.ApplianceManager;

/**
 * Sorts appliances by power in descending order.
 * Uses the appliance manager to perform sorting.
 */
public class SortCommand implements Command {

    /** Reference to the appliance manager. */
    private final ApplianceManager manager;

    /**
     * Creates a new SortCommand.
     *
     * @param manager appliance manager instance
     */
    public SortCommand(ApplianceManager manager) {
        this.manager = manager;
    }

    /**
     * Executes the sort command.
     * Sorts all appliances by their power consumption.
     *
     * @param args not used
     */
    @Override
    public void execute(String[] args) {
        manager.sortByPower();
    }
}
