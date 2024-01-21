package fr.skytryx.arkmmo;

import fr.skytryx.arkmmo.commands.claim.CommandAdminclaim;
import fr.skytryx.arkmmo.commands.claim.CommandClaim;
import fr.skytryx.arkmmo.commands.claim.CommandUnclaim;
import fr.skytryx.arkmmo.commands.guild.CommandGuildcreate;
import fr.skytryx.arkmmo.events.FirstJoin;
import fr.skytryx.arkmmo.events.SBManager;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;

public class ArkMMO extends JavaPlugin {

    @Override
    public void onEnable() {
        Bukkit.getLogger().info("Loading Commands");
        Objects.requireNonNull(getCommand("claim")).setExecutor(new CommandClaim());
        Objects.requireNonNull(getCommand("unclaim")).setExecutor(new CommandUnclaim());
        Objects.requireNonNull(getCommand("adminclaim")).setExecutor(new CommandAdminclaim());
        Objects.requireNonNull(getCommand("guildcreate")).setExecutor(new CommandGuildcreate());
        Bukkit.getLogger().info("Loading Events");
        getServer().getPluginManager().registerEvents(new FirstJoin() ,this);
        getServer().getPluginManager().registerEvents(new SBManager(), this);
        Bukkit.getLogger().info("Plugin Enabled");
    }

    @Override
    public void onDisable() {
        Bukkit.getLogger().info("Plugin Disabled");
    }
}
