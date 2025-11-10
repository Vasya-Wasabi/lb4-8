package electricalAppliances;

/**
 * Represents a single electrical appliance with its basic properties.
 * Each appliance has a name, category, power (in watts), and plug state.
 */
public class Appliance {

    /** Appliance name. */
    private String name;

    /** Category the appliance belongs to. */
    private String category;

    /** Power consumption in watts. */
    private double powerW;

    /** Indicates whether the appliance is plugged in. */
    private boolean plugged;

    /**
     * Creates a new appliance with the given parameters.
     * The appliance is unplugged by default.
     *
     * @param name     appliance name
     * @param category category name
     * @param powerW   power consumption in watts
     */
    public Appliance(String name, String category, double powerW) {
        this.name = name;
        this.category = category;
        this .powerW = powerW;
        this.plugged = false;
    }

    /** Returns the appliance name. */
    public String getName() {return this.name;}

    /** Returns the category name. */
    public String getCategory() {return this.category;}

    /** Returns the power in watts. */
    public double getPowerW() {return this.powerW;}

    /** Returns true if the appliance is plugged in. */
    public boolean isPlugged() {return this.plugged;}

    /** Sets a new appliance name. */
    public void setName(String name) {this.name = name;}

    /** Sets a new category name. */
    public void setCategory(String category) {this.category = category;}

    /** Sets the power value in watts. */
    public void setPowerW(double powerW) {this.powerW = powerW;}

    /** Sets the plug state. */
    public void setPlugged(boolean plugged) {this.plugged = plugged;}

    /** Returns a formatted string with all appliance information. */
    @Override
    public String toString() {
        return String.format(
                "%-15s | %-14s | %7.1f W | %-3s |",
                name, category, powerW, plugged ? "ON" : "OFF"
        );
    }

}
