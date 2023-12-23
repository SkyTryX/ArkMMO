package fr.skytryx.arkmmo.api.classes;

import org.bukkit.Location;

public class Floor {

    Location spawn;
    String name;
    Integer number;

    public Floor(Location s, String n, Integer nu){
        spawn = s;
        name = n;
        number = nu;
    }

    public String getName() {
        return name;
    }

    public Integer getNumber() {
        return number;
    }

    public Location getSpawn() {
        return spawn;
    }
}

