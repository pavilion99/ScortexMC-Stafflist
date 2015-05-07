package co.valdeon.ScortexMC.StaffPlugin.Listeners;

import co.valdeon.ScortexMC.StaffPlugin.Hooks.GMHook;
import co.valdeon.ScortexMC.StaffPlugin.StaffPlugin;
import org.anjocaido.groupmanager.permissions.AnjoPermissionsHandler;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerJoinListener implements Listener{

    @EventHandler
    public void onEvent(PlayerJoinEvent event) {
        Player p = event.getPlayer();
        AnjoPermissionsHandler h = StaffPlugin.getGMHook().getHandler(p);
        String rank = h.getGroup(p.getName());

        for(String s : StaffPlugin.staffRanks) {
            if(s.equals(rank)) {
                StaffPlugin.staffList.put(p, GMHook.getProperTitle(p, h));
                break;
            }
        }
    }

}
