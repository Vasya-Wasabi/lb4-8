package commands;

import electricalAppliances.ApplianceManager;

/**
 * Manages appliance power connections.
 * Allows turning devices on/off or listing all plugged-in appliances.
 */
public class PlugCommand implements Command {

    /** Reference to the appliance manager. */
    private final ApplianceManager manager;

    /**
     * Creates a new PlugCommand.
     *
     * @param manager appliance manager instance
     */
    public PlugCommand(ApplianceManager manager) {
        this.manager = manager;
    }

    /**
     * Executes the plug command.
     * Handles turning appliances on, off, or listing active ones.
     *
     * @param args command arguments
     */
    @Override
    public void execute(String[] args) {
        if (args.length < 2) {
            System.out.println("""
            Usage:
              plug -on <deviceName>     - plug appliance into socket
              plug -off <deviceName>    - unplug appliance from socket
              plug -ls                  - list all plugged appliances
            """);
            return;
        }

        switch (args[0]) {
            case "-on" -> {
                String name = args[1];
                manager.plugAppliance(name);
            }
            case "-off" -> {
                String name = args[1];
                manager.unplugAppliance(name);
            }
            case "-ls" -> manager.listPluggedAppliances();
            default -> System.out.println("Unknown flag: " + args[0]);
        }
    }
}
