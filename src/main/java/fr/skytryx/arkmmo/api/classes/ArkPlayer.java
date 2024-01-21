package fr.skytryx.arkmmo.api.classes;

import fr.skytryx.arkmmo.api.Database;
import fr.skytryx.arkmmo.api.enums.Races;
import org.bukkit.entity.Player;

import java.util.LinkedList;
import java.util.List;

public class ArkPlayer {

    Player player;
    Integer gold = 1000;
    Integer xp = 0;
    Integer force = 1;
    Integer agilite = 1;
    Integer aether = 1;
    Races race = Races.NONE;
    List<Quest> completed_quest = new LinkedList<>();

    Guild guild;

    public ArkPlayer(Player p){
        this.player = p;
    }

    public Player getPlayer(){
        return this.player;
    }

    public Integer getGold(){
        return this.gold;
    }

    public void setGold(Integer g){
        this.gold = g;
    }

    public Integer getXP(){
        return this.xp;
    }

    public void setXP(Integer x){
        this.xp = x;
    }

    public Integer getForce(){
        return this.force;
    }

    public void setForce(Integer f){
        this.force = f;
    }

    public Integer getAgilite(){
        return this.agilite;
    }

    public void setAgilite(Integer a){
        this.agilite = a;
    }

    public Integer getAether(){
        return this.aether;
    }

    public void setAether(Integer ae){
        this.aether = ae;
    }

    public boolean hasDoneQuest(Quest q){
        return this.completed_quest.contains(q);
    }

    public void addDoneQuest(Quest q){
        this.completed_quest.add(q);
    }

    public List<Quest> getCompleted_quest() {
        return this.completed_quest;
    }

    public Guild getGuild() {
        player.sendMessage(String.valueOf(this.guild));
        if(this.guild == null){
            return new Guild();
        }
        return this.guild;

    }

    public void setGuild(Guild guild) {
        this.guild = guild;
    }

    public Races getRace() {
        return race;
    }

    public void setRace(Races race) {
        this.race = race;
    }

    public void save(){
        Database db = new Database("player");
        db.addData(this.player.getUniqueId()+".gold", this.getGold());
        db.addData(this.player.getUniqueId()+".force", this.getForce());
        db.addData(this.player.getUniqueId()+".agilite", this.getAgilite());
        db.addData(this.player.getUniqueId()+".aether", this.getAether());
        db.addData(this.player.getUniqueId()+".guild", this.getGuild().getName());
        db.addData(this.player.getUniqueId()+".xp", this.getXP());
        db.addData(this.player.getUniqueId()+".completed_quest", this.getCompleted_quest());
        db.save();
    }
}
