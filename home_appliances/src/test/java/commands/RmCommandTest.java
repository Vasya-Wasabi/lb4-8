package commands;

import electricalAppliances.ApplianceManager;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for RmCommand.
 * Verifies removal of categories and appliances.
 */
public class RmCommandTest {
    private ApplianceManager manager;
    private RmCommand command;

    @BeforeEach
    void setup() {
        manager = new ApplianceManager();
        command = new RmCommand(manager);
        manager.addCategory("Kitchen");
        manager.addAppliance("Kitchen", "Toaster", 120);
    }

    @Test
    void rmCategory() {
        command.execute(new String[]{"-c", "Kitchen"});
        assertFalse(manager.getCategories().containsKey("Kitchen"));
    }

    @Test
    void rmAppliance() {
        command.execute(new String[]{"-a", "Toaster", "-c", "Kitchen"});
        assertEquals(0, manager.getCategories().get("Kitchen").size());
    }

}
