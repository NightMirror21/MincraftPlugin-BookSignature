package ru.nightmirror.main;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class Refresher implements Runnable {

    private BookSignature plugin;
    private final Logger LOG = Logger.getLogger("BookSignature");
    private final Config CONFIG = new Config();

    public Refresher(BookSignature plugin) {
        this.plugin = plugin;
    }

    @Override
    public void run() {
        while (true) {
            try {
                final String ONLINE = CONFIG.getLine("player-online");
                final String OFFLINE = CONFIG.getLine("player-offline");

                for (Player player : plugin.getServer().getOnlinePlayers()) {
                    Inventory inventory = player.getInventory();

                    if (inventory != null) {
                        ItemStack[] items = inventory.getContents();

                        for (int i = 0; items.length != i; ++i) {
                            if (items[i] != null && items[i].getType() == Material.WRITTEN_BOOK) {
                                ItemStack book = items[i];
                                ItemMeta meta = book.getItemMeta();

                                if (meta.hasLore()) {
                                    List<String> bookLore = meta.getLore();
                                    List<String> newLore = new ArrayList<>();

                                    for (String lore : bookLore) {
                                        if (!lore.equals(CONFIG.getLine("signed"))) {
                                            String nickname = ChatColor.stripColor(lore).split(" ")[0];

                                            if (plugin.getServer().getPlayer(nickname) != null)
                                                newLore.add(ChatColor.WHITE + nickname + " " + ONLINE);
                                            else
                                                newLore.add(ChatColor.WHITE + nickname + " " + OFFLINE);
                                        } else {
                                            newLore.add(CONFIG.getLine("signed"));
                                        }
                                    }

                                    meta.setLore(newLore);
                                }

                                book.setItemMeta(meta);
                            }
                        }
                    }
                }

                Thread.currentThread().sleep(3000);
            } catch (Exception e) {
                LOG.severe(e.getMessage());
            }
        }
    }
}
