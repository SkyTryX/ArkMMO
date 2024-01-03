package fr.skytryx.arkmmo.commands.claim;

import fr.skytryx.arkmmo.api.Database;
import fr.skytryx.arkmmo.api.Ftion;
import fr.skytryx.arkmmo.api.classes.Claim;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class CommandAdminclaim implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        if(strings.length == 1){
            Database db = new Database("claim");
            if(commandSender instanceof Player player){
                db.getDatas().getValues(false).forEach((path, obj) ->{
                    if(((Claim)db.getData(path+".claim")).getChunk().equals(player.getLocation().getChunk())){
                            db.removeData(path);
                            db.save();
                        player.sendMessage(Ftion.msgf("claim", "You admin-unclaimed this chunk! §6"+player.getLocation().getChunk().getX()+" "+player.getLocation().getChunk().getZ()));
                    }
                });
            }
        }
       return false;
    }
}
