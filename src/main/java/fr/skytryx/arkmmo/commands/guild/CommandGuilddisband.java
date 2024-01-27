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

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

public class CommandGuilddisband implements CommandExecutor {

    public static List<Player> confirm_disband = new LinkedList<>();
    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        if(commandSender instanceof Player player){
            ArkPlayer arkPlayer = Ftion.getArkPlayer(player);
            if(arkPlayer != null && !arkPlayer.getGuild().getName().equals("None") && arkPlayer.getGuild().getOwner().equals(String.valueOf(player.getUniqueId()))){
                if(confirm_disband.contains(player)){
                    Guild guild = arkPlayer.getGuild();
                    Ftion.broadcastGuild(guild, Ftion.msgf("Guild", "§cThe guild has been disbanded by "+player.getName()));
                    for (String member : guild.getMembers()) {
                        Objects.requireNonNull(Ftion.getArkPlayer((Player) Bukkit.getOfflinePlayer(UUID.fromString(member)))).setGuild(new Guild());
                    }
                    Database db = new Database("guild");
                    db.removeData(guild.getName());
                    db.save();
                } else {
                    confirm_disband.add(player);
                    player.sendMessage(Ftion.msgf("Guild", "§cAre you sure?? Re-type the command to confirm"));
                }
            }
        }
        return false;
    }
}
