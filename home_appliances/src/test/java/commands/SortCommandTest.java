package commands;

import electricalAppliances.ApplianceManager;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit test for SortCommand.
 * Ensures appliances are sorted by power in descending order.
 */
public class SortCommandTest {
    private ApplianceManager manager;
    private SortCommand command;

    @BeforeEach
    void setup() {
        manager = new ApplianceManager();
        command = new SortCommand(manager);
        manager.addCategory("Kitchen");
        manager.addAppliance("Kitchen", "Microwave", 300);
        manager.addAppliance("Kitchen", "Toaster", 120);
        manager.addAppliance("Kitchen", "Cooker", 50);
    }

    @Test
    void sortAppliances() {

        command.execute(new String[]{});
        assertEquals("Microwave", manager.getCategories().get("Kitchen").get(0).getName());
        assertEquals("Toaster", manager.getCategories().get("Kitchen").get(1).getName());
        assertEquals("Cooker", manager.getCategories().get("Kitchen").get(2).getName());
    }
}
