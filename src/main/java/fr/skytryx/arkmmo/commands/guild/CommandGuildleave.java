package fr.skytryx.arkmmo.commands.guild;

import fr.skytryx.arkmmo.api.Ftion;
import fr.skytryx.arkmmo.api.classes.ArkPlayer;
import fr.skytryx.arkmmo.api.classes.Guild;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import static fr.skytryx.arkmmo.api.Ftion.broadcastGuild;

public class CommandGuildleave implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        if(commandSender instanceof Player player){
            ArkPlayer arkPlayer = Ftion.getArkPlayer(player);
            if(!arkPlayer.getGuild().getName().equals("None")){
                Guild guild = arkPlayer.getGuild();
                broadcastGuild(guild, Ftion.msgf("Guild", "§c"+player.getName()+" left the guild"));
                guild.removeMembers(arkPlayer);
                arkPlayer.setGuild(new Guild());
                arkPlayer.save();
                guild.save();
            } else player.sendMessage(Ftion.msgf("Guild", "§cYou are not in a guild"));
        }
        return false;
    }
}
