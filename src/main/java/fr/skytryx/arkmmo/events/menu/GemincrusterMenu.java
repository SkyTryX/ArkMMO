package fr.skytryx.arkmmo.events.menu;

import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.ItemStack;

import java.util.Arrays;
import java.util.List;

public class GemincrusterMenu implements Listener {

    public List<Material> Armors = Arrays.asList(
            Material.DIAMOND_BOOTS, Material.DIAMOND_LEGGINGS, Material.DIAMOND_CHESTPLATE, Material.DIAMOND_HELMET,
            Material.GOLDEN_BOOTS, Material.GOLDEN_LEGGINGS, Material.GOLDEN_CHESTPLATE, Material.GOLDEN_HELMET,
            Material.LEATHER_BOOTS, Material.LEATHER_LEGGINGS, Material.LEATHER_CHESTPLATE, Material.LEATHER_HELMET,
            Material.IRON_BOOTS, Material.IRON_LEGGINGS, Material.IRON_CHESTPLATE, Material.IRON_HELMET,
            Material.NETHERITE_BOOTS, Material.NETHERITE_LEGGINGS, Material.NETHERITE_CHESTPLATE, Material.NETHERITE_HELMET,
            Material.CHAINMAIL_BOOTS, Material.CHAINMAIL_LEGGINGS, Material.CHAINMAIL_CHESTPLATE, Material.CHAINMAIL_HELMET);


    @EventHandler
    public void onplayerinvclick(InventoryClickEvent event){
        if(event.getWhoClicked().getOpenInventory().getTitle().equals("§8GemIncruster")){
            event.setCancelled(true);
            if(event.getClickedInventory() != null && event.getCurrentItem() != null && event.getCurrentItem().getItemMeta() != null){
                if(event.getClickedInventory().getType().equals(InventoryType.PLAYER)){
                    if(Armors.contains(event.getCurrentItem().getType())){
                        event.getWhoClicked().getOpenInventory().setItem(20, event.getCurrentItem());
                        event.getClickedInventory().setItem(event.getSlot(), new ItemStack(Material.AIR));
                    } else if (event.getCurrentItem().getItemMeta().getDisplayName().contains("Gemstone")){
                        event.getWhoClicked().getOpenInventory().setItem(24, event.getCurrentItem());
                        event.getClickedInventory().setItem(event.getSlot(), new ItemStack(Material.AIR));
                    }
                } else if(event.getClickedInventory().equals(event.getWhoClicked().getOpenInventory().getTopInventory())) {
                    if(event.getSlot() == 20 || event.getSlot() == 24){
                        event.getWhoClicked().getInventory().addItem(event.getClickedInventory().getItem(event.getSlot()));
                        event.getClickedInventory().setItem(event.getSlot(), new ItemStack(Material.AIR));
                    }
                }
            }
        }
    }

    @EventHandler
    public void closingInventory(InventoryCloseEvent event){
        if(event.getView().getTitle().equals("§8GemIncruster")){
            if(event.getInventory().getItem(20) != null){
                event.getPlayer().getInventory().addItem(event.getInventory().getItem(20));
            }
            if(event.getInventory().getItem(24) != null){
                event.getPlayer().getInventory().addItem(event.getInventory().getItem(24));
            }
        }
    }


    @EventHandler
    public void logoutwithopeninv(PlayerQuitEvent event){
        if(event.getPlayer().getOpenInventory().getTitle().equals("§8GemIncruster")){
            if(event.getPlayer().getOpenInventory().getItem(20) != null){
                event.getPlayer().getInventory().addItem(event.getPlayer().getOpenInventory().getItem(20));
            }
            if(event.getPlayer().getOpenInventory().getItem(24) != null){
                event.getPlayer().getInventory().addItem(event.getPlayer().getOpenInventory().getItem(24));
            }
        }
    }
}