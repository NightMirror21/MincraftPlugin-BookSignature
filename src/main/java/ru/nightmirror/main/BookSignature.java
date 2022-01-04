package ru.nightmirror.main;

import org.bstats.bukkit.Metrics;
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
        // Setup and check config
        Config config = Config.getInstance();
        config.setPlugin(this);
        config.check();

        // Register commands and set tab complete
        getCommand("bs").setExecutor(new BsCommand());
        getCommand("bs").setTabCompleter(new BsTabComplete());
        getCommand("sign").setExecutor(new SignCommand());

        new Thread(new Refresher(this)).start();

        Metrics metrics = new Metrics(this, 13841);

        log.info(ChatColor.GOLD + "Enabled.");
    }

    @Override
    public void onDisable() {
        log.info(ChatColor.GOLD + "Disabled.");
    }
}
