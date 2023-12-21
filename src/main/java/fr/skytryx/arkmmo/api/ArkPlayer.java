package fr.skytryx.arkmmo.api;

import org.bukkit.entity.Player;

public class ArkPlayer {

    Player player;
    Integer gold;
    Integer xp;
    Integer force;
    Integer agilite;
    Integer aether;

    public ArkPlayer(Player p, Integer g, Integer x, Integer f, Integer a, Integer ae){
        player = p;
        gold = g;
        xp = x;
        force = f;
        agilite = a;
        aether = ae;
    }

    public Player getPlayer(){
        return player;
    }

    public Integer getGold(){
        return gold;
    }

    public void setGold(Integer g){
        gold = g;
    }

    public Integer getXP(){
        return xp;
    }

    public void setXP(Integer x){
        xp = x;
    }

    public Integer getForce(){
        return force;
    }

    public void setForce(Integer f){
        force = f;
    }

    public Integer getAgilite(){
        return agilite;
    }

    public void setAgilite(Integer a){
        agilite = a;
    }

    public Integer getAether(){
        return aether;
    }

    public void setAether(Integer ae){
        aether = ae;
    }
}
