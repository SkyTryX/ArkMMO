package fr.skytryx.arkmmo.events;

import fr.skytryx.arkmmo.utils.Database;
import org.bukkit.Location;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;

public class LobbyInteract implements Listener {

    @EventHandler
    public void lobbyinteract(PlayerInteractEvent event){
        if(!event.getPlayer().isOp() && event.getClickedBlock() != null){
            Location clicked = event.getClickedBlock().getLocation();
            Database db = new Database("lobbyregion");
            db.getDatas().getValues(false).forEach((path, obj) ->{
                if(db.getDataDouble(path+".x1") < clicked.getX() && clicked.getX() < db.getDataDouble(path+".x2") &&
                   db.getDataDouble(path+".y1") < clicked.getY() && clicked.getY() < db.getDataDouble(path+".y2") &&
                   db.getDataDouble(path+".z1") < clicked.getZ() && clicked.getZ() < db.getDataDouble(path+".z2")){
                    event.setCancelled(true);
                }
            });
        }
    }
}
