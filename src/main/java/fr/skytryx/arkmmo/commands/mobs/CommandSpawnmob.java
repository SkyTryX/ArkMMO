package fr.skytryx.arkmmo.commands.mobs;

import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.Zombie;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;


public class CommandSpawnmob implements CommandExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if(sender instanceof Player player){
            Zombie zombieEntity = (Zombie) player.getWorld().spawnEntity(player.getLocation(), EntityType.ZOMBIE);
            zombieEntity.setHealth(14);
            Objects.requireNonNull(zombieEntity.getEquipment()).setItem(EquipmentSlot.HAND, new ItemStack(Material.STONE_SWORD));
            zombieEntity.setCustomName("§4Juan §7- §c"+zombieEntity.getHealth()+"§fHP");
            zombieEntity.addPotionEffect(new PotionEffect(PotionEffectType.FIRE_RESISTANCE, 999999, 1, false, false));
            zombieEntity.setCustomNameVisible(true);
        }
        return true;
    }
}
