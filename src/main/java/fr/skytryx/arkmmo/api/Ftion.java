package fr.skytryx.arkmmo.api;

import fr.skytryx.arkmmo.api.classes.ArkPlayer;
import org.bukkit.entity.Player;

import java.util.Map;

public class Ftion {

    public static String msgf(String prefix, String msg){
        return "§c["+prefix+"] §b"+msg;
    }

    public static ArkPlayer getArkPlayer(Player player){
        Database db = new Database("player");
        for (Map.Entry<String, Object> set :db.getDatas().getValues(false).entrySet()){
            if(player.getUniqueId().toString().equals(set.getKey())){
                ArkPlayer res = new ArkPlayer(player);
                res.setGold((Integer) db.getData(set.getKey()+".gold"));
                res.setAether((Integer) db.getData(set.getKey()+".aether"));
                res.setAgilite((Integer) db.getData(set.getKey()+".agilite"));
                res.setXP((Integer) db.getData(set.getKey()+".xp"));
                return (ArkPlayer)set.getValue();
            }
        }
        throw new NullPointerException("Couldn't find ArkPlayer in playerDB");
    }
}
