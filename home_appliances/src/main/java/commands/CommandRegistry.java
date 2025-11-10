package commands;

import electricalAppliances.ApplianceManager;
import java.util.Map;
import java.util.HashMap;

/**
 * Stores and manages all available commands in the system.
 * Each command is registered by name and can be retrieved by key.
 */
public class CommandRegistry {

    /** Map of command names to their corresponding command objects. */
    private final Map<String, Command> commands = new HashMap<>();

    /**
     * Initializes the registry with all supported commands.
     *
     * @param manager appliance manager used by commands that modify or access data
     */
    public CommandRegistry(ApplianceManager manager) {
        commands.put("help", new HelpCommand());
        commands.put("version", new VersionCommand());
        commands.put("cls", new ClsCommand());
        commands.put("exit", new ExitCommand());
        commands.put("add", new AddCommand(manager));
        commands.put("ls", new LsCommand(manager));
        commands.put("rm", new RmCommand(manager));
        commands.put("edit", new EditCommand(manager));
        commands.put("plug", new PlugCommand(manager));
        commands.put("total", new TotalCommand(manager));
        commands.put("sort", new SortCommand(manager));
        commands.put("find", new FindCommand(manager));
        commands.put("save", new SaveCommand(manager));
        commands.put("load", new LoadCommand(manager));
        commands.put("log", new LogCommand());
    }

    /**
     * Retrieves a command by its name.
     *
     * @param name command name
     * @return command instance, or null if not found
     */
    public Command get(String name) {
        return commands.get(name);
    }
}
