package fr.skytryx.arkmmo.commands.gold;

import fr.skytryx.arkmmo.utils.Ftion;
import fr.skytryx.arkmmo.utils.classes.ArkPlayer;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

import static fr.skytryx.arkmmo.utils.Ftion.getArkPlayerByUUID;

public class CommandSetgold implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if(args.length == 2 && Ftion.isNumeric(args[1])){
            ArkPlayer arkPlayer = getArkPlayerByUUID(Bukkit.getOfflinePlayer(args[0]).getUniqueId().toString());
            arkPlayer.setGold(Integer.parseInt(args[1]));
            arkPlayer.save();
            sender.sendMessage(Ftion.msgf("Gold", "Set gold from §6"+arkPlayer.getName()+" §bto §6"+arkPlayer.getGold()));
        }
        return false;
    }
}
