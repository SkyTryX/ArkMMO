package fr.skytryx.arkmmo.api.classes;

import org.bukkit.Location;

public class Floor {

    Location spawn;
    String name;
    Integer number;

    public Floor(Location s, String n, Integer nu){
        this.spawn = s;
        this.name = n;
        this.number = nu;
    }

    public String getName() {
        return this.name;
    }

    public Integer getNumber() {
        return this.number;
    }

    public Location getSpawn() {
        return this.spawn;
    }
}

