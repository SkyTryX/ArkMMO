package fr.skytryx.arkmmo.api.classes;

import org.bukkit.entity.Player;

import java.util.LinkedList;
import java.util.List;

public class ArkPlayer {

    Player player;
    Integer gold;
    Integer xp;
    Integer force;
    Integer agilite;
    Integer aether;
    List<Quest> completed_quest = new LinkedList<>();

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

    public boolean hasDoneQuest(Quest q){
        return completed_quest.contains(q);
    }

    public void addDoneQuest(Quest q){
        completed_quest.add(q);
    }

    public Integer getXp() {
        return xp;
    }

    public List<Quest> getCompleted_quest() {
        return completed_quest;
    }
}
