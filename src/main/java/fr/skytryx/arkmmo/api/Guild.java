package fr.skytryx.arkmmo.api;

import java.util.List;

public class Guild {

    String name;
    ArkPlayer owner;
    static List<ArkPlayer> members;
    static List<Claim> claims;
    Integer size = 15;
    Integer level = 0;
    Integer xp = 0;

    public Guild(String n, ArkPlayer o){
        name = n;
        owner = o;
        members.add(o);
    }

    public Integer getSize(){
        return size;
    }

    public String getName(){
        return name;
    }

    public void setName(String n){
        name = n;
    }

    public Integer getLevel(){
        return level;
    }

    public void setLevel(Integer l){
        level = l;
    }

    public Integer getXP(){
        return xp;
    }

    public void setXP(Integer x){
        xp = x;
    }

    public ArkPlayer getOwner(){
        return owner;
    }

    public void setOwner(ArkPlayer o){
        owner = o;
    }

    public void addMembers(ArkPlayer p){
        members.add(p);
    }

    public void removeMembers(ArkPlayer p){
        members.remove(p);
    }

    public List<ArkPlayer> getMembers(){
        return members;
    }

    public void addClaims(Claim c){
        claims.add(c);
    }

    public void removeClaims(Claim c){
        claims.remove(c);
    }

    public List<Claim> getClaims(){
        return claims;
    }
}
