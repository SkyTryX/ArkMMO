package fr.skytryx.arkmmo.api;

import fr.skytryx.arkmmo.api.classes.ArkPlayer;
import org.bukkit.entity.Player;

import java.util.Map;

public class Ftion {

    public static String msgf(String prefix, String msg){
        return "§c["+prefix+"] §b"+msg;
    }

    public static ArkPlayer getArkPlayer(Player player){
        for (Map.Entry<String, Object> set : new Database("arkplayer").getDatas().getValues(false).entrySet()){
            if(player.getUniqueId().toString().equals(set.getKey())){
                return (ArkPlayer)set.getValue();
            }
        }
        throw new NullPointerException("Couldn't find ArkPlayer in playerDB");
    }
}
