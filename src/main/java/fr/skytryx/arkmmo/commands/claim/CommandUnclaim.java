package fr.skytryx.arkmmo.commands.claim;

import fr.skytryx.arkmmo.api.Database;
import fr.skytryx.arkmmo.api.Ftion;
import fr.skytryx.arkmmo.api.classes.ArkPlayer;
import fr.skytryx.arkmmo.api.classes.Claim;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class CommandUnclaim implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        Database db = new Database("claim");
        if(commandSender instanceof Player player){
            db.getDatas().getValues(false).forEach((path, obj) ->{
                if(((Claim)db.getData(path+".claim")).getChunk().equals(player.getLocation().getChunk())){
                    if(((ArkPlayer)db.getData(path+"owner")).getPlayer().equals(player)){
                        db.removeData(path);
                        db.save();
                        player.sendMessage(Ftion.msgf("claim", "You unclaimed this chunk! §6"+player.getLocation().getChunk().getX() + " " + player.getLocation().getChunk().getZ()));
                    } else{
                        player.sendMessage(Ftion.msgf("claim", "This chunk isn't §6ours!"));
                    }
                }
            });
        }
        return false;
    }
}
