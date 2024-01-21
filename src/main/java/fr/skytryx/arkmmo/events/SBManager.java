package fr.skytryx.arkmmo.events;

import fr.skytryx.arkmmo.api.Ftion;
import fr.skytryx.arkmmo.api.classes.ArkPlayer;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;

import java.util.HashMap;
import java.util.Objects;

public class SBManager implements Listener {
    public HashMap<Player, Integer> score_list = new HashMap<>();
    @EventHandler
    public void join_sb(PlayerJoinEvent event){
        Scoreboard sb = Objects.requireNonNull(Bukkit.getServer().getScoreboardManager()).getNewScoreboard();
        Objective obj = sb.registerNewObjective("Scoreboard", "Dummy");
        ArkPlayer arkPlayer = Ftion.getArkPlayer(event.getPlayer());

        obj.setDisplaySlot(DisplaySlot.SIDEBAR);
        obj.setDisplayName("§b§lArkxia §f§l- §3§lMMORPG");

        Team race = sb.registerNewTeam("p-"+event.getPlayer().getUniqueId().toString().substring(0, 12));
        race.addEntry("§1");
        race.setPrefix("§3Race: ");
        race.setSuffix("§b"+(arkPlayer.getRace()));

        Team niveau = sb.registerNewTeam("n-"+event.getPlayer().getUniqueId().toString().substring(0, 12));
        niveau.addEntry("§2");
        niveau.setPrefix("§3Niveau: ");
        niveau.setSuffix("§b"+(arkPlayer.getXP()));

        Team gold = sb.registerNewTeam("a-"+event.getPlayer().getUniqueId().toString().substring(0, 12));
        gold.addEntry("§3");
        gold.setPrefix("§3Gold: ");
        gold.setSuffix("§b"+(arkPlayer.getGold()));

        Team guild = sb.registerNewTeam("g-"+event.getPlayer().getUniqueId().toString().substring(0, 12));
        guild.addEntry("§4");
        guild.setPrefix("§3Guild: ");
        guild.setSuffix("§b"+(arkPlayer.getGuild().getName()));

        obj.getScore("§7-------------------").setScore(7);
        obj.getScore("§1").setScore(6);
        obj.getScore("§2").setScore(5);
        obj.getScore("§3").setScore(4);
        obj.getScore("§4").setScore(2);
        obj.getScore("§7§7-------------------").setScore(1);
        obj.getScore("§6arkxia.ddns.net").setScore(0);


        event.getPlayer().setScoreboard(sb);

        int id = Bukkit.getScheduler().scheduleSyncRepeatingTask(Objects.requireNonNull(Bukkit.getPluginManager().getPlugin("ArkMMO")), ()->{
            race.setSuffix("§b"+(arkPlayer.getRace()));
            niveau.setSuffix("§b"+(arkPlayer.getXP()));
            gold.setSuffix("§b"+(arkPlayer.getGold()));
            guild.setSuffix("§b"+(arkPlayer.getGuild().getName()));
        }, 20L, 20L);
        score_list.put(event.getPlayer(), id);
    }

    @EventHandler
    public void onleavesb(PlayerQuitEvent event){
        if(score_list.containsKey(event.getPlayer())){
            Bukkit.getScheduler().cancelTask(score_list.get(event.getPlayer()));
        }
    }
}
