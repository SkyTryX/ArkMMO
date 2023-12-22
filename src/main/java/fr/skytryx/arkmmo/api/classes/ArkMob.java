package fr.skytryx.arkmmo.api.classes;

import org.bukkit.Bukkit;
import org.bukkit.entity.EntityType;
import org.bukkit.inventory.ItemStack;

import java.util.List;

public class ArkMob {

    EntityType entityType;
    String name;
    List<ItemStack> loot;
    Integer health;
    Integer damage;
   public ArkMob(EntityType et, String n, List<ItemStack> l, Integer h, Integer d){
        entityType = et;
        name = n;
        loot = l;
        health = h;
        damage = d;
   }

    public String getName() {
        return name;
    }

    public EntityType getEntityType() {
        return entityType;
    }

    public Integer getDamage() {
        return damage;
    }

    public Integer getHealth() {
        return health;
    }

    public List<ItemStack> getLoot() {
        return loot;
    }
}
