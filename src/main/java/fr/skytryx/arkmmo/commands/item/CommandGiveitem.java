package fr.skytryx.arkmmo.commands.item;

import fr.skytryx.arkmmo.ArkMMO;
import fr.skytryx.arkmmo.utils.Ftion;
import fr.skytryx.arkmmo.utils.classes.ArkItem;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class CommandGiveitem implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        if(commandSender instanceof Player player){
            ArkItem arkItem = ArkMMO.items.get(strings[0]);
            if(arkItem != null){
                player.getInventory().addItem(arkItem.getAsItem());
                player.sendMessage(Ftion.msgf("Item", "Gave you item §6"+strings[0]));
            }
        }
        return false;
    }
}
