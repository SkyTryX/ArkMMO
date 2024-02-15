package fr.skytryx.arkmmo.api.classes;

import fr.skytryx.arkmmo.api.enums.Gemstone;
import fr.skytryx.arkmmo.api.enums.Rarity;
import org.bukkit.Material;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class ArkItem {

    Material material;
    String[] lore;
    String name;
    Rarity rarity;
    Integer model_data;
    List<Gemstone> gemstones = new LinkedList<>();

    public ArkItem(Material m, String n, Rarity r, String[] l, Integer md){
        this.material = m;
        this.name = n;
        this.rarity = r;
        this.lore = l;
        this.model_data = md;
    }

    public ArkItem(Material m, String n, Rarity r, String[] l, List<Gemstone> g, Integer md){
        this.material = m;
        this.name = n;
        this.rarity = r;
        this.lore = l;
        this.gemstones = g;
        this.model_data = md;
    }



    public Material getMaterial() {
        return this.material;
    }

    public String getName() {
        return this.name;
    }

    public String[] getLore() {
        return this.lore;
    }

    public Rarity getRarity() {
        return rarity;
    }

    public ItemStack getAsItem() {
        ItemStack item = new ItemStack(this.getMaterial());
        ItemMeta itemMeta = item.getItemMeta();
        assert itemMeta != null;
        String color;
        if(this.getRarity().equals(Rarity.COMMON)) color = "§f";
        else if(this.getRarity().equals(Rarity.UNCOMMON)) color = "§a";
        else if(this.getRarity().equals(Rarity.RARE)) color = "§b";
        else if(this.getRarity().equals(Rarity.EPIC)) color = "§d";
        else if(this.getRarity().equals(Rarity.LEGENDARY)) color = "§6";
        else if(this.getRarity().equals(Rarity.MYTHIC)) color = "§e";
        else color = "§7";
        List<String> l = new LinkedList<>();
        l.add("");
        l.addAll(Arrays.stream(this.getLore()).toList());
        l.add("");
        l.add(color+"§l"+this.getRarity());
        itemMeta.setDisplayName(color+this.getName());
        itemMeta.setLore(l);
        itemMeta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        itemMeta.setCustomModelData(this.model_data);
        item.setItemMeta(itemMeta);
        return item;
    }
}
