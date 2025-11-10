package commands;

import electricalAppliances.ApplianceManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Unit tests for AddCommand.
 * Verifies adding new categories and appliances.
 */
public class AddCommandTest {
    private ApplianceManager manager;
    private AddCommand command;

    @BeforeEach
    void setup (){
        manager = new ApplianceManager();
        command = new AddCommand(manager);
    }

    @Test
    void addNewsCategorySuccessfully() {
        command.execute(new String[]{"-c", "Kitchen"});
        assertTrue(manager.getCategories().containsKey("Kitchen"));
    }

    @Test
    void addNewsApplianceSuccessfully() {
        manager.addCategory("Kitchen");
        command.execute(new String[]{"-a", "Toaster", "-c", "Kitchen", "-p", "124"});
        assertTrue(manager.getCategories().get("Kitchen").stream().anyMatch(a -> a.getName().equals("Toaster")));
    }
}

