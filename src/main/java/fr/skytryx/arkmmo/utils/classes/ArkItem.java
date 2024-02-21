package fr.skytryx.arkmmo.utils.classes;

import fr.skytryx.arkmmo.utils.enums.Gemstone;
import fr.skytryx.arkmmo.utils.enums.Rarity;
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
    Gemstone gemstone;

    public ArkItem(Material m, String n, Rarity r, String[] l){
        this.material = m;
        this.name = n;
        this.rarity = r;
        this.lore = l;
    }

    public ArkItem(Material m, String n, Rarity r, String[] l, Gemstone g){
        this.material = m;
        this.name = n;
        this.rarity = r;
        this.lore = l;
        this.gemstone = g;
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

    public Gemstone getGemstone() {
        return gemstone;
    }

    public void setGemstone(Gemstone gemstone) {
        this.gemstone = gemstone;
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
        if(this.getGemstone() != null){
            l.add("§fIncrusted with gemstone ("+this.getGemstone()+")");
            l.add("");
        }
        l.add(color+"§l"+this.getRarity());
        itemMeta.setDisplayName(color+this.getName());
        itemMeta.setLore(l);
        itemMeta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        item.setItemMeta(itemMeta);
        return item;
    }
}
