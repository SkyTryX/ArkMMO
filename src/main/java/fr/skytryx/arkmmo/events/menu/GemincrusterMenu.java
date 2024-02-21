package fr.skytryx.arkmmo.events.menu;

import fr.skytryx.arkmmo.utils.Ftion;
import fr.skytryx.arkmmo.utils.classes.ArkItem;
import fr.skytryx.arkmmo.utils.enums.Gemstone;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.ItemStack;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class GemincrusterMenu implements Listener {

    public static List<Material> Armors = Arrays.asList(
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
                    ArkItem arkItem = Ftion.getArkfromItem(event.getCurrentItem());
                    if(Armors.contains(event.getCurrentItem().getType()) && arkItem != null && arkItem.getGemstone() == null){
                        ItemStack item = event.getWhoClicked().getOpenInventory().getItem(20);
                        event.getWhoClicked().getOpenInventory().setItem(20, event.getCurrentItem());
                        event.getClickedInventory().setItem(event.getSlot(), item);
                        ((Player) event.getWhoClicked()).playSound(event.getWhoClicked().getLocation(), Sound.BLOCK_DISPENSER_DISPENSE, 1f, 1f);
                    } else if (event.getCurrentItem().getItemMeta().getDisplayName().contains("Gemstone")){
                        ItemStack item = event.getWhoClicked().getOpenInventory().getItem(24);
                        event.getWhoClicked().getOpenInventory().setItem(24, event.getCurrentItem());
                        event.getClickedInventory().setItem(event.getSlot(), item);
                        ((Player) event.getWhoClicked()).playSound(event.getWhoClicked().getLocation(), Sound.BLOCK_DISPENSER_DISPENSE, 1f, 1f);
                    }
                    ItemStack item = event.getWhoClicked().getOpenInventory().getItem(20);
                    ItemStack gemstone = event.getWhoClicked().getOpenInventory().getItem(24);
                    if(item != null && item.getItemMeta() != null && item.getItemMeta().getLore() != null && gemstone != null && gemstone.getItemMeta() != null){
                        ArkItem arkitem = Ftion.getArkfromItem(item);
                        if(arkitem != null){
                            if(Objects.requireNonNull(gemstone.getItemMeta()).getDisplayName().equals("§dRed Gemstone")) {
                                arkitem.setGemstone(Gemstone.RED);
                            }
                            else if(Objects.requireNonNull(gemstone.getItemMeta()).getDisplayName().equals("§dBlue Gemstone")) {
                                arkitem.setGemstone(Gemstone.BLUE);
                            }
                            else if(Objects.requireNonNull(gemstone.getItemMeta()).getDisplayName().equals("§dYellow Gemstone")) {
                                arkitem.setGemstone(Gemstone.YELLOW);
                            }
                            else if(Objects.requireNonNull(gemstone.getItemMeta()).getDisplayName().equals("§dWhite Gemstone")) {
                                arkitem.setGemstone(Gemstone.WHITE);
                            }
                            event.getWhoClicked().getOpenInventory().setItem(13, arkitem.getAsItem());
                        }
                    }
                } else if(event.getClickedInventory().equals(event.getWhoClicked().getOpenInventory().getTopInventory())) {
                    if(event.getSlot() == 20 || event.getSlot() == 24){
                        event.getWhoClicked().getInventory().addItem(event.getClickedInventory().getItem(event.getSlot()));
                        event.getClickedInventory().setItem(event.getSlot(), new ItemStack(Material.AIR));
                        event.getClickedInventory().setItem(13, new ItemStack(Material.AIR));
                    } else if (event.getSlot() == 13) {
                        if(event.getCurrentItem() != null && ((Player)event.getWhoClicked()).getLevel() >= 10){
                            ((Player) event.getWhoClicked()).setLevel(((Player) event.getWhoClicked()).getLevel() - 10);
                            ((Player) event.getWhoClicked()).playSound(event.getWhoClicked().getLocation(), Sound.BLOCK_ANVIL_USE, 1f, 1f);
                            event.getClickedInventory().setItem(20, new ItemStack(Material.AIR));
                            event.getClickedInventory().setItem(24, new ItemStack(Material.AIR));
                            event.getWhoClicked().getInventory().addItem(event.getClickedInventory().getItem(event.getSlot()));
                            event.getClickedInventory().setItem(event.getSlot(), new ItemStack(Material.AIR));
                        } else{
                            ((Player) event.getWhoClicked()).playSound(event.getWhoClicked().getLocation(), Sound.BLOCK_ANVIL_BREAK, 1f, 1f);
                            event.getWhoClicked().sendMessage(Ftion.msgf("Gemstone", "§cYou need 10 levels of XP in order to unincrust"));
                        }
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