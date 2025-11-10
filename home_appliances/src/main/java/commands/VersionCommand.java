package commands;

/**
 * Displays the current program version information.
 */
public class VersionCommand implements Command {
    /**
     * Executes the version command.
     * Prints the application version and release date.
     *
     * @param args not used
     */
   @Override
    public void execute(String[] args) {
        System.out.println("Home Appliances version \"1.0.0\" 2025-11-03");
    }
}
