package commands;

import electricalAppliances.ApplianceManager;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for EditCommand.
 * Verifies editing appliance and category properties.
 */
public class EditCommandTest {

    private ApplianceManager manager;
    private EditCommand command;

    @BeforeEach
    void setup() {
        manager = new ApplianceManager();
        command = new EditCommand(manager);
        manager.addCategory("Kitchen");
        manager.addCategory("LivingRoom");
        manager.addAppliance("Kitchen", "Toaster", 120);
    }

    @Test
    void editApplianceName(){
        command.execute(new String[]{"-a", "Toaster", "-n", "newToaster"});
        assertTrue(manager.getCategories().get("Kitchen").stream().anyMatch(a -> a.getName().equals("newToaster")));
    }

     @Test
    void moveApplianceToNewCategory() {
        command.execute(new String[]{"-a", "Toaster", "-c", "LivingRoom"});
        assertEquals(0, manager.getCategories().get("Kitchen").size());
        assertEquals(1, manager.getCategories().get("LivingRoom").size());
    }

    @Test
    void editAppliancePowerW() {
        command.execute(new String[]{"-a", "Toaster", "-p", "100"});
        assertEquals(100, manager.getCategories().get("Kitchen").get(0).getPowerW());
    }

    @Test
    void editCategoryName() {
        command.execute(new String[]{"-c", "Kitchen", "-n", "newKitchen"});
        assertTrue(manager.getCategories().containsKey("newKitchen"));
        assertFalse(manager.getCategories().containsKey("Kitchen"));
    }
}
