package fr.skytryx.arkmmo.events.menu;

import fr.skytryx.arkmmo.ArkMMO;
import fr.skytryx.arkmmo.api.Ftion;
import fr.skytryx.arkmmo.api.classes.ArkItem;
import fr.skytryx.arkmmo.api.classes.ArkPlayer;
import fr.skytryx.arkmmo.api.enums.Gemstone;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class GemunincrusterMenu implements Listener {

    @EventHandler
    public void menuOpen(InventoryClickEvent event){
        if(event.getWhoClicked().getOpenInventory().getTitle().equals("§8GemIncruster") && event.getCurrentItem() != null &&
                event.getCurrentItem().getItemMeta() != null && event.getCurrentItem().getItemMeta().getDisplayName().equals("§cUnincrust Gemstone")){
            Inventory inv = Bukkit.createInventory(null, 45, "§8GemUnIncruster");
            Ftion.fillInv(inv);
            inv.setItem(11, Ftion.itemCreator(Material.GRAY_STAINED_GLASS_PANE, " "));
            inv.setItem(12, Ftion.itemCreator(Material.GRAY_STAINED_GLASS_PANE, " "));
            inv.setItem(13, new ItemStack(Material.AIR));
            inv.setItem(22, Ftion.itemCreator(Material.ARMOR_STAND, "§n§7Add Gemston-ed Armor Here"));
            inv.setItem(14, Ftion.itemCreator(Material.GRAY_STAINED_GLASS_PANE, " "));
            inv.setItem(15, Ftion.itemCreator(Material.GRAY_STAINED_GLASS_PANE, " "));
            inv.setItem(20, new ItemStack(Material.AIR));
            inv.setItem(24, new ItemStack(Material.AIR));
            event.getWhoClicked().openInventory(inv);
        }
    }

    @EventHandler
    public void onplayerinvclick(InventoryClickEvent event){
        if(event.getWhoClicked().getOpenInventory().getTitle().equals("§8GemUnIncruster")) {
            event.setCancelled(true);
            if (event.getClickedInventory() != null && event.getCurrentItem() != null && event.getCurrentItem().getItemMeta() != null) {
                if (event.getClickedInventory().getType().equals(InventoryType.PLAYER)) {
                    ArkItem arkItem = Ftion.getArkfromItem(event.getCurrentItem());
                    if (GemincrusterMenu.Armors.contains(event.getCurrentItem().getType()) && event.getCurrentItem().getItemMeta().getLore() != null &&
                            arkItem != null && arkItem.getGemstone() != null) {
                        ItemStack item = event.getWhoClicked().getOpenInventory().getItem(13);
                        event.getWhoClicked().getOpenInventory().setItem(13, event.getCurrentItem());
                        ((Player) event.getWhoClicked()).playSound(event.getWhoClicked().getLocation(), Sound.BLOCK_DISPENSER_DISPENSE, 1f, 1f);
                        event.getClickedInventory().setItem(event.getSlot(), item);
                    }
                    ItemStack item = event.getWhoClicked().getOpenInventory().getItem(13);
                    if (item != null && item.getItemMeta() != null && item.getItemMeta().getLore() != null) {
                        ArkItem arkitem = Ftion.getArkfromItem(item);
                        if (arkitem != null) {
                            if (arkitem.getGemstone() != null) {
                                if(arkitem.getGemstone().equals(Gemstone.RED)){
                                    event.getWhoClicked().getOpenInventory().setItem(24, ArkMMO.items.get("red_gemstone").getAsItem());
                                }
                                else if(arkitem.getGemstone().equals(Gemstone.BLUE)){
                                    event.getWhoClicked().getOpenInventory().setItem(24, ArkMMO.items.get("blue_gemstone").getAsItem());
                                }
                                else if(arkitem.getGemstone().equals(Gemstone.YELLOW)){
                                    event.getWhoClicked().getOpenInventory().setItem(24, ArkMMO.items.get("yellow_gemstone").getAsItem());
                                }
                                else if(arkitem.getGemstone().equals(Gemstone.WHITE)){
                                    event.getWhoClicked().getOpenInventory().setItem(24, ArkMMO.items.get("white_gemstone").getAsItem());
                                }
                                arkitem.setGemstone(null);
                                event.getWhoClicked().getOpenInventory().setItem(20, arkitem.getAsItem());
                            }
                        }
                    }
                } else if (event.getClickedInventory().equals(event.getWhoClicked().getOpenInventory().getTopInventory())) {
                    if (event.getSlot() == 20 || event.getSlot() == 24) {
                        ArkPlayer arkPlayer = Ftion.getArkPlayer(((Player) event.getWhoClicked()));
                        if(arkPlayer != null && arkPlayer.getGold() >= 5000 && arkPlayer.getPlayer().getLevel() >= 30) {
                            ((Player) event.getWhoClicked()).setLevel(((Player) event.getWhoClicked()).getLevel() - 30);
                            arkPlayer.setGold(arkPlayer.getGold() - 5000);
                            arkPlayer.save();
                            ((Player) event.getWhoClicked()).playSound(event.getWhoClicked().getLocation(), Sound.BLOCK_ANVIL_USE, 1f, 1f);
                            event.getWhoClicked().getInventory().addItem(event.getClickedInventory().getItem(20));
                            event.getClickedInventory().setItem(20, new ItemStack(Material.AIR));
                            event.getWhoClicked().getInventory().addItem(event.getClickedInventory().getItem(24));
                            event.getClickedInventory().setItem(24, new ItemStack(Material.AIR));
                            event.getClickedInventory().setItem(13, new ItemStack(Material.AIR));
                        } else {
                        ((Player) event.getWhoClicked()).playSound(event.getWhoClicked().getLocation(), Sound.BLOCK_ANVIL_BREAK, 1f, 1f);
                        event.getWhoClicked().sendMessage(Ftion.msgf("Gemstone", "§cYou need 30 levels of XP & 5000 golds in order to unincrust"));
                    }
                    } else if (event.getSlot() == 13 && event.getCurrentItem() != null) {
                        event.getWhoClicked().getInventory().addItem(event.getClickedInventory().getItem(event.getSlot()));
                        event.getClickedInventory().setItem(event.getSlot(), new ItemStack(Material.AIR));
                        event.getClickedInventory().setItem(20, new ItemStack(Material.AIR));
                        event.getClickedInventory().setItem(24, new ItemStack(Material.AIR));
                    }
                }
            }
        }
    }


    @EventHandler
    public void closingInventory(InventoryCloseEvent event){
        if(event.getView().getTitle().equals("§8GemUnIncruster")){
            if(event.getInventory().getItem(13) != null){
                event.getPlayer().getInventory().addItem(event.getInventory().getItem(13));
            }
        }
    }


    @EventHandler
    public void logoutwithopeninv(PlayerQuitEvent event){
        if(event.getPlayer().getOpenInventory().getTitle().equals("§8GemUnIncruster")){
            if(event.getPlayer().getOpenInventory().getItem(13) != null){
                event.getPlayer().getInventory().addItem(event.getPlayer().getOpenInventory().getItem(13));
            }
        }
    }
}
