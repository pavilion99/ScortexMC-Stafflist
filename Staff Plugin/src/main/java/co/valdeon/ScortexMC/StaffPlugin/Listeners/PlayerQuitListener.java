package co.valdeon.ScortexMC.StaffPlugin.Listeners;

import co.valdeon.ScortexMC.StaffPlugin.StaffPlugin;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerQuitListener implements Listener {

    @EventHandler
    public void onEvent(PlayerQuitEvent event) {
        Player p = event.getPlayer();
        StaffPlugin.staffList.remove(p);
    }

}
