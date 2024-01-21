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
                    Claim claim = Ftion.loadClaim(player.getLocation().getChunk());
                    if (claim != null) {
                        Ftion.removeClaim(player.getLocation().getChunk());
                    }
                }
            }
        }
        return false;
    }
}