package commands;

/**
 * Displays a list of all available commands and their descriptions.
 * Provides a quick overview of the system’s functionality.
 */
public class HelpCommand implements Command {

    /**
     * Executes the help command.
     * Prints a list of all supported commands and their usage.
     *
     * @param args not used
     */
    @Override
    public void execute(String[] args) {
        System.out.println("""
    Available commands:
        help                - Shows all available commands.
        cls                 - Clears the console window.
        version             - Displays the program version.
        exit                - Closes the application safely.
        add                 - Adds a new appliance with specified parameters or category.
        rm                  - Removes appliance or category.
        edit                - Modifies appliance parameters (e.g., power, name, category).
        ls                  - Lists appliances and categories currently in the system.
        plug                - Connect or disconnect an appliance to power or to list connected appliance .
        total               - Calculates total power consumption of all connected devices.
        sort                - Sorts appliances by power consumption or name.
        find                - Searches for appliances within a specified power or name range.
        save                - Saves all current data to a file (e.g., appliances.json).
        load                - Loads appliances data from a file.
        log                 - Opens or displays recent log file entries.
    
    For detailed help on a specific command, type 'commandName'.
    """);
    }
}
