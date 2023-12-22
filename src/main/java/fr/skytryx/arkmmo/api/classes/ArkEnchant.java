package fr.skytryx.arkmmo.api.classes;

import org.bukkit.enchantments.EnchantmentTarget;

public class ArkEnchant {

    Integer max_level;
    Integer level;
    String name;
    String description;
    EnchantmentTarget enchantmentTarget;

    public ArkEnchant(Integer ml, Integer l, String n, String d, EnchantmentTarget et){
        max_level = ml;
        level = l;
        name = n;
        description = d;
        enchantmentTarget = et;
    }

    public String getName(){
        return name;
    }

    public Integer getLevel(){
        return level;
    }

    public Integer getMaxLevel(){
        return max_level;
    }

    public String getDescription(){
        return description;
    }

    public EnchantmentTarget getEquipmentType(){
        return enchantmentTarget;
    }

    public EnchantmentTarget getEnchantmentTarget() {
        return enchantmentTarget;
    }

    public Integer getMax_level() {
        return max_level;
    }
}
