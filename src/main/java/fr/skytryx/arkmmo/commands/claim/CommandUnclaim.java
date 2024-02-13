package fr.skytryx.arkmmo.commands.claim;

import fr.skytryx.arkmmo.api.Ftion;
import fr.skytryx.arkmmo.api.classes.ArkPlayer;
import fr.skytryx.arkmmo.api.classes.Claim;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class CommandUnclaim implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        if(commandSender instanceof Player player){
            Claim claim = Ftion.loadClaim(player.getLocation().getChunk(), player.getWorld());
            ArkPlayer arkPlayer = Ftion.getArkPlayer(player);
            if(arkPlayer != null && claim != null && claim.getOwner().equals(arkPlayer.getGuild().getName())){
                Ftion.removeClaim(player.getLocation().getChunk(), player.getWorld());
                player.sendMessage(Ftion.msgf("Claim", "You unclaimed this chunk! §6" + player.getLocation().getChunk().getX() + " " + player.getLocation().getChunk().getZ()));

            } else {
                player.sendMessage(Ftion.msgf("Claim", "§cYou can't unclaim this chunk"));
            }
        }
        return false;
    }
}
