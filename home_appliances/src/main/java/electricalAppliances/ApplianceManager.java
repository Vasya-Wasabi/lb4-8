package electricalAppliances;

import system.LogService;

import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.Comparator;

/**
 * Manages appliances grouped by categories.
 * Provides operations for adding, editing, removing, listing,
 * and searching appliances, as well as calculating total power usage.
 * All actions are logged using LogService.
 */
public class ApplianceManager {

    /** Stores appliance categories and their corresponding appliances. */
    private final Map<String,List<Appliance>> categories = new HashMap<>();

    /** Adds a new category if it does not already exist. */
    public void addCategory(String name) {
        if (categories.containsKey(name)) {
            System.out.println("Category already exists: " + name);
            LogService.warn("Attempted to add existing category: " + name);
            return;
        }
        categories.put(name, new ArrayList<>());
        System.out.println("Category added: " + name);
        LogService.info("Category added: " + name);
    }

    /** Removes a category by name. */
    public void removeCategory(String name) {
        if(categories.remove(name) != null){
            System.out.println("Category successfully removed: " + name);
            LogService.info("Category removed: " + name);
        }
        else{
            System.out.println("Category not found: " + name);
            LogService.warn("Attempted to remove non-existing category: " + name);
        }
    }

    /** Renames an existing category. */
    public void renameCategory(String oldName, String newName) {
        if(!categories.containsKey(oldName)){
            System.out.println("Category does not exist: " + oldName);
            LogService.warn("Attempted to rename missing category:Category does not exist " + oldName);
            return;
        }
        if(categories.containsKey(newName)){
            System.out.println("Category already exists: " + newName);
            LogService.warn("Attempted to rename to existing category: " + newName);
            return;
        }

        List<Appliance> device = categories.remove(oldName);
        for(Appliance d : device){
            d.setCategory(newName);
        }
        categories.put(newName, device);

        System.out.println("Categories renamed from " + oldName + " to " + newName);
        LogService.info("Category renamed from " + oldName + " to " + newName);

    }

    /** Adds a new appliance to a given category. */
    public void addAppliance(String category, String appliance, double powerW) {
        if (!categories.containsKey(category)) {
            System.out.println("Category doesn't exists: " + category);
            LogService.warn("Failed to add appliance, category missing: " + category);
            return;
        }
        categories.get(category).add(new Appliance(appliance, category, powerW));
        System.out.println("Appliance added: " + appliance);
        LogService.info("Appliance added: " + appliance);
    }

    /** Removes an appliance by name across all categories. */
    public void removeAppliance(String name) {
        boolean removed = false;
        for (List<Appliance> list : categories.values()) {
            if (list.removeIf(d -> d.getName().equals(name))) {
                removed = true;
            }
        }
        if (removed) {
            System.out.println("Appliance removed: " + name);
            LogService.info("Appliance removed: " + name);
        }
        else {
            System.out.println("Appliance not found: " + name);
            LogService.warn("Attempted to remove non-existing appliance: " + name);
        }
    }

    /** Edits appliance properties (name, category, power). */
    public void editAppliance(String oldName, String newName, String newCategory, Integer newPower) {
        Appliance d = findAppliance(oldName);
        if (d == null) {
            System.out.println("Appliance not found: " + oldName);
            LogService.warn("Attempted to edit missing appliance: " + oldName);
            return;
        }

        if (newName != null) d.setName(newName);
        if (newPower != null) d.setPowerW(newPower);

        if (newCategory != null && !newCategory.equals(d.getCategory())) {
            if (!categories.containsKey(newCategory)) {
                System.out.println("Category doesn't exists: " + newCategory);
                LogService.warn("Attempted to move appliance to missing category: " + newCategory);
                return;
            }
            categories.get(d.getCategory()).remove(d);
            d.setCategory(newCategory);
            categories.get(newCategory).add(d);
        }

        System.out.println("Appliance updated: " + d);
        LogService.info("Appliance updated: " + d);
    }

    /** Finds an appliance by its name. */
    public Appliance findAppliance(String name) {
        for (List<Appliance> list : categories.values()) {
            for (Appliance d : list) {
                if (d.getName().equalsIgnoreCase(name)) return d;
            }
        }
        return null;
    }

    /** Displays all available categories. */
    public void listCategories() {
        if (categories.isEmpty()) {
            System.out.println("No categories found!");
            LogService.warn("No categories available to list.");
            return;
        }
        System.out.println("Categories:");
        LogService.info("Displayed list of categories");
        categories.keySet().forEach(c -> System.out.println(" - " + c));
    }

