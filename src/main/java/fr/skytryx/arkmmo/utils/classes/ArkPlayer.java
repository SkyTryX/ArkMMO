package fr.skytryx.arkmmo.utils.classes;

import fr.skytryx.arkmmo.utils.Database;
import fr.skytryx.arkmmo.utils.enums.Races;
import org.bukkit.entity.Player;

import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

public class ArkPlayer {

    Player player;
    String UUID;
    String name;
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
        this.UUID = p.getUniqueId().toString();
        this.name = p.getName();
    }

    public ArkPlayer(UUID u, String n){
        this.UUID = u.toString();
        this.name = n;
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

    public String getUUID() {
        return UUID;
    }

    public String getName() {
        return name;
    }

    public void save(){
        Database db = new Database("player");
        db.addData(this.getUUID()+".gold", this.getGold());
        db.addData(this.getUUID()+".force", this.getForce());
        db.addData(this.getUUID()+".agilite", this.getAgilite());
        db.addData(this.getUUID()+".aether", this.getAether());
        db.addData(this.getUUID()+".guild", this.getGuild().getName());
        db.addData(this.getUUID()+".xp", this.getXP());
        db.addData(this.getUUID()+".completed_quest", this.getCompleted_quest());
        db.save();
    }
}
