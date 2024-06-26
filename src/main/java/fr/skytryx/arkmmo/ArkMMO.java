package fr.skytryx.arkmmo;

import fr.skytryx.arkmmo.commands.gold.CommandAddgold;
import fr.skytryx.arkmmo.commands.gold.CommandRemovegold;
import fr.skytryx.arkmmo.commands.gold.CommandSetgold;
import fr.skytryx.arkmmo.commands.mobs.CommandMobregion;
import fr.skytryx.arkmmo.commands.mobs.CommandSpawnmob;
import fr.skytryx.arkmmo.events.*;
import fr.skytryx.arkmmo.utils.classes.ArkItem;
import fr.skytryx.arkmmo.utils.enums.Rarity;
import fr.skytryx.arkmmo.commands.claim.*;
import fr.skytryx.arkmmo.commands.item.CommandGemincruster;
import fr.skytryx.arkmmo.commands.item.CommandGiveitem;
import fr.skytryx.arkmmo.commands.guild.*;
import fr.skytryx.arkmmo.events.menu.GemincrusterMenu;
import fr.skytryx.arkmmo.events.menu.GemunincrusterMenu;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;
import java.util.List;
import java.util.Objects;

public class ArkMMO extends JavaPlugin {

    public static HashMap<String, ArkItem> items = new HashMap<>();

    @Override
    public void onEnable() {
        Bukkit.getLogger().info("[ArkMMO] Loading Commands");
        final long start1 = System.currentTimeMillis();
        Objects.requireNonNull(getCommand("claim")).setExecutor(new CommandClaim());
        Objects.requireNonNull(getCommand("unclaim")).setExecutor(new CommandUnclaim());
        Objects.requireNonNull(getCommand("adminclaim")).setExecutor(new CommandAdminclaim());
        Objects.requireNonNull(getCommand("seeclaim")).setExecutor(new CommandSeeclaim());
        Objects.requireNonNull(getCommand("bypassclaim")).setExecutor(new CommandBypassclaim());
        Objects.requireNonNull(getCommand("lobbyregion")).setExecutor(new CommandLobbyregion());

        Objects.requireNonNull(getCommand("guildcreate")).setExecutor(new CommandGuildcreate());
        Objects.requireNonNull(getCommand("guildinvite")).setExecutor(new CommandGuildinvite());
        Objects.requireNonNull(getCommand("guildjoin")).setExecutor(new CommandGuildjoin());
        Objects.requireNonNull(getCommand("guildmsg")).setExecutor(new CommandGuildmsg());
        Objects.requireNonNull(getCommand("guildinfo")).setExecutor(new CommandGuildinfo());
        Objects.requireNonNull(getCommand("guildleave")).setExecutor(new CommandGuildleave());
        Objects.requireNonNull(getCommand("guildkick")).setExecutor(new CommandGuildkick());
        Objects.requireNonNull(getCommand("guilddisband")).setExecutor(new CommandGuilddisband());
        Objects.requireNonNull(getCommand("guildpromote")).setExecutor(new CommandGuildpromote());
        Objects.requireNonNull(getCommand("guilddemote")).setExecutor(new CommandGuilddemote());

        Objects.requireNonNull(getCommand("giveitem")).setExecutor(new CommandGiveitem());
        Objects.requireNonNull(getCommand("gemsincruster")).setExecutor(new CommandGemincruster());

        Objects.requireNonNull(getCommand("addgold")).setExecutor(new CommandAddgold());
        Objects.requireNonNull(getCommand("removegold")).setExecutor(new CommandRemovegold());
        Objects.requireNonNull(getCommand("setgold")).setExecutor(new CommandSetgold());

        Objects.requireNonNull(getCommand("spawnmob")).setExecutor(new CommandSpawnmob());
        Objects.requireNonNull(getCommand("mobregion")).setExecutor(new CommandMobregion());



        Bukkit.getLogger().info("[ArkMMO] Loaded commands ("+(System.currentTimeMillis()-start1)+"ms)! Now loading Events");
        long start2 = System.currentTimeMillis();
        getServer().getPluginManager().registerEvents(new FirstJoin() ,this);
        getServer().getPluginManager().registerEvents(new SBManager(), this);
        getServer().getPluginManager().registerEvents(new ClaimCheck(), this);
        getServer().getPluginManager().registerEvents(new GemincrusterMenu(), this);
        getServer().getPluginManager().registerEvents(new GemunincrusterMenu(), this);
        getServer().getPluginManager().registerEvents(new ChatEvent(), this);
        getServer().getPluginManager().registerEvents(new LobbyInteract(), this);
        getServer().getPluginManager().registerEvents(new BreakingEvent(), this);
        getServer().getPluginManager().registerEvents(new MobSpawningInZone(), this);
        Bukkit.getLogger().info("[ArkMMO] Loaded events ("+(System.currentTimeMillis()-start2)+"ms)! Now loading Items");

        items.put("midas_sword", new ArkItem(Material.GOLDEN_SWORD, "Midas Sword", Rarity.MYTHIC, List.of("§6The §nbest§r§6 of the golden swords").toArray(new String[0])));
        items.put("arch_sword", new ArkItem(Material.STICK, "Arch Sword", Rarity.UNCOMMON, List.of("§8§oUnfortunately, it sucks, a lot..").toArray(new String[0])));
        items.put("curved_sword", new ArkItem(Material.DIAMOND_SWORD, "Curved Sword", Rarity.COMMON, List.of("§8§oKinda look funny not gonna lie").toArray(new String[0])));
        items.put("dance_pants", new ArkItem(Material.GOLDEN_LEGGINGS, "Dance Pants", Rarity.RARE, List.of("§8§oDancin..").toArray(new String[0])));
        items.put("blue_gemstone", new ArkItem(Material.BLUE_CANDLE, "Blue Gemstone", Rarity.EPIC, List.of("§r§bGrants ...").toArray(new String[0])));
        items.put("red_gemstone", new ArkItem(Material.RED_CANDLE, "Red Gemstone", Rarity.EPIC, List.of("§r§bGrants ...").toArray(new String[0])));
        items.put("white_gemstone", new ArkItem(Material.WHITE_CANDLE, "White Gemstone", Rarity.EPIC, List.of("§r§bGrants ...").toArray(new String[0])));
        items.put("yellow_gemstone", new ArkItem(Material.YELLOW_CANDLE, "Yellow Gemstone", Rarity.EPIC, List.of("§r§bGrants ...").toArray(new String[0])));
        Bukkit.getLogger().info("[ArkMMO] Loaded items ("+(System.currentTimeMillis()-start2)+"ms)");
        start2 = System.currentTimeMillis();
        Scheduled.LaunchMobSpawning();
        Bukkit.getLogger().info("[ArkMMO] Loaded scheduled events ("+(System.currentTimeMillis()-start2)+"ms)");
        Bukkit.getLogger().info("[ArkMMO] Plugin Enabled ("+(System.currentTimeMillis()-start1)+"ms)");
    }

    @Override
    public void onDisable() {
        Bukkit.getLogger().info("Plugin Disabled");
    }
}
