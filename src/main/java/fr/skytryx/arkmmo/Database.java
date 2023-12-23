package fr.skytryx.arkmmo;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Objects;

public class Database {

    YamlConfiguration data;
    String name;

    public Database(String n){
        name = n;
        data = YamlConfiguration.loadConfiguration(new File(Objects.requireNonNull(Bukkit.getServer().getPluginManager().getPlugin("ArkMMO")).getDataFolder(), name+".yml"));
    }

    public boolean load(){
        data = YamlConfiguration.loadConfiguration(new File(Objects.requireNonNull(Bukkit.getServer().getPluginManager().getPlugin("ArkMMO")).getDataFolder(), name+".yml"));
        return true;
    }

    public boolean save(){
        try {
            data.save(new File(Objects.requireNonNull(Bukkit.getServer().getPluginManager().getPlugin("ArkMMO")).getDataFolder(), name+".yml"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return false;
    }

    public String getName(){
        return name;
    }

    public YamlConfiguration getDatas(){
        return data;
    }

    public void addData(String path, Object d){
        data.set(path, d);
    }

    public void removeData(String path){
        data.set(path, null);
    }

    public Object getData(String path){
        return data.get(path);
    }

    public boolean containsData(String path){
        return data.contains(path);
    }
}
