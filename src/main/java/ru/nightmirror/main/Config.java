package ru.nightmirror.main;

import org.bukkit.configuration.file.FileConfiguration;

import java.io.File;

public class Config {

    private static BookSignature plugin;
    private static FileConfiguration config;

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

    public String getPluginReloaded() {
        return config.getString("plugin-reloaded").replace("&", "§");
    }

    public String getNoFoundBook() {
        return config.getString("not-found-book").replace("&", "§");
    }

    public String getNotPermission() {
        return config.getString("not-permission").replace("&", "§");
    }

    public String getSigned() {
        return config.getString("signed").replace("&", "§");
    }
}
