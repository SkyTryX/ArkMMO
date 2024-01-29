package fr.skytryx.arkmmo.commands.guild;

import fr.skytryx.arkmmo.api.Ftion;
import fr.skytryx.arkmmo.api.classes.ArkPlayer;
import fr.skytryx.arkmmo.api.classes.Guild;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import static fr.skytryx.arkmmo.api.Ftion.broadcastGuild;

public class CommandGuilddemote implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        if(commandSender instanceof Player player && strings.length == 1){
            ArkPlayer demoted_player = Ftion.getArkPlayer((Player) Bukkit.getOfflinePlayer(strings[0]));
            if(demoted_player != null){
                Guild guild = demoted_player.getGuild();
                if(guild.getOwner().equals(String.valueOf(player.getUniqueId())) && guild.getModerators().contains(String.valueOf(demoted_player.getPlayer().getUniqueId()))){
                    broadcastGuild(guild, Ftion.msgf("Guild", "§c"+demoted_player.getPlayer().getName()+" has been demoted from his moderator position in the guild by "+player.getName()));
                    guild.removeModerator(demoted_player);
                    guild.save();
                } else player.sendMessage(Ftion.msgf("Guild", "§cYou don't have the permission to do that!"));
            }
        }
        return false;
    }
}
