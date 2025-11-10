package commands;

import system.LogService;


/**
 * Exits the application safely.
 * Logs the exit event before terminating the program.
 */
public class ExitCommand implements Command{
    /**
     * Executes the exit command.
     * Logs the exit and terminates the program.
     *
     * @param args not used
     */
    @Override
    public void execute(String[] args){
        LogService.info("Program exited by user.");
        System.exit(0);
    }
}
