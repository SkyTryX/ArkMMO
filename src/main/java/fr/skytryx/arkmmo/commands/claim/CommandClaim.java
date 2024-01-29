package fr.skytryx.arkmmo.commands.claim;

import fr.skytryx.arkmmo.api.Ftion;
import fr.skytryx.arkmmo.api.classes.ArkPlayer;
import fr.skytryx.arkmmo.api.classes.Claim;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;
import java.util.UUID;


public class CommandClaim implements CommandExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, String[] strings) {
        if(commandSender instanceof Player player) {
            ArkPlayer arkPlayer = Ftion.getArkPlayer(player);
            if (arkPlayer != null && !arkPlayer.getGuild().getName().equals("None")) {
                new Claim(player.getLocation().getChunk(), Objects.requireNonNull(Ftion.getArkPlayer(player)).getGuild().getName(), UUID.randomUUID()).save();
                player.sendMessage(Ftion.msgf("claim", "You claimed this chunk! §6" + player.getLocation().getChunk().getX() + " " + player.getLocation().getChunk().getZ()));
            }
        }
        return false;
    }
}
