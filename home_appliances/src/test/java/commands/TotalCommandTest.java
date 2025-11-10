package commands;

import electricalAppliances.ApplianceManager;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit test for TotalCommand.
 * Verifies that total power is calculated correctly for plugged appliances.
 */
public class TotalCommandTest {
    private ApplianceManager manager;
    private TotalCommand command;

    @BeforeEach
    void setup() {
        manager = new ApplianceManager();
        command = new TotalCommand(manager);
        manager.addCategory("Kitchen");
        manager.addAppliance("Kitchen", "Microwave", 300);
        manager.addAppliance("Kitchen", "Toaster", 120);
        manager.addAppliance("Kitchen", "Cooker", 50);

        // Plug in Microwave and Cooker
        manager.getCategories().get("Kitchen").get(0).setPlugged(true);
        manager.getCategories().get("Kitchen").get(2).setPlugged(true);
    }

    @Test
    void TotalAppliancesPowerW() {
        // Expected total = 300 + 50 = 350
        command.execute(new String[]{});
        assertEquals(350.0, manager.totalPower());
    }
}
