package fr.skytryx.arkmmo.utils.classes;

import org.bukkit.enchantments.EnchantmentTarget;

public class ArkEnchant {

    Integer max_level;
    Integer level;
    String name;
    String description;
    EnchantmentTarget enchantmentTarget;

    public ArkEnchant(Integer ml, Integer l, String n, String d, EnchantmentTarget et){
        this.max_level = ml;
        this.level = l;
        this.name = n;
        this.description = d;
        this.enchantmentTarget = et;
    }

    public String getName(){
        return this.name;
    }

    public Integer getLevel(){
        return this.level;
    }

    public Integer getMaxLevel(){
        return this.max_level;
    }

    public String getDescription(){
        return this.description;
    }

    public EnchantmentTarget getEnchantmentTarget() {
        return this.enchantmentTarget;
    }

    public Integer getMax_level() {
        return this.max_level;
    }
}
