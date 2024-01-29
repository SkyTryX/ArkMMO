package fr.skytryx.arkmmo.api;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Objects;

public class Database {

    YamlConfiguration data;
    String name;

    public Database(String n){
        name = n;
        data = YamlConfiguration.loadConfiguration(new File(Objects.requireNonNull(Bukkit.getServer().getPluginManager().getPlugin("ArkMMO")).getDataFolder(), name+".yml"));
    }

    public void save(){
        try {
            this.data.save(new File(Objects.requireNonNull(Bukkit.getServer().getPluginManager().getPlugin("ArkMMO")).getDataFolder(), name+".yml"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public YamlConfiguration getDatas(){
        return this.data;
    }

    public void addData(String path, Object d){
        this.data.set(path, d);
    }

    public void removeData(String path){
        this.data.set(path, null);
    }

    public Object getData(String path){
        return this.data.get(path);
    }

    public String getStringData(String path){
        return this.data.getString(path);
    }

    public List<?> getDataList(String path){
        return this.data.getList(path);
    }

    public Integer getDataInt(String path){
        return this.data.getInt(path);
    }


    public Boolean containsData(String path){
        return this.data.contains(path);
    }

    public Integer getSize(){
        if(this.data.getList("") == null) return 0;
        return Objects.requireNonNull(this.data.getList("")).size();
    }
}
