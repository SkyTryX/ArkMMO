package fr.skytryx.arkmmo.commands.guild;

import fr.skytryx.arkmmo.api.Ftion;
import fr.skytryx.arkmmo.api.classes.Guild;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class CommandGuildmsg implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        if(commandSender instanceof Player player && strings.length >= 1) {
            Guild guild = Ftion.getArkPlayer(player).getGuild();
            StringBuilder msg = new StringBuilder();
            for (String string : strings) {
                msg.append(string);
            }
            Ftion.broadcastGuild(guild, "§bGuild "+player.getName()+"> §a"+msg);
        }
        return false;
    }
}
