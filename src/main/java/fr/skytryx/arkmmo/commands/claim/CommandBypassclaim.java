package fr.skytryx.arkmmo.commands.claim;

import fr.skytryx.arkmmo.utils.Ftion;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.LinkedList;
import java.util.List;

public class CommandBypassclaim implements CommandExecutor {

    public static List<Player> bypass_list = new LinkedList<>();
    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        if(commandSender instanceof Player player){
            if(bypass_list.contains(player)){
                player.sendMessage(Ftion.msgf("Claim", "You can no longer §6interact §bin every claim"));
                bypass_list.remove(player);
            } else {
                player.sendMessage(Ftion.msgf("Claim", "You can now §6interact §bin every claim"));
                bypass_list.add(player);
            }
        }
        return false;
    }
}
