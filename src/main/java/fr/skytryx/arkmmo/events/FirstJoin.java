package fr.skytryx.arkmmo.events;

import fr.skytryx.arkmmo.api.Database;
import fr.skytryx.arkmmo.api.classes.ArkPlayer;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class FirstJoin implements Listener {

    @EventHandler
    public void onFirstJoin(PlayerJoinEvent event){
        if(!event.getPlayer().hasPlayedBefore()){
            Database db = new Database("arkplayer");
            db.addData(String.valueOf(event.getPlayer().getUniqueId()), new ArkPlayer(event.getPlayer()));
            db.save();
        }
    }
}
