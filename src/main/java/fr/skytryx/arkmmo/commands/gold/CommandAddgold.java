package fr.skytryx.arkmmo.commands.gold;

import fr.skytryx.arkmmo.utils.Ftion;
import fr.skytryx.arkmmo.utils.classes.ArkPlayer;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

import static fr.skytryx.arkmmo.utils.Ftion.getArkPlayerByUUID;

public class CommandAddgold implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (args.length == 2 && Ftion.isNumeric(args[1])) {
            ArkPlayer arkPlayer = getArkPlayerByUUID(Bukkit.getOfflinePlayer(args[0]).getUniqueId().toString());
            arkPlayer.setGold(arkPlayer.getGold() + Integer.parseInt(args[1]));
            arkPlayer.save();
            sender.sendMessage(Ftion.msgf("Gold", "Added §6"+args[1]+"§b gold to §6" + arkPlayer.getName() + "§b. They now have §6" + arkPlayer.getGold()+" §b gold"));
        }
        return false;
    }
}
