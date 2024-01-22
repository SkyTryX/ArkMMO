package fr.skytryx.arkmmo.commands.guild;

import fr.skytryx.arkmmo.api.Database;
import fr.skytryx.arkmmo.api.Ftion;
import fr.skytryx.arkmmo.api.classes.ArkPlayer;
import fr.skytryx.arkmmo.api.classes.Guild;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import static fr.skytryx.arkmmo.api.Ftion.getArkPlayer;

public class CommandGuildcreate implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        if(commandSender instanceof Player player){
            if(strings.length == 1){
                ArkPlayer arkPlayer = getArkPlayer(player);
                if(arkPlayer.getGuild().getName().equals("None")){
                    if(!(new Database("guild").containsData(strings[0]))){
                        Guild guild = new Guild(strings[0], arkPlayer);
                        guild.save();
                        arkPlayer.setGuild(guild);
                        arkPlayer.save();
                        player.sendMessage(Ftion.msgf("Guild", "Successfully created guild §6"+strings[0]));
                    } else player.sendMessage(Ftion.msgf("Guild", "§cThis guild already exists"));
                } else player.sendMessage(Ftion.msgf("Guild", "§cYou are already in a guild"));
            } else player.sendMessage(Ftion.msgf("Guild", "§cProvide a name to your guild!"));
        }
        return false;
    }
}
