package fr.skytryx.arkmmo.commands.item;

import fr.skytryx.arkmmo.api.Ftion;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

public class CommandGemincruster implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        if(commandSender instanceof Player player){
            Inventory inv = Bukkit.createInventory(null, 45, "§8GemIncruster");
            Ftion.fillInv(inv);
            inv.setItem(11, new ItemStack(Ftion.itemCreator(Material.GRAY_STAINED_GLASS_PANE, " ")));
            inv.setItem(12, new ItemStack(Ftion.itemCreator(Material.GRAY_STAINED_GLASS_PANE, " ")));
            inv.setItem(13, new ItemStack(Material.AIR));
            inv.setItem(14, new ItemStack(Ftion.itemCreator(Material.GRAY_STAINED_GLASS_PANE, " ")));
            inv.setItem(15, new ItemStack(Ftion.itemCreator(Material.GRAY_STAINED_GLASS_PANE, " ")));
            inv.setItem(29, new ItemStack(Ftion.itemCreator(Material.ARMOR_STAND, "§n§7Add Armor Here")));
            inv.setItem(20, new ItemStack(Material.AIR));
            inv.setItem(24, new ItemStack(Material.AIR));
            inv.setItem(33, new ItemStack(Ftion.itemCreator(Material.BOOK, "§n§7Add Gemstone Here")));
            player.openInventory(inv);
        }
        return false;
    }
}
