package fr.skytryx.arkmmo.api.classes;

import fr.skytryx.arkmmo.api.Database;

import java.util.LinkedList;
import java.util.List;

public class Guild {

    String name;
    String owner;
    List<String> members = new LinkedList<>();
    List<String> claims = new LinkedList<>();
    List<String> moderators = new LinkedList<>();
    Integer size = 15;
    Integer level = 0;
    Integer xp = 0;

    public Guild(String n, ArkPlayer o){
        this.name = n;
        this.owner = String.valueOf(o.getPlayer().getUniqueId());
        this.members.add(o.getPlayer().getUniqueId().toString());
        this.moderators.add(o.getPlayer().getUniqueId().toString());
    }

    public Guild(){
        this.name = "None";
        this.owner = "None";
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

    public String getOwner(){
        return this.owner;
    }

    public void setOwner(ArkPlayer o){
        this.owner = o.getPlayer().getUniqueId().toString();
    }

    public void addMembers(ArkPlayer p){
        this.members.add(p.getPlayer().getUniqueId().toString());
    }

    public void removeMembers(ArkPlayer p){
        this.members.remove(p.getPlayer().getUniqueId().toString());
    }

    public List<String> getMembers(){
        return this.members;
    }

    public void addClaims(Claim c){
        this.claims.add(c.getUuid().toString());
    }

    public void removeClaims(Claim c){
        this.claims.remove(c.getUuid().toString());
    }

    public List<String> getClaims(){
        return this.claims;
    }

    public List<String> getModerators() {
        return this.moderators;
    }

    public void addModerator(ArkPlayer p){
        this.moderators.add(p.getPlayer().getUniqueId().toString());
    }

    public void removeModerator(ArkPlayer p){
        this.moderators.remove(p.getPlayer().getUniqueId().toString());
    }

    public void save(){
        Database db = new Database("guild");
        db.addData(this.getName()+".claims", this.getClaims());
        db.addData(this.getName()+".level", this.getLevel());
        db.addData(this.getName()+".members", this.getMembers());
        db.addData(this.getName()+".owner", this.getOwner());
        db.addData(this.getName()+".size", this.getSize());
        db.addData(this.getName()+".xp", this.getXP());
        db.addData(this.getName()+".moderators", this.getModerators());
        db.save();
    }
}
