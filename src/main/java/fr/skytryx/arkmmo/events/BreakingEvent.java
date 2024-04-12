package fr.skytryx.arkmmo.events;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Zombie;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.EntityCombustEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.inventory.ItemStack;

import java.util.*;

public class BreakingEvent implements Listener {

    Map<Material, List<Object>> block_list = Map.of(Material.STRIPPED_OAK_WOOD, List.of(60, Material.OAK_WOOD, 1),
            Material.STRIPPED_BIRCH_WOOD, List.of(120, Material.BIRCH_WOOD, 2),
            Material.CHISELED_STONE_BRICKS, List.of(30, Material.COBBLESTONE, 1),
            Material.COAL_ORE, List.of(60, Material.COAL, 2),
            Material.COPPER_ORE, List.of(120, Material.COPPER_INGOT, 2),
            Material.IRON_ORE, List.of(150, Material.IRON_INGOT, 3),
            Material.GOLD_ORE, List.of(210, Material.GOLD_INGOT, 4),
            Material.DIAMOND_ORE, List.of(300, Material.DIAMOND, 5));

    public Integer amountfromtier(Integer tier) {
        if (tier == 1) {
            return new Random().nextInt(1, 5);
        } else if (tier == 2) {
            return new Random().nextInt(1, 4);
        } else if (tier == 3) {
            return new Random().nextInt(1, 3);
        } else if (tier == 4) {
            return new Random().nextInt(1, 2);
        } else return 1;
    }

    @EventHandler
    public void onBreak(BlockBreakEvent event) {
        if (block_list.containsKey(event.getBlock().getType())) {
            Material oldblock = event.getBlock().getType();
            event.getPlayer().getInventory().addItem(new ItemStack((Material) block_list.get(event.getBlock().getType()).get(1), amountfromtier((int) block_list.get(event.getBlock().getType()).get(2))));
            event.setCancelled(true);
            event.getBlock().setType(Material.BEDROCK);
            Bukkit.getScheduler().scheduleSyncDelayedTask(Objects.requireNonNull(Bukkit.getPluginManager().getPlugin("ArkMMO")), () -> event.getBlock().setType(oldblock), 20L * ((int) block_list.get(oldblock).get(0)));
        }
    }

    @EventHandler
    public void onHitJuan(EntityDamageByEntityEvent event) {
        if (Objects.requireNonNull(event.getEntity().getCustomName()).contains("Juan") && event.getEntityType().equals(EntityType.ZOMBIE)) {
            event.getEntity().setCustomName("§4Juan §7- §c" + (Math.round(((Zombie) event.getEntity()).getHealth() - event.getDamage() + 1) + "§fHP"));
        }
    }

    @EventHandler
    public void onEntityCombust(EntityCombustEvent event) {
        if (event.getEntity() instanceof Zombie) {
            event.setCancelled(true);
        }
    }
}