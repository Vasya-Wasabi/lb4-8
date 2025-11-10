package commands;

import electricalAppliances.ApplianceManager;
import system.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.Map;
import java.util.List;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

/**
 * Loads appliance data from a JSON file and restores it into the system.
 * Uses Gson for JSON parsing and logs results or errors through LogService.
 */
public class LoadCommand implements Command {

    /** Reference to the appliance manager. */
    private final ApplianceManager manager;

    /**
     * Creates a new LoadCommand.
     *
     * @param manager appliance manager to update with loaded data
     */
    public LoadCommand(ApplianceManager manager) {
        this.manager = manager;
    }

    /**
     * Executes the load command.
     * Reads data from appliances.json and updates the manager categories.
     *
     * @param args not used
     */
    @Override
    public void execute(String[] args) {
        try (BufferedReader reader = new BufferedReader(new FileReader(
                "D:\\Політєх\\2 курс\\1 семестер\\ПП\\ЛР-4-8\\lb4-8\\home_appliances\\src\\main\\java\\system\\appliances.json"))) {

            Gson gson = new Gson();
            Type type = new TypeToken<Map<String, List<electricalAppliances.Appliance>>>(){}.getType();
            Map<String, List<electricalAppliances.Appliance>> data = gson.fromJson(reader, type);

            manager.setCategories(data);
            System.out.println("Data successfully loaded from appliances.json");
            LogService.info("Data loaded from appliances.json");

        } catch (com.google.gson.JsonSyntaxException | IllegalStateException e) {
            LogService.error("Error: Invalid JSON format: ",  e);
            System.out.println("Error: Invalid JSON format: " + e.getMessage());
        } catch (IOException e) {
            LogService.error("Error loading data: ", e);
        }
    }

}
