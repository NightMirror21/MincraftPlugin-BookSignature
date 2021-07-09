package ru.nightmirror.commands;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import ru.nightmirror.main.Config;

import java.util.ArrayList;
import java.util.List;

public class SignCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (commandSender instanceof Player) {
            Player player = (Player) commandSender;
            Config config = new Config();
            if (player.hasPermission("bs.sign") || player.isOp()) {
                if (player.getInventory().getItemInMainHand().getType().equals(Material.WRITTEN_BOOK)) {

                    ItemStack book = player.getInventory().getItemInMainHand();
                    ItemMeta bookMeta = book.getItemMeta();
                    List<String> bookLore = bookMeta.getLore();

                    String nickname = player.getName();
                    if (bookLore == null) {
                        List<String> newLore = new ArrayList<String>();
                        newLore.add(config.getSigned());
                        newLore.add(ChatColor.WHITE + nickname);
                        bookMeta.setLore(newLore);
                    } else if (bookLore.contains(config.getSigned()) && !bookLore.contains(ChatColor.WHITE + nickname)) {
                        bookLore.add(ChatColor.WHITE + nickname);
                        bookMeta.setLore(bookLore);
                    }

                    book.setItemMeta(bookMeta);
                } else {
                    player.sendMessage(config.getNoFoundBook());
                }
            } else {
                commandSender.sendMessage(config.getNotPermission());
            }
        } else {
            commandSender.sendMessage("This command only for players, sorry.");
        }
        return true;
    }
}
