package fr.skytryx.arkmmo.commands.guild;

import fr.skytryx.arkmmo.utils.Ftion;
import fr.skytryx.arkmmo.utils.classes.ArkPlayer;
import fr.skytryx.arkmmo.utils.classes.Guild;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.LinkedList;
import java.util.List;

import static fr.skytryx.arkmmo.utils.Ftion.broadcastGuild;

public class CommandGuildpromote implements CommandExecutor {

    List<Player> confirmation = new LinkedList<>();
    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        if(commandSender instanceof Player player && strings.length == 1){
            ArkPlayer promoted = Ftion.getArkPlayer((Player) Bukkit.getOfflinePlayer(strings[0]));
            if(promoted != null){
                Guild guild = promoted.getGuild();
                if(guild.getOwner().equals(String.valueOf(player.getUniqueId())) && !guild.getModerators().contains(String.valueOf(promoted.getPlayer().getUniqueId()))) {
                    guild.addModerator(promoted);
                    broadcastGuild(guild, Ftion.msgf("Guild", promoted.getPlayer().getName() + " has been promoted to moderator in the guild by " + player.getName()));
                    guild.save();
                } else if(guild.getOwner().equals(String.valueOf(player.getUniqueId())) && guild.getModerators().contains(String.valueOf(promoted.getPlayer().getUniqueId()))){
                    if(confirmation.contains(player)){
                        guild.setOwner(promoted);
                        broadcastGuild(guild, Ftion.msgf("Guild",  promoted.getPlayer().getName() + " has been promoted to owner in the guild by " + player.getName()));
                        guild.save();
                    } else{
                        confirmation.add(player);
                        player.sendMessage(Ftion.msgf("Guild", "§cAre you SURE you want to do that? You will no longer be owner of this guild! Re-type the command to confirm"));
                    }
                } else player.sendMessage(Ftion.msgf("Guild", "§cYou don't have the permission to do that!"));
            }
        }
        return false;
    }
}
