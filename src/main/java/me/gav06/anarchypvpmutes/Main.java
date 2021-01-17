package me.gav06.anarchypvpmutes;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;

public final class Main extends JavaPlugin implements Listener {

    public static ArrayList<String> mutes = new ArrayList<>();

    @Override
    public void onEnable() {
        // Plugin startup logic
        this.getServer().getPluginManager().registerEvents(this, this);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (label.equalsIgnoreCase("mute")) {
            if (sender.hasPermission("mutes.use")) {
                if (args.length == 0) {

                } else {
                    if (getServer().getPlayerExact(args[0]) != null) {
                        sender.sendMessage(ChatColor.RED + "The specified player is not online");
                    }
                }
            } else {
                sender.sendMessage(ChatColor.RED + "no permission");
            }
        }

        return true;
    }

    @EventHandler
    public void onChat(AsyncPlayerChatEvent event) {
        if (mutes.contains(event.getPlayer().getUniqueId().toString())) {
            event.setCancelled(true);
            event.getPlayer().sendMessage(ChatColor.RED + "You are muted!");
        }
    }
}
