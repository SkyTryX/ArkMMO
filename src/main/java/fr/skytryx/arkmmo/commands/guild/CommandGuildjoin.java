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

public class CommandGuildjoin implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        if(commandSender instanceof Player player){
            if(CommandGuildinvite.invites_list.containsKey(player)){
                Guild guild = Ftion.getGuildFromName(CommandGuildinvite.invites_list.get(player));
                ArkPlayer arkPlayer = Ftion.getArkPlayer(player);
                if(arkPlayer != null && arkPlayer.getGuild().getName().equals("None")){
                    guild.addMembers(arkPlayer);
                    arkPlayer.setGuild(guild);
                    arkPlayer.save();
                    guild.save();
                    broadcastGuild(guild, Ftion.msgf("Guild", player.getName()+" joined the guild §6"+guild.getName()));
                    CommandGuildinvite.invites_list.remove(player);
                } else player.sendMessage(Ftion.msgf("Guild", "§cYou are already in a guild"));
            } else player.sendMessage(Ftion.msgf("Guild", "§cYou did not get invited anywhere!"));
        }
        return false;
    }
}
