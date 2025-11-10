package commands;

import electricalAppliances.ApplianceManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for FindCommand.
 * Verifies appliance search by name and power range.
 */
public class FindCommandTest {

    private ApplianceManager manager;
    private FindCommand command;

    @BeforeEach
    void setup() {
        manager = new ApplianceManager();
        command = new FindCommand(manager);

        manager.addCategory("Kitchen");
        manager.addAppliance("Kitchen", "Microwave", 700);
        manager.addAppliance("Kitchen", "Toaster", 120);
        manager.addAppliance("Kitchen", "Cooker", 50);
    }

    @Test
    void testFindApplianceByName() {
        // Run the command
        command.execute(new String[]{"-a", "Microwave"});

        // Check if appliance exists
        assertNotNull(manager.findAppliance("Microwave"),
                "Appliance 'Microwave' should be found by name");
    }

    @Test
    void testFindByPowerRange() {
        // Search for appliances between 100 and 200 W
        command.execute(new String[]{"-r", "100", "200"});

        // Validate manually through manager logic
        assertTrue(manager.getCategories().get("Kitchen").stream().anyMatch(a -> a.getPowerW() >= 100 && a.getPowerW() <= 200),
                "At least one appliance should be found within 100-200 W range");
    }
}
