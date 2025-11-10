package commands;

import electricalAppliances.ApplianceManager;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for PlugCommand.
 * Verifies plug and unplug operations.
 */
public class PlugCommandTest {
    private ApplianceManager manager;
    private PlugCommand command;

    @BeforeEach
    void setup() {
        manager = new ApplianceManager();
        command = new PlugCommand(manager);
        manager.addCategory("Kitchen");
        manager.addAppliance("Kitchen", "Toaster", 120);
    }

    @Test
    void plugOnTest() {
        command.execute(new String[] {"-on", "Toaster"});
        assertTrue(manager.getCategories().get("Kitchen").get(0).isPlugged());
    }

    @Test
    void plugOffTest() {
        command.execute(new String[] {"-off", "Toaster"});
        assertFalse(manager.getCategories().get("Kitchen").get(0).isPlugged());
    }
}

