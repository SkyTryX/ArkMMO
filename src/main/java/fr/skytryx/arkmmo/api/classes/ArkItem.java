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
        material = m;
        name = n;
        power = p;
        is_glowing = false;
        lore = new String[]{""};
    }

    public ArkItem(Material m, String n, PowerType p, String[] l, Boolean glow){
        material = m;
        name = n;
        power = p;
        is_glowing = glow;
        lore = l;
    }

    public Boolean getIs_glowing() {
        return is_glowing;
    }

    public List<ArkEnchant> getArkEnchants() {
        return arkEnchants;
    }

    public List<Enchantment> getEnchants() {
        return enchants;
    }

    public Material getMaterial() {
        return material;
    }

    public PowerType getPower() {
        return power;
    }

    public String getName() {
        return name;
    }

    public String[] getLore() {
        return lore;
    }

    public void addArkEnchant(ArkEnchant e){
        arkEnchants.add(e);
    }

    public void addEnchant(Enchantment e){
        enchants.add(e);
    }
}
