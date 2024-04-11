package fr.skytryx.arkmmo.events;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.server.ServerLoadEvent;

import java.util.Objects;
import java.util.Random;

public class ChatEvent implements Listener {

    @EventHandler
    public void onChat(AsyncPlayerChatEvent event){
        event.setCancelled(true);
        Bukkit.broadcastMessage("§7"+event.getPlayer().getName()+"§8: §7"+event.getMessage());
    }

    @EventHandler
    public void randomTips(ServerLoadEvent event){
        Bukkit.getScheduler().scheduleSyncRepeatingTask(Objects.requireNonNull(Bukkit.getPluginManager().getPlugin("ArkMMO")), ()->{
            Random random = new Random();
            String msg = "§6[Tip] §3";
            int rdm_int = random.nextInt(0, 5);
            if (rdm_int == 0) {
                msg += "TIP 1";
            } else if (rdm_int == 1){
                msg += "TIP 2";
            } else if (rdm_int == 2){
                msg += "TIP 3";
            } else if (rdm_int == 3){
                msg += "TIP 4";
            } else if (rdm_int == 4){
                msg += "TIP 5";
            }
            Bukkit.broadcastMessage(msg);
        }, 100, 2400);
    }
}
