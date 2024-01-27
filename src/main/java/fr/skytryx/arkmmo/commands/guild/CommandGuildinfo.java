package fr.skytryx.arkmmo.commands.guild;

import fr.skytryx.arkmmo.api.Ftion;
import fr.skytryx.arkmmo.api.classes.Guild;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.UUID;

public class CommandGuildinfo implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        if (commandSender instanceof Player player) {
            Guild guild = Ftion.getArkPlayer(player).getGuild();
            if (guild.getName().equals("None") && strings.length >= 1) {
                guild = Ftion.getGuildFromName(strings[0]);
                if (!guild.getName().equals("None")) {
                    StringBuilder list_member = new StringBuilder();
                    for (String member : guild.getMembers()) {
                        list_member.append(" ").append(Bukkit.getOfflinePlayer(UUID.fromString(member)).getName()).append(",");
                    }
                    player.sendMessage("§6§lINFO GUILD " + guild.getName() + "\n" +
                            "§3Owner: §b" + Bukkit.getOfflinePlayer(UUID.fromString(guild.getOwner())).getName() + "\n" +
                            "§3Level: §b" + guild.getLevel() + "\n" +
                            "§3XP: §b" + guild.getXP() + "\n" +
                            "§3Size: §b15\n" +
                            "§3Members:§b" + list_member);
                }
            } else if (!guild.getName().equals("None")) {
                StringBuilder list_member = new StringBuilder();
                for (String member : guild.getMembers()) {
                    list_member.append(" ").append(Bukkit.getOfflinePlayer(UUID.fromString(member)).getName()).append(",");
                }
                player.sendMessage("§6§lINFO GUILD " + guild.getName() + "\n" +
                        "§3Owner: §b" + Bukkit.getOfflinePlayer(UUID.fromString(guild.getOwner())).getName() + "\n" +
                        "§3Level: §b" + guild.getLevel() + "\n" +
                        "§3XP: §b" + guild.getXP() + "\n" +
                        "§3Size: §b15\n" +
                        "§3Members:§b" + list_member);
            }
        }
        return false;
    }
}
