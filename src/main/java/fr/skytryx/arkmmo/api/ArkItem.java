package fr.skytryx.arkmmo.api;

import org.bukkit.Material;

public class ArkItem {

    public Material material;
    public String[] lore;
    public String name;
    public boolean is_glowing;

    public String power;

    public ArkItem(Material m, String n, String p){
        material = m;
        name = n;
        power = p;
        is_glowing = false;
        lore = new String[]{""};
    }

    public ArkItem(Material m, String n, String p, String[] l, boolean glow){
        material = m;
        name = n;
        power = p;
        is_glowing = glow;
        lore = l;
    }


}
