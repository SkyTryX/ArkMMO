package fr.skytryx.arkmmo.commands.guild;

import fr.skytryx.arkmmo.utils.Ftion;
import fr.skytryx.arkmmo.utils.classes.Guild;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Objects;

public class CommandGuildinvite implements CommandExecutor {

    public static HashMap<Player, String> invites_list = new HashMap<>();
    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        if(commandSender instanceof Player player && strings.length == 1){
            Guild guild = Objects.requireNonNull(Ftion.getArkPlayer(player)).getGuild();
            if(guild.getOwner().equals(player.getUniqueId().toString()) || guild.getModerators().contains(player.getUniqueId().toString())){
                Player target = Bukkit.getPlayer(strings[0]);
                if(target != null){
                    invites_list.put(target, guild.getName());
                    player.sendMessage(Ftion.msgf("Guild", "You have invited "+strings[0]+" to join the guild"));

                    target.sendMessage(Ftion.msgf("Guild", "You have been invited to join the guild §6"+guild.getName()));
                    TextComponent message = new TextComponent("§a[Accept]");
                    message.setClickEvent(new ClickEvent(ClickEvent.Action.SUGGEST_COMMAND, "/guildjoin "+guild.getName()));
                    message.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder("Click to accept!").create()));
                    target.spigot().sendMessage(message);
                }
            }
        }
        return false;
    }
}
