package fr.skytryx.arkmmo.api.classes;

import org.bukkit.Chunk;

public class Claim {

    Chunk chunk;
    Guild owner;

    public Claim(Chunk c, Guild o){
        chunk = c;
        owner = o;
    }

    public Guild getOwner(){
        return owner;
    }

    public void setOwner(Guild o){
        owner = o;
    }
}
