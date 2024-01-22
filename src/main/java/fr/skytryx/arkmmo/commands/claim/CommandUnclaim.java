package fr.skytryx.arkmmo.commands.claim;

import fr.skytryx.arkmmo.api.Ftion;
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
            Claim claim = Ftion.loadClaim(player.getLocation().getChunk());
            if(claim != null && claim.getOwner().equals(player.getName())){
                Ftion.removeClaim(player.getLocation().getChunk());
            }
        }
        return false;
    }
}