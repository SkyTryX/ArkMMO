package fr.skytryx.arkmmo.api.classes;

import fr.skytryx.arkmmo.api.Database;

import java.util.LinkedList;
import java.util.List;

public class Guild {

    String name;
    ArkPlayer owner;
    List<ArkPlayer> members = new LinkedList<>();
    List<Claim> claims = new LinkedList<>();
    Integer size = 15;
    Integer level = 0;
    Integer xp = 0;

    public Guild(String n, ArkPlayer o){
        this.name = n;
        this.owner = o;
        this.members.add(o);
    }

    public Integer getSize(){
        return this.size;
    }

    public String getName(){
        return this.name;
    }

    public void setName(String n){
        this.name = n;
    }

    public Integer getLevel(){
        return this.level;
    }

    public void setLevel(Integer l){
        this.level = l;
    }

    public Integer getXP(){
        return this.xp;
    }

    public void setXP(Integer x){
        this.xp = x;
    }

    public ArkPlayer getOwner(){
        return this.owner;
    }

    public void setOwner(ArkPlayer o){
        this.owner = o;
    }

    public void addMembers(ArkPlayer p){
        this.members.add(p);
    }

    public void removeMembers(ArkPlayer p){
        this.members.remove(p);
    }

    public List<ArkPlayer> getMembers(){
        return this.members;
    }

    public void addClaims(Claim c){
        this.claims.add(c);
    }

    public void removeClaims(Claim c){
        this.claims.remove(c);
    }

    public List<Claim> getClaims(){
        return this.claims;
    }

    public void save(){
        Database db = new Database("player");
        db.addData(this.getName()+".claims", this.getClaims());
        db.addData(this.getName()+".level", this.getLevel());
        db.addData(this.getName()+".members", this.getMembers());
        db.addData(this.getName()+".owner", this.getOwner());
        db.addData(this.getName()+".size", this.getSize());
        db.addData(this.getName()+".xp", this.getXP());
        db.save();
    }
}
