package fr.skytryx.arkmmo.events;

import fr.skytryx.arkmmo.utils.Database;
import org.bukkit.Material;
import org.bukkit.entity.Husk;
import org.bukkit.entity.Zombie;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityCombustEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.inventory.ItemStack;

import java.util.Random;

public class MobSpawningInZone implements Listener {


    @EventHandler
    public void onHitJuan(EntityDamageEvent event) {
        if(event.getEntity().getCustomName() == null) return;
        if (!event.getEntity().getScoreboardTags().isEmpty()) {
            if(event.getEntity().getScoreboardTags().contains("tutorial")){
                event.getEntity().setCustomName("§4Juan §7- §c" + (Math.round(((Zombie) event.getEntity()).getHealth() - event.getDamage() + 1) + "§fHP"));
                if(!((Zombie) event.getEntity()).hasAI()){
                    ((Zombie) event.getEntity()).setAI(true);
                }
            } else if(event.getEntity().getScoreboardTags().contains("huskzone")) {
                event.getEntity().setCustomName("§4Husk §7- §c" + (Math.round(((Husk) event.getEntity()).getHealth() - event.getDamage() + 1) + "§fHP"));
            }
        }
    }

    @EventHandler
    public void onEntityCombust(EntityCombustEvent event) {
        if (!event.getEntity().getScoreboardTags().isEmpty()) {
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onMobKill(EntityDeathEvent event){
        if(event.getEntity().getScoreboardTags().contains("tutorial")){
            Database db = new Database("mobregion");
            db.addData("tutorial.mob", db.getDataInt("tutorial.mob")-1);
            event.getDrops().clear();
            event.getDrops().add(new ItemStack(Material.ROTTEN_FLESH));
            if(new Random().nextInt(0,10) == 0){
                event.getDrops().add(new ItemStack(Material.STONE_SWORD));
            }
            db.save();
        }
        else if(event.getEntity().getScoreboardTags().contains("huskzone")){
            Database db = new Database("mobregion");
            db.addData("huskzone.mob", db.getDataInt("huskzone.mob")-1);
            event.getDrops().clear();
            event.getDrops().add(new ItemStack(Material.ROTTEN_FLESH));
            if(new Random().nextInt(0,10) == 0){
                event.getDrops().add(new ItemStack(Material.IRON_INGOT));
            }
            if(new Random().nextInt(0,20) == 0){
                event.getDrops().add(new ItemStack(Material.GOLD_INGOT));
            }
            db.save();
        }
    }
}
