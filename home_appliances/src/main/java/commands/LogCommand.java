package commands;

import system.LogService;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * Displays the content of the application log file.
 * Reads and prints all lines from app.log to the console.
 */
public class LogCommand implements Command {

    /**
     * Executes the log command.
     * Prints the contents of the log file and logs the event.
     *
     * @param args not used
     */
    @Override
    public void execute(String[] args) {
        try (BufferedReader reader = new BufferedReader(new FileReader(
                "D:\\Політєх\\2 курс\\1 семестер\\ПП\\ЛР-4-8\\lb4-8\\home_appliances\\src\\main\\java\\system\\logs\\app.log"))){
            System.out.println("---Log file content---");
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
            System.out.println("---End of log---");
            LogService.info("Displayed log content.");

        }catch (IOException e) {
            System.out.println("Error reading log file:" + e.getMessage());
            LogService.error("Error reading log file:", e);
        }
    }
}
