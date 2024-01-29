package fr.skytryx.arkmmo;

import fr.skytryx.arkmmo.api.classes.ArkItem;
import fr.skytryx.arkmmo.api.enums.Rarity;
import fr.skytryx.arkmmo.commands.item.CommandGiveitem;
import fr.skytryx.arkmmo.commands.claim.CommandAdminclaim;
import fr.skytryx.arkmmo.commands.claim.CommandClaim;
import fr.skytryx.arkmmo.commands.claim.CommandUnclaim;
import fr.skytryx.arkmmo.commands.guild.*;
import fr.skytryx.arkmmo.events.FirstJoin;
import fr.skytryx.arkmmo.events.SBManager;
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
        Bukkit.getLogger().info("[ArkMMO] Loaded commands ("+(System.currentTimeMillis()-start1)+"ms)! Now loading Events");
        long start2 = System.currentTimeMillis();
        getServer().getPluginManager().registerEvents(new FirstJoin() ,this);
        getServer().getPluginManager().registerEvents(new SBManager(), this);
        Bukkit.getLogger().info("[ArkMMO] Loaded events ("+(System.currentTimeMillis()-start2)+"ms)! Now loading Items");
        start2 = System.currentTimeMillis();
        items.put("midas_sword", new ArkItem(Material.GOLDEN_SWORD, "Midas Sword", Rarity.MYTHIC, List.of("§6The §nbest§r§6 of the golden swords").toArray(new String[0])));
        items.put("arch_sword", new ArkItem(Material.STICK, "Arch Sword", Rarity.UNCOMMON, List.of("§8§oUnfortunately, it sucks, a lot..").toArray(new String[0])));
        Bukkit.getLogger().info("[ArkMMO] Loaded items ("+(System.currentTimeMillis()-start2)+"ms)");
        Bukkit.getLogger().info("[ArkMMO] Plugin Enabled ("+(System.currentTimeMillis()-start1)+"ms)");
    }

    @Override
    public void onDisable() {
        Bukkit.getLogger().info("Plugin Disabled");
    }
}
