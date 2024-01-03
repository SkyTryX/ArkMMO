package fr.skytryx.arkmmo.api.classes;

import org.bukkit.Location;
import org.bukkit.Material;

import java.util.List;

public class Zone {

    List<Material> minable;
    Integer x1, y1, z1, x2, y2, z2;
    String name;

    public Zone(String n, List<Material> m, Location l1, Location l2){
        this.name = n;
        this.minable = m;
        this.x1 = l1.getBlockX();
        this.y1 = l1.getBlockY();
        this.z1 = l1.getBlockZ();
        this.x2 = l2.getBlockX();
        this.y2 = l2.getBlockY();
        this.z2 = l2.getBlockZ();
    }

    public String getName() {
        return this.name;
    }

    public Integer getX1() {
        return this.x1;
    }

    public Integer getX2() {
        return this.x2;
    }

    public Integer getY1() {
        return this.y1;
    }

    public Integer getY2() {
        return this.y2;
    }

    public Integer getZ1() {
        return this.z1;
    }

    public Integer getZ2() {
        return this.z2;
    }

    public List<Material> getMinable() {
        return this.minable;
    }

    public boolean inZone(Location l){
        return ((this.x1 < l.getBlockX() && l.getBlockX() < this.x2) || (this.x2 < l.getBlockX() && l.getBlockX() < this.x1)) && ((this.y1 < l.getBlockY() && l.getBlockY() < this.y2) || (this.y2 < l.getBlockY() && l.getBlockY() < this.y1)) && ((this.z1 < l.getBlockZ() && l.getBlockZ() < this.z2) || (this.z2 < l.getBlockZ() && l.getBlockZ() < this.z1));
    }
}