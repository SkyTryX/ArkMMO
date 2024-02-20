package fr.skytryx.arkmmo.api;

import fr.skytryx.arkmmo.api.classes.ArkItem;
import fr.skytryx.arkmmo.api.classes.ArkPlayer;
import fr.skytryx.arkmmo.api.classes.Claim;
import fr.skytryx.arkmmo.api.classes.Guild;
import fr.skytryx.arkmmo.api.enums.Gemstone;
import fr.skytryx.arkmmo.api.enums.Rarity;
import org.bukkit.Bukkit;
import org.bukkit.Chunk;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.jetbrains.annotations.Nullable;

import java.util.List;
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

    public static Claim loadClaim(Chunk chunk, World world){
        Database db = new Database("claim");
        AtomicReference<String> p = new AtomicReference<>("");
        db.getDatas().getValues(false).forEach((path, obj) -> {
            if(world.getChunkAt(db.getDataInt(path + ".chunk_x"), db.getDataInt(path + ".chunk_z")).equals(chunk)){
                p.set(path);
            }
        });
        if(!p.get().isEmpty()){
            return new Claim(chunk, db.getStringData(p.get()+".owner"), UUID.fromString(p.get()));
        }
        return null;
    }

    public static void removeClaim(Chunk chunk, World world){
        Database db = new Database("claim");
        AtomicReference<String> p = new AtomicReference<>("");
        db.getDatas().getValues(false).forEach((path, obj) -> {
            if (world.getChunkAt(db.getDataInt(path + ".chunk_x"), db.getDataInt(path + ".chunk_z")).equals(chunk)) {
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

    public static void fillInv(Inventory i){
        ItemStack item = itemCreator(Material.BLACK_STAINED_GLASS_PANE, " ");
        for(int j = 0; j < i.getSize(); j++){
            i.setItem(j, item);
        }
    }

    public static ItemStack itemCreator(Material m, String n){
        ItemStack item = new ItemStack(m);
        ItemMeta itemMeta = item.getItemMeta();
        itemMeta.setDisplayName(n);
        item.setItemMeta(itemMeta);
        return item;
    }

    @Nullable
    public static Rarity getRarity(ItemStack item) {
        Rarity r = null;
        if(item.getItemMeta().getDisplayName().startsWith("§f")) r = Rarity.COMMON;
        else if(item.getItemMeta().getDisplayName().startsWith("§a")) r = Rarity.UNCOMMON;
        else if(item.getItemMeta().getDisplayName().startsWith("§b")) r = Rarity.RARE;
        else if(item.getItemMeta().getDisplayName().startsWith("§d")) r = Rarity.EPIC;
        else if(item.getItemMeta().getDisplayName().startsWith("§6")) r = Rarity.LEGENDARY;
        else if(item.getItemMeta().getDisplayName().startsWith("§e")) r = Rarity.MYTHIC;
        return r;
    }

    public static ArkItem getArkfromItem(ItemStack item) {
        Rarity r = getRarity(item);
        List<String> lore = item.getItemMeta().getLore();
        if (r != null && lore != null) {
            lore.removeFirst();
            lore.removeLast();
            lore.removeLast();
            if (lore.getLast().contains("§fIncrusted with gemstone")) {
                if (lore.getLast().contains("(RED)")) {
                    lore.removeLast();
                    lore.removeLast();
                    return new ArkItem(item.getType(), item.getItemMeta().getDisplayName(), r, lore.toArray(new String[0]), Gemstone.RED);
                }
            }
            return new ArkItem(item.getType(), item.getItemMeta().getDisplayName(), r, lore.toArray(new String[0]));
        }
        return null;
    }
}
