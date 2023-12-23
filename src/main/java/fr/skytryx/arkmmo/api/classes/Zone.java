package fr.skytryx.arkmmo.api.classes;

import org.bukkit.Location;
import org.bukkit.Material;

import java.util.List;

public class Zone {

    List<Material> minable;
    Integer x1, y1, z1, x2, y2, z2;
    String name;

    public Zone(String n, List<Material> m, Location l1, Location l2){
        name = n;
        minable = m;
        x1 = l1.getBlockX();
        y1 = l1.getBlockY();
        z1 = l1.getBlockZ();
        x2 = l2.getBlockX();
        y2 = l2.getBlockY();
        z2 = l2.getBlockZ();
    }

    public String getName() {
        return name;
    }

    public Integer getX1() {
        return x1;
    }

    public Integer getX2() {
        return x2;
    }

    public Integer getY1() {
        return y1;
    }

    public Integer getY2() {
        return y2;
    }

    public Integer getZ1() {
        return z1;
    }

    public Integer getZ2() {
        return z2;
    }

    public List<Material> getMinable() {
        return minable;
    }

    public boolean inZone(Location l){
        return ((x1 < l.getBlockX() && l.getBlockX() < x2) || (x2 < l.getBlockX() && l.getBlockX() < x1)) && ((y1 < l.getBlockY() && l.getBlockY() < y2) || (y2 < l.getBlockY() && l.getBlockY() < y1)) && ((z1 < l.getBlockZ() && l.getBlockZ() < z2) || (z2 < l.getBlockZ() && l.getBlockZ() < z1));
    }
}