package fr.skytryx.arkmmo.events;

import fr.skytryx.arkmmo.utils.Database;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Zombie;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.server.ServerLoadEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;

import java.util.List;
import java.util.Objects;
import java.util.Random;

public class MobSpawningInZone implements Listener {

    public Integer getValidHeight(Integer y_min, Integer y_max, Integer x, Integer z, World world){
        for(int y = y_max; y > y_min; y--) {
            if (!List.of(Material.WATER, Material.LAVA, Material.AIR).contains(world.getBlockAt(x, y, z).getType())) {
                return y + 1;
            } else {
                y--;
            }
        }
        return y_min;
    }


    @EventHandler
    public void ServerLoadUp(ServerLoadEvent event){
        World world = Bukkit.getWorld("world");
        if(world != null) {
            Bukkit.getScheduler().scheduleSyncRepeatingTask(Objects.requireNonNull(Bukkit.getPluginManager().getPlugin("ArkMMO")), () ->{
                Database db = new Database("mobregion");
                db.getDatas().getValues(false).forEach((k, v) ->{
                    Integer x1 = db.getDataInt(k+".x1");
                    Integer y1 = db.getDataInt(k+".y1");
                    Integer z1 = db.getDataInt(k+".z1");
                    Integer x2 = db.getDataInt(k+".x2");
                    Integer y2 = db.getDataInt(k+".y2");
                    Integer z2 = db.getDataInt(k+".z2");
                    Random r = new Random();

                    if(k.equals("tutorial")){
                        for(int i = db.getDataInt(k+".mob"); i < db.getDataInt(k+".mob_max"); i++){
                            int x = r.nextInt(x1, x2);
                            int z = r.nextInt(z1, z2);
                            Zombie zombieEntity = (Zombie) world.spawnEntity(new Location(world, x,getValidHeight(y1, y2, x, z, world) , z), EntityType.ZOMBIE);
                            zombieEntity.setHealth(14);
                            Objects.requireNonNull(zombieEntity.getEquipment()).setItem(EquipmentSlot.HAND, new ItemStack(Material.STONE_SWORD));
                            zombieEntity.setCustomName("§4Juan §7- §c"+zombieEntity.getHealth()+"§fHP");
                            zombieEntity.setCustomNameVisible(true);
                            zombieEntity.addScoreboardTag(k);
                        }
                        db.addData(k+".mob",  db.getDataInt(k+".mob_max"));
                        db.save();
                    }
                });
            }, 100L, 200L);
        }
    }

    @EventHandler
    public void onMobKill(EntityDeathEvent event){
        if(event.getEntity().getScoreboardTags().contains("tutorial")){
            Database db = new Database("mobregion");
            db.addData("tutorial.mob", db.getDataInt("tutorial.mob")-1);
            db.save();
        }
    }
}
