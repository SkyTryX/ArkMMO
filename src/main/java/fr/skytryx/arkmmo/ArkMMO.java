package fr.skytryx.arkmmo;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class ArkMMO extends JavaPlugin {

    @Override
    public void onEnable() {

        Bukkit.getLogger().info("Loading Event Manager");
        Bukkit.getLogger().info("Plugin Enabled");
    }

    @Override
    public void onDisable() {
        Bukkit.getLogger().info("Plugin Disabled");
    }
}
