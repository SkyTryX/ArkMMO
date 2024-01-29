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

public class CommandGuildkick implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        if(commandSender instanceof Player player && strings.length == 1){
            ArkPlayer kicked_player = Ftion.getArkPlayer((Player)Bukkit.getOfflinePlayer(strings[0]));
            if(kicked_player != null){
                Guild guild = kicked_player.getGuild();
                if(guild.getOwner().equals(String.valueOf(player.getUniqueId())) || guild.getModerators().contains(player.getUniqueId().toString())){
                    broadcastGuild(guild, Ftion.msgf("Guild", "§c"+kicked_player.getPlayer().getName()+" has been kicked from the guild by "+player.getName()));
                    guild.removeMembers(kicked_player);
                    kicked_player.setGuild(new Guild());
                    kicked_player.save();
                    guild.save();
                } else player.sendMessage(Ftion.msgf("Guild", "§cYou are not in a guild"));
            }
        }
        return false;
    }
}
