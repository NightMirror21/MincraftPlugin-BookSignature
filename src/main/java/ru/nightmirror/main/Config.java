package ru.nightmirror.main;

import org.bukkit.configuration.file.FileConfiguration;

import java.io.File;

public class Config {

    private static Config instance;
    private BookSignature plugin;
    private FileConfiguration config;

    public static Config getInstance() {
        if (instance == null) instance = new Config();
        return instance;
    }

    public void setPlugin(BookSignature plugin) {
        this.plugin = plugin;
    }

    public void check() {
        File configFile = new File(plugin.getDataFolder() + File.separator + "config.yml");
        if (!configFile.exists()) {
            plugin.getConfig().options().copyDefaults(true);
            plugin.saveConfig();
        }
        plugin.reloadConfig();
        config = plugin.getConfig();
    }

    public String getLine(String path) {
        return config.getString(path).replace("&", "§");
    }
}
