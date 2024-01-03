package fr.skytryx.arkmmo.api.classes;

import fr.skytryx.arkmmo.api.enums.PowerType;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;

import java.util.LinkedList;
import java.util.List;

public class ArkItem {

    Material material;
    String[] lore;
    String name;
    Boolean is_glowing;
    PowerType power;
    List<ArkEnchant> arkEnchants = new LinkedList<>();
    List<Enchantment> enchants = new LinkedList<>();

    public ArkItem(Material m, String n, PowerType p){
        this.material = m;
        this.name = n;
        this.power = p;
        this.is_glowing = false;
        this.lore = new String[]{""};
    }

    public ArkItem(Material m, String n, PowerType p, String[] l, Boolean glow){
        this.material = m;
        this.name = n;
        this.power = p;
        this.is_glowing = glow;
        this.lore = l;
    }

    public Boolean getIs_glowing() {
        return this.is_glowing;
    }

    public List<ArkEnchant> getArkEnchants() {
        return this.arkEnchants;
    }

    public List<Enchantment> getEnchants() {
        return this.enchants;
    }

    public Material getMaterial() {
        return this.material;
    }

    public PowerType getPower() {
        return this.power;
    }

    public String getName() {
        return this.name;
    }

    public String[] getLore() {
        return this.lore;
    }

    public void addArkEnchant(ArkEnchant e){
        this.arkEnchants.add(e);
    }

    public void addEnchant(Enchantment e){
        this.enchants.add(e);
    }
}
