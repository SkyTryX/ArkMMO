package fr.skytryx.arkmmo.api;

import fr.skytryx.arkmmo.api.classes.ArkPlayer;
import fr.skytryx.arkmmo.api.classes.Claim;
import fr.skytryx.arkmmo.api.classes.Guild;
import org.bukkit.Bukkit;
import org.bukkit.Chunk;
import org.bukkit.entity.Player;

import java.util.Map;
import java.util.Objects;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicReference;

public class Ftion {

    public static String msgf(String prefix, String msg){
        return "§c["+prefix+"] §b"+msg;
    }

    public static ArkPlayer getWithoutGuildArkPlayer(Player player){
        Database db = new Database("player");
        for (Map.Entry<String, Object> set :db.getDatas().getValues(false).entrySet()){
            if(player.getUniqueId().toString().equals(set.getKey())){
                ArkPlayer res = new ArkPlayer(player);
                res.setGold((Integer) db.getData(set.getKey()+".gold"));
                res.setAether((Integer) db.getData(set.getKey()+".aether"));
                res.setAgilite((Integer) db.getData(set.getKey()+".agilite"));
                res.setXP((Integer) db.getData(set.getKey()+".xp"));
                return res;
            }
        }
        return null;
    }
    public static Guild getGuildFromName(String name){
        Database db = new Database("guild");
        for (Map.Entry<String, Object> set :db.getDatas().getValues(false).entrySet()) {
            if (name.equals(set.getKey())) {
                Guild res = new Guild(name, Objects.requireNonNull(getWithoutGuildArkPlayer((Player) Bukkit.getOfflinePlayer(UUID.fromString(db.getStringData(set.getKey() + ".owner"))))));
                res.setXP((Integer) db.getData(set.getKey() + ".xp"));
                res.setLevel((Integer) db.getData(set.getKey() + ".level"));
                for (Object o : db.getDataList(set.getKey() + ".members")) {
                    ArkPlayer temp = getWithoutGuildArkPlayer((Player)Bukkit.getOfflinePlayer(UUID.fromString((String) o)));
                    if(temp != null && !res.getMembers().contains(String.valueOf(temp.getPlayer().getUniqueId()))) res.addMembers(temp);
                }
                for (Object o : db.getDataList(set.getKey() + ".moderators")) {
                    ArkPlayer temp = getWithoutGuildArkPlayer((Player)Bukkit.getOfflinePlayer(UUID.fromString((String) o)));
                    if(temp != null && !res.getModerators().contains(String.valueOf(temp.getPlayer().getUniqueId()))) res.addModerator(temp);
                }
                return res;
            }
        }
        return new Guild();
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
                res.setGuild(getGuildFromName(db.getStringData(set.getKey()+".guild")));
                return res;
            }
        }
        return null;
    }

    public static Claim loadClaim(Chunk chunk){
        Database db = new Database("claim");
        AtomicReference<String> p = new AtomicReference<>("");
        db.getDatas().getValues(false).forEach((path, obj) -> {
            if (db.getData(path + ".chunk").equals(chunk)) {
                p.set(path);
            }
        });
        if(!p.get().isEmpty()){
            return new Claim(chunk, db.getStringData(p.get()+".owner"), UUID.fromString(p.get()));
        }
        return null;
    }

    public static void removeClaim(Chunk chunk){
        Database db = new Database("claim");
        AtomicReference<String> p = new AtomicReference<>("");
        db.getDatas().getValues(false).forEach((path, obj) -> {
            if (db.getData(path + ".chunk").equals(chunk)) {
                p.set(path);
            }
        });
        if(!p.get().isEmpty()){
            db.removeData(p.get());
            db.save();
        }
    }

    public static void broadcastGuild(Guild guild, String msg){
        for (String member : guild.getMembers()) {
            Player player = Bukkit.getPlayer(UUID.fromString(member));
            if(player != null){
                player.sendMessage(msg);
            }
        }
    }
}
