package fr.skytryx.arkmmo.events;

import fr.skytryx.arkmmo.api.Ftion;
import fr.skytryx.arkmmo.api.classes.ArkPlayer;
import fr.skytryx.arkmmo.api.classes.Claim;
import fr.skytryx.arkmmo.commands.claim.CommandBypassclaim;
import org.bukkit.Chunk;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.PlayerInteractEvent;

public class ClaimCheck implements Listener {

    @EventHandler
    public void onBreakChunk(BlockBreakEvent event){
        Chunk chunk = event.getBlock().getChunk();
        Claim claim = Ftion.loadClaim(chunk, event.getBlock().getWorld());
        ArkPlayer player = Ftion.getArkPlayer(event.getPlayer());
        if(claim != null && player != null){
            if(!player.getGuild().getName().equals(claim.getOwner()) && !CommandBypassclaim.bypass_list.contains(event.getPlayer())){
                event.setCancelled(true);
                event.getPlayer().sendMessage(Ftion.msgf("Claim", "§cThis terrain is claimed by another guild"));
            }
        }
    }

    @EventHandler
    public void onPlaceChunk(BlockPlaceEvent event){
        Chunk chunk = event.getBlock().getChunk();
        Claim claim = Ftion.loadClaim(chunk, event.getBlock().getWorld());
        ArkPlayer player = Ftion.getArkPlayer(event.getPlayer());
        if(claim != null && player != null){
            if(!player.getGuild().getName().equals(claim.getOwner())){
                event.setCancelled(true);
                event.getPlayer().sendMessage(Ftion.msgf("Claim", "§cThis terrain is claimed by another guild"));
            }
        }
    }

    @EventHandler
    public void onInteractChunk(PlayerInteractEvent event){
        if(event.getClickedBlock() != null){
            Chunk chunk = event.getClickedBlock().getChunk();
            Claim claim = Ftion.loadClaim(chunk, event.getClickedBlock().getWorld());
            ArkPlayer player = Ftion.getArkPlayer(event.getPlayer());
            if(claim != null && player != null){
                if(!player.getGuild().getName().equals(claim.getOwner())){
                    event.setCancelled(true);
                    event.getPlayer().sendMessage(Ftion.msgf("Claim", "§cThis terrain is claimed by another guild"));
                }
            }
        }
    }
}
