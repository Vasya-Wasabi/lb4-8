package commands;

import electricalAppliances.ApplianceManager;
import java.io.FileWriter;
import java.io.IOException;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import system.LogService;

/**
 * Saves all appliance data to a JSON file.
 * Serializes categories and appliances using Gson and logs the result.
 */
public class SaveCommand implements Command {

    /** Reference to the appliance manager. */
    private final ApplianceManager manager;

    /**
     * Creates a new SaveCommand.
     *
     * @param manager appliance manager instance
     */
    public SaveCommand(ApplianceManager manager) {
        this.manager = manager;
    }

    /**
     * Executes the save command.
     * Exports all appliances and categories to a JSON file.
     *
     * @param args not used
     */
    @Override
    public void execute(String[] args) {
        try (FileWriter writer = new FileWriter("D:\\Політєх\\2 курс\\1 семестер\\ПП\\ЛР-4-8\\lb4-8\\home_appliances\\src\\main\\java\\system\\appliances.json", false)) {
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            gson.toJson(manager.getCategories(), writer);

            System.out.println("Data successfully saved to appliances.json");
            LogService.info("Data saved to appliances.json");
        } catch (IOException e) {
            System.out.println("Error saving data: " + e.getMessage());
            LogService.error("Error saving data: ", e);
        }
    }
}