    /** Lists all appliances within a specific category. */
    public void listApplianceByCategories(String category) {
        if (!categories.containsKey(category)) {
            System.out.println("Category not found: " + category);
            LogService.warn("Tried to list appliances in missing category: " + category);
            return;
        }
        List<Appliance> list = categories.get(category);
        if (list.isEmpty()) {
            System.out.println("No appliance in category: " + category);
            LogService.warn("Category is empty: " + category);
            return;
        }
        System.out.println("Appliance in " + category + ":");
        LogService.info("Listed appliances in category: " + category);
        list.forEach(System.out::println);
    }

    /** Lists all appliances from all categories. */
    public void listAllAppliance() {
        boolean empty = true;
        for (Map.Entry<String, List<Appliance>> entry : categories.entrySet()) {
            if (entry.getValue().isEmpty()) continue;
            empty = false;
            System.out.println("[" + entry.getKey() + "]");
            entry.getValue().forEach(System.out::println);
        }
        if (empty) {
            System.out.println("No appliances found.");
            LogService.warn("No appliances available to list.");
        } else {
            LogService.info("Displayed list of all appliances");
        }
    }

    /** Lists only appliances that are currently plugged in. */
    public void listPluggedAppliances() {
        boolean found = false;
        for (Map.Entry<String, List<Appliance>> entry : categories.entrySet()) {
            List<Appliance> list = entry.getValue();
            for (Appliance d : list) {
                if (d.isPlugged()) {
                    if (!found) {
                        System.out.println("Plugged appliances:");
                        found = true;
                    }
                    System.out.println(d);
                }
            }
        }
        if (found)
            LogService.info("Displayed list of plugged appliances");
        else {
            System.out.println("No plugged appliances found.");
            LogService.warn("No plugged appliances found.");
        }
    }

    /** Plugs in an appliance by name. */
    public void plugAppliance(String name) {
        Appliance d = findAppliance(name);
        if (d == null) {
            System.out.println("Appliance not found: " + name);
            LogService.warn("Attempted to plug missing appliance: " + name);
            return;
        }
        d.setPlugged(true);
        System.out.println("Appliance plugged in: " + d.getName());
        LogService.info("Appliance plugged in: " + d.getName());
    }

    /** Unplugs an appliance by name. */
    public void unplugAppliance(String name) {
        Appliance d = findAppliance(name);
        if (d == null) {
            System.out.println("Appliance not found: " + name);
            LogService.warn("Attempted to unplug missing appliance: " + name);
            return;
        }
        d.setPlugged(false);
        System.out.println("Appliance unplugged: " + d.getName());
        LogService.info("Appliance unplugged: " + d.getName());
    }

    /** Calculates total power of all plugged-in appliances. */
    public double totalPower() {
        double sum = 0;
        for (List<Appliance> list : categories.values()) {
            for (Appliance d : list) {
                if (d.isPlugged()) sum += d.getPowerW();
            }
        }
        return sum;
    }

    /** Sorts all appliances by power in descending order. */
    public void sortByPower() {
        for (List<Appliance> list : categories.values()) {
            list.sort(Comparator.comparingDouble(Appliance::getPowerW).reversed());
        }
        System.out.println("Appliances sorted by power (descending).");
        LogService.info("Appliances sorted by power.");
    }

    /** Finds and prints an appliance by name. */
    public void find(String name) {
        Appliance d = findAppliance(name);
        if (d != null)
            System.out.println(d);
        else {
            System.out.println("Appliance not found: " + name);
            LogService.warn("Appliance not found: " + name);
        }

    }

    /** Finds and lists all appliances within the given power range. */
    public void findByPowerRange(int min, int max) {
        boolean found = false;
        for (List<Appliance> list : categories.values()) {
            for (Appliance d : list) {
                if (d.getPowerW() >= min && d.getPowerW() <= max) {
                    System.out.println(d);
                    LogService.info("Found appliance in range " + min + "-" + max + " W: " + d.getName());
                    found = true;
                }
            }
        }
        if (!found) {
            System.out.println("No appliance in range " + min + "-" + max + " W.");
            LogService.warn("No appliance in range " + min + "-" + max + " W.");
        }
    }

    /** Returns all categories with their appliances. */
    public Map<String, List<Appliance>> getCategories() {
        return categories;
    }

    /** Replaces the current category map with a new one. */
    public void setCategories(Map<String, List<Appliance>> newCategories) {
        categories.clear();
        categories.putAll(newCategories);
        LogService.info("Categories replaced from external source.");
    }
}
