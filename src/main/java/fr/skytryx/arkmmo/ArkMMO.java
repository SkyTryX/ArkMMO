package fr.skytryx.arkmmo;

import fr.skytryx.arkmmo.commands.claim.CommandAdminclaim;
import fr.skytryx.arkmmo.commands.claim.CommandClaim;
import fr.skytryx.arkmmo.commands.claim.CommandUnclaim;
import fr.skytryx.arkmmo.commands.guild.*;
import fr.skytryx.arkmmo.events.FirstJoin;
import fr.skytryx.arkmmo.events.SBManager;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;

public class ArkMMO extends JavaPlugin {

    @Override
    public void onEnable() {
        Bukkit.getLogger().info("Loading Commands");
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
        Bukkit.getLogger().info("Loaded commands ("+(System.currentTimeMillis()-start1)+")! Now loading Events");
        final long start2 = System.currentTimeMillis();
        getServer().getPluginManager().registerEvents(new FirstJoin() ,this);
        getServer().getPluginManager().registerEvents(new SBManager(), this);
        Bukkit.getLogger().info("Loaded events ("+(System.currentTimeMillis()-start2)+")");
        Bukkit.getLogger().info("Plugin Enabled ("+(System.currentTimeMillis()-start1)+")");
    }

    @Override
    public void onDisable() {
        Bukkit.getLogger().info("Plugin Disabled");
    }
}
