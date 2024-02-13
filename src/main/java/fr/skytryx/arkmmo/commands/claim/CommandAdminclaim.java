package fr.skytryx.arkmmo.commands.claim;

import fr.skytryx.arkmmo.api.Ftion;
import fr.skytryx.arkmmo.api.classes.Claim;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class CommandAdminclaim implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        if (commandSender instanceof Player player) {
            if (strings.length == 1) {
                if (strings[0].equals("remove")) {
                    Claim claim = Ftion.loadClaim(player.getLocation().getChunk(), player.getWorld());
                    if (claim != null) {
                        Ftion.removeClaim(player.getLocation().getChunk(), player.getWorld());
                        player.sendMessage(Ftion.msgf("Claim", "You unclaimed this chunk! §6" + player.getLocation().getChunk().getX() + " " + player.getLocation().getChunk().getZ()));

                    }
                }
            }
        }
        return false;
    }
}