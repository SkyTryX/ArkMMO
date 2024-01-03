package fr.skytryx.arkmmo.api.classes;

import org.bukkit.Chunk;

public class Claim {

    Chunk chunk;
    Guild owner;

    public Claim(Chunk c, Guild o){
        this.chunk = c;
        this.owner = o;
    }

    public Guild getOwner(){
        return this.owner;
    }

    public void setOwner(Guild o){
        this.owner = o;
    }

    public Chunk getChunk() {
        return this.chunk;
    }
}
