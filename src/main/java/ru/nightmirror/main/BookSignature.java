package ru.nightmirror.main;

import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;
import ru.nightmirror.commands.BsCommand;
import ru.nightmirror.commands.BsTabComplete;
import ru.nightmirror.commands.SignCommand;

import java.util.logging.Logger;

public class BookSignature extends JavaPlugin {

    private final Logger log = Logger.getLogger("BookSignature");

    @Override
    public void onEnable() {
        log.info(ChatColor.GOLD + "Loading...");

        // Check config
        Config config = new Config();
        config.setPlugin(this);
        config.check();

        // Register commands and set tab complete
        getCommand("bs").setExecutor(new BsCommand(this));
        getCommand("bs").setTabCompleter(new BsTabComplete());
        getCommand("sign").setExecutor(new SignCommand());

        log.info(ChatColor.GOLD + "Enabled.");
    }

    @Override
    public void onDisable() {
        log.info(ChatColor.GOLD + "Disabled.");
    }
}
