package fr.skytryx.arkmmo.commands.claim;

import fr.skytryx.arkmmo.utils.Ftion;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class CommandSeeclaim implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        if(commandSender instanceof Player player){
            int basechunk_x = player.getLocation().getChunk().getX();
            int basechunk_z = player.getLocation().getChunk().getZ();
            World world = player.getWorld();
            player.sendMessage("§6§lEXPLANATION\n§a▧ §bmeans the chunk is not claimed, §c▧ §bmeans it's claimed");
            for(int i = basechunk_z-4; i <= basechunk_z+4; i++) {
                StringBuilder msg = new StringBuilder();
                for (int j = basechunk_x - 4; j <= basechunk_x + 4; j++) {
                    if (Ftion.loadClaim(world.getChunkAt(j, i), world) == null) {
                        if(j == basechunk_x && i == basechunk_z){
                            msg.append("§l§a▧");
                        } else msg.append("§r§a▧");
                    } else {
                        if(j == basechunk_x && i == basechunk_z){
                            msg.append("§l§c▧");
                        } else msg.append("§r§c▧");
                    }
                }
                TextComponent message = new TextComponent(msg.toString());
                message.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder("COORDONNEES DES CHUNKS\n Z=" + i + "\nX à gauche=" + (basechunk_x - 4) + "\nX à droite=" + (basechunk_x + 4)).create()));
                player.spigot().sendMessage(message);
            }
        }
        return false;
    }
}
