package fr.skytryx.arkmmo.utils.classes;

import fr.skytryx.arkmmo.utils.Database;
import org.bukkit.Chunk;

import java.util.UUID;

public class Claim {

    Chunk chunk;
    String owner;

    UUID uuid;

    public Claim(Chunk c, String o, UUID uuid){
        this.chunk = c;
        this.owner = o;
        this.uuid = uuid;
    }

    public String getOwner(){
        return this.owner;
    }

    public void setOwner(String o){
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
        db.addData(this.getUuid()+".chunk_x", this.getChunk().getX());
        db.addData(this.getUuid()+".chunk_z", this.getChunk().getZ());
        db.save();
    }
}
