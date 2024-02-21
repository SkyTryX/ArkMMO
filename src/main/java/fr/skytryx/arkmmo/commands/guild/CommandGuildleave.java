package fr.skytryx.arkmmo.commands.guild;

import fr.skytryx.arkmmo.utils.Ftion;
import fr.skytryx.arkmmo.utils.classes.ArkPlayer;
import fr.skytryx.arkmmo.utils.classes.Guild;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import static fr.skytryx.arkmmo.utils.Ftion.broadcastGuild;

public class CommandGuildleave implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        if(commandSender instanceof Player player){
            ArkPlayer arkPlayer = Ftion.getArkPlayer(player);
            if(arkPlayer != null && !arkPlayer.getGuild().getName().equals("None")){
                if(!arkPlayer.getGuild().getOwner().equals(String.valueOf(player.getUniqueId()))){
                    Guild guild = arkPlayer.getGuild();
                    broadcastGuild(guild, Ftion.msgf("Guild", "§c"+player.getName()+" left the guild"));
                    guild.removeMembers(arkPlayer);
                    arkPlayer.setGuild(new Guild());
                    arkPlayer.save();
                    guild.save();
                } else player.sendMessage(Ftion.msgf("Guild", "§cYou can't leave whilst being the owner of the guild"));
            } else player.sendMessage(Ftion.msgf("Guild", "§cYou are not in a guild"));
        }
        return false;
    }
}
