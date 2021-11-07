package ru.nightmirror.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import ru.nightmirror.main.BookSignature;
import ru.nightmirror.main.Config;

public class BsCommand implements CommandExecutor {

    private BookSignature plugin;

    public BsCommand(BookSignature plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (strings.length > 0) {
            if (strings[0].equals("reload")) {
                Config config = new Config();

                if (commandSender instanceof Player) {
                    if (commandSender.hasPermission("bs.reload") || commandSender.isOp()) {
                        config.check();
                        commandSender.sendMessage(config.getLine("plugin-reloaded"));
                    } else {
                        commandSender.sendMessage(config.getLine("not-permission"));
                    }
                } else {
                    config.check();
                    commandSender.sendMessage(config.getLine("plugin-reloaded"));
                }
            }
        }
        return true;
    }
}
