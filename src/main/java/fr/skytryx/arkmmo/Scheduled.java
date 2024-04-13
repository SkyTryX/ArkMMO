package fr.skytryx.arkmmo;

import fr.skytryx.arkmmo.utils.Database;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Husk;
import org.bukkit.entity.Zombie;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.List;
import java.util.Objects;
import java.util.Random;

public class Scheduled {

    public static Integer getValidHeight(Integer y_min, Integer y_max, Integer x, Integer z, World world){
        for(int y = y_max; y > y_min; y--) {
            if (!List.of(Material.WATER, Material.LAVA, Material.AIR).contains(world.getBlockAt(x, y, z).getType())) {
                return y + 1;
            } else {
                y--;
            }
        }
        return y_min;
    }

    public static Integer randomNumberGen(int n1, int n2){
        Random random = new Random();
        if(n1 < n2){
            return random.nextInt(n1, n2);
        } else {
            return random.nextInt(n2, n1);
        }
    }

    public static void LaunchMobSpawning(){
        World world = Bukkit.getWorld("world");
        Bukkit.getScheduler().scheduleSyncRepeatingTask(Objects.requireNonNull(Bukkit.getPluginManager().getPlugin("ArkMMO")), () ->{
            Database db = new Database("mobregion");
            db.getDatas().getValues(false).forEach((k, v) ->{
                Integer x1 = db.getDataInt(k+".x1");
                Integer y1 = db.getDataInt(k+".y1");
                Integer z1 = db.getDataInt(k+".z1");
                Integer x2 = db.getDataInt(k+".x2");
                Integer y2 = db.getDataInt(k+".y2");
                Integer z2 = db.getDataInt(k+".z2");
                if(k.equals("tutorial")){
                    for(int i = db.getDataInt(k+".mob"); i < db.getDataInt(k+".mob_max"); i++){
                        int x = randomNumberGen(x2, x1);
                        int z = randomNumberGen(z2, z1);
                        assert world != null;
                        Zombie zombieEntity = (Zombie) world.spawnEntity(new Location(world, x,getValidHeight(y1, y2, x, z, world)+1 , z), EntityType.ZOMBIE);
                        zombieEntity.setHealth(14);
                        zombieEntity.setAI(false);
                        Objects.requireNonNull(zombieEntity.getEquipment()).setItem(EquipmentSlot.HAND, new ItemStack(Material.STONE_SWORD));
                        zombieEntity.setCustomName("§4Juan §7- §c"+zombieEntity.getHealth()+"§fHP");
                        zombieEntity.setCustomNameVisible(true);
                        zombieEntity.setPersistent(true);
                        zombieEntity.addScoreboardTag(k);
                    }
                }
                else if(k.equals("huskzone")){
                    for(int i = db.getDataInt(k+".mob"); i < db.getDataInt(k+".mob_max"); i++){
                        int x = randomNumberGen(x2, x1);
                        int z = randomNumberGen(z2, z1);
                        assert world != null;
                        Husk huskentity = (Husk) world.spawnEntity(new Location(world, x,getValidHeight(y1, y2, x, z, world)+1 , z), EntityType.ZOMBIE);
                        huskentity.setHealth(30);
                        huskentity.addPotionEffect(new PotionEffect(PotionEffectType.WEAKNESS, 99999, 0, false, false));
                        huskentity.setCustomName("§4Husk §7- §c"+huskentity.getHealth()+"§fHP");
                        huskentity.setCustomNameVisible(true);
                        huskentity.setPersistent(true);
                        huskentity.addScoreboardTag(k);
                    }
                }
                db.addData(k+".mob",  db.getDataInt(k+".mob_max"));
                db.save();
            });
        }, 100L, 200L);
    }

    public static void CheckingMob(){
        Bukkit.getScheduler().scheduleSyncRepeatingTask(Objects.requireNonNull(Bukkit.getPluginManager().getPlugin("ArkMMO")), () ->{
            World world = Bukkit.getWorld("world");
            Database db = new Database("mobregion");
            assert world != null;
            for (Entity entity : world.getEntities()) {
                if(entity.getScoreboardTags().contains("tutorial")){
                    int x1 = db.getDataInt("tutorial.x1");
                    int z1 = db.getDataInt("tutorial.z1");
                    int x2 = db.getDataInt("tutorial.x2");
                    int z2 = db.getDataInt("tutorial.z2");
                    int x = (int) entity.getLocation().getX();
                    int z = (int) entity.getLocation().getZ();
                    if(!((x1 < x && x < x2) || (x2 < x && x < x1)) || !((z1 < z && z < z2) || (z2 < z && z < z1))){
                        entity.teleport(new Location(world, x2-x1, 65 , z1-z2));
                    }
                }
            }
        }, 100L, 20L);
    }
}
