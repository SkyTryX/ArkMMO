package fr.skytryx.arkmmo.api.classes;

import fr.skytryx.arkmmo.api.enums.PowerType;
import org.bukkit.Material;

public class ArkItem {

    public Material material;
    public String[] lore;
    public String name;
    public boolean is_glowing;

    public PowerType power;

    public ArkItem(Material m, String n, PowerType p){
        material = m;
        name = n;
        power = p;
        is_glowing = false;
        lore = new String[]{""};
    }

    public ArkItem(Material m, String n, PowerType p, String[] l, boolean glow){
        material = m;
        name = n;
        power = p;
        is_glowing = glow;
        lore = l;
    }


}
