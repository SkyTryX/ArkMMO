package fr.skytryx.arkmmo.commands.guild;

import fr.skytryx.arkmmo.utils.Ftion;
import fr.skytryx.arkmmo.utils.classes.Guild;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public class CommandGuildmsg implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        if(commandSender instanceof Player player && strings.length >= 1) {
            Guild guild = Objects.requireNonNull(Ftion.getArkPlayer(player)).getGuild();
            StringBuilder msg = new StringBuilder();
            for (String string : strings) {
                msg.append(string).append(" ");
            }
            Ftion.broadcastGuild(guild, "§bGuild "+player.getName()+"> §a"+msg);
        }
        return false;
    }
}
