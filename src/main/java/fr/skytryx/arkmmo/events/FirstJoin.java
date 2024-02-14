package fr.skytryx.arkmmo.events;

import fr.skytryx.arkmmo.api.classes.ArkPlayer;
import org.bukkit.Sound;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class FirstJoin implements Listener {

    @EventHandler
    public void onFirstJoin(PlayerJoinEvent event) {
        if (!event.getPlayer().hasPlayedBefore()) {
            new ArkPlayer(event.getPlayer()).save();
            event.getPlayer().sendTitle("§bBIENVENUE!", "sur Arkxia MMORPG", 1, 80, 1);
            event.getPlayer().playSound(event.getPlayer().getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 3.0F, 0.5F);
            event.getPlayer().sendMessage("§c[First Join] §bMessage quand tu rejoins pour la première fois le serveur");
        }
    }
}
