package commands;

/**
 * Represents a generic command that can be executed by the system.
 * All command classes must implement this interface.
 */
public interface Command {

    /**
     * Executes the command with the specified arguments.
     *
     * @param args command arguments entered by the user
     */
    void execute(String[] args);
}
