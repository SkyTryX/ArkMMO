package fr.skytryx.arkmmo.api.classes;

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
        this.entityType = et;
        this.name = n;
        this.loot = l;
        this.health = h;
        this.damage = d;
   }

    public String getName() {
        return this.name;
    }

    public EntityType getEntityType() {
        return this.entityType;
    }

    public Integer getDamage() {
        return this.damage;
    }

    public Integer getHealth() {
        return this.health;
    }

    public List<ItemStack> getLoot() {
        return this.loot;
    }
}
