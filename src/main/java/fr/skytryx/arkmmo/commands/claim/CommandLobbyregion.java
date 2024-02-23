package fr.skytryx.arkmmo.commands.claim;

import fr.skytryx.arkmmo.utils.Database;
import fr.skytryx.arkmmo.utils.Ftion;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class CommandLobbyregion implements CommandExecutor {

    public static Location p1;
    public static Location p2;
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if(args.length > 0 && sender instanceof Player player){
            if(args[0].equals("pos1")){
                p1 = player.getLocation();
                player.sendMessage(Ftion.msgf("Region", "Added §6pos1 §bfor your region"));
            } else if(args[0].equals("pos2")){
                p2 = player.getLocation();
                player.sendMessage(Ftion.msgf("Region", "Added §6pos2 §bfor your region"));
            } if(args[0].equals("set") && args.length == 2 && p1 != null & p2 != null){
                Database db = new Database("lobbyregion");
                db.addData(args[1]+".world", p1.getWorld());
                db.addData(args[1]+".x1", p1.getX());
                db.addData(args[1]+".y1", p1.getY());
                db.addData(args[1]+".z1", p1.getZ());
                db.addData(args[1]+".x2", p2.getX());
                db.addData(args[1]+".y2", p2.getY());
                db.addData(args[1]+".z2", p2.getZ());
                db.save();
                player.sendMessage(Ftion.msgf("Region", "Saved region §6"+args[1]));
            }
        }
        return false;
    }
}
