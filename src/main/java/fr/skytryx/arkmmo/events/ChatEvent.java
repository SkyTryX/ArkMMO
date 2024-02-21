package fr.skytryx.arkmmo.events;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class ChatEvent implements Listener {

    @EventHandler
    public void onChat(AsyncPlayerChatEvent event){
        event.setCancelled(true);
        Bukkit.broadcastMessage(event.getPlayer().getName()+"§8: §f"+event.getMessage());
    }
}
