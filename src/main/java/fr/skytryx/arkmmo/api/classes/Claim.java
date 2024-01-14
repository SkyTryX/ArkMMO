package fr.skytryx.arkmmo.api.classes;

import fr.skytryx.arkmmo.api.Database;
import org.bukkit.Chunk;

import java.util.UUID;

public class Claim {

    Chunk chunk;
    Guild owner;

    UUID uuid;

    public Claim(Chunk c, Guild o, UUID uuid){
        this.chunk = c;
        this.owner = o;
        this.uuid = uuid;
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

    public UUID getUuid() {
        return uuid;
    }

    public void save(){
        Database db = new Database("claim");
        db.addData(this.getUuid()+".owner", this.getOwner());
        db.addData(this.getUuid()+".chunk", this.getChunk());
        db.save();
    }
}
