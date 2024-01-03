package fr.skytryx.arkmmo.commands.guild;

import fr.skytryx.arkmmo.api.Database;
import fr.skytryx.arkmmo.api.Ftion;
import fr.skytryx.arkmmo.api.classes.ArkPlayer;
import fr.skytryx.arkmmo.api.classes.Guild;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.UUID;

import static fr.skytryx.arkmmo.api.Ftion.getArkPlayer;

public class CommandGuildcreate implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        if(strings.length == 1 && commandSender instanceof Player player){
            ArkPlayer arkPlayer = getArkPlayer(player);
            if(arkPlayer.getGuild() == null){
                Guild guild = new Guild(strings[0], arkPlayer);
                Database db = new Database("guild");
                db.addData(String.valueOf(UUID.randomUUID()), guild);
                db.save();
                Database db_player = new Database("arkplayer");
                db_player.addData(player.getUniqueId().toString(), arkPlayer);
                db_player.save();
                arkPlayer.setGuild(guild);
                player.sendMessage(Ftion.msgf("guild", "Successfully created guild §6"+strings[0]));
            }
        }
        return false;
    }
}
