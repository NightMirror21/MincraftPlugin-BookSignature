package ru.nightmirror.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import ru.nightmirror.main.BookSignature;
import ru.nightmirror.main.Config;

public class BsCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (strings.length > 0) {
            if (strings[0].equals("reload")) {
                final Config CONFIG = Config.getInstance();

                if (commandSender instanceof Player) {
                    if (commandSender.hasPermission("bs.reload") || commandSender.isOp()) {
                        CONFIG.check();
                        commandSender.sendMessage(CONFIG.getLine("plugin-reloaded"));
                    } else {
                        commandSender.sendMessage(CONFIG.getLine("not-permission"));
                    }
                } else {
                    CONFIG.check();
                    commandSender.sendMessage(CONFIG.getLine("plugin-reloaded"));
                }
            }
        }
        return true;
    }
}
