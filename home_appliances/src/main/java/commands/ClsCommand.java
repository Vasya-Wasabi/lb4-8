package commands;

/**
 * Clears the console screen.
 * Works on Windows systems using the "cmd /c cls" command.
 */
public class ClsCommand implements Command {

    /**
     * Executes the clear-screen command.
     * If the operation fails, prints an error message.
     *
     * @param args not used
     */
    public void execute(String[] args) {
        try {
            new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            System.out.println("Home Appliances (type 'help' for commands)");
        } catch (Exception e) {
            System.out.println("Cannot clear screen.");
        }
    }
}
